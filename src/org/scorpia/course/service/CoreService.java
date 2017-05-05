package org.scorpia.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.scorpia.course.NetWork.App;
import org.scorpia.course.NetWork.JSONOperate;
import org.scorpia.course.message.resp.*;
import org.scorpia.course.util.MessageUtil;
import org.scorpia.weixin.main.MenuManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 核心服务类
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息";
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 设置文本消息的内容
			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			respXml = MessageUtil.messageToXml(textMessage);
			// 事件推送
			 if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
					// 设置文本消息的内容
					textMessage.setContent(respContent);
					// 将文本消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					if (eventKey.equals("article")) {
						Article article = new Article();
						article.setTitle("水调歌头");
						article.setDescription("明月几时有？把酒问青天。不知天上宫阙、今夕是何年？我欲乘风归去，惟恐琼楼玉宇，高处不胜寒．起舞弄清影，何似在人间？  \n转朱阁，低绮户，照无眠。不应有恨、何事长向别时圆？人有悲欢离合，月有阴晴圆缺，此事古难全。但愿人长久，千里共蝉娟。");
						article.setPicUrl("");
						article.setUrl("http://baike.baidu.com/link?url=sDmQGEGJoYfi8oGipjoVvamLNiuLKDa1G89_SMUb8icRIqMp1Zm1bzOFr2oMYnVaI102ck6NC00_VQ8UFXRxTtyQ5KbmNw7DwIARhtaljnwve8IgFMfP9Ae2ybAln_xj");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("note")) {
						textMessage.setContent("您好\n您点击了一句话按钮");
						respXml = MessageUtil.messageToXml(textMessage);
					}else if(eventKey.equals("data"))
					{
						List<App> apps;
						JSONOperate json=new JSONOperate();
						apps=json.sendRequestWithOKHttp();
						StringBuilder builder=new StringBuilder();
						for(App app:apps){
							builder.append("Id:"+app.getId()+"\nName:"+app.getName()+"\nVersion"+app.getVersion()+"\n");
						}
						textMessage.setContent(builder.toString());
						respXml = MessageUtil.messageToXml(textMessage);
					}else if(eventKey.equals("music"))
					{

						textMessage.setContent("http://119.29.213.109:82/music.mp3");
						respXml = MessageUtil.messageToXml(textMessage);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
