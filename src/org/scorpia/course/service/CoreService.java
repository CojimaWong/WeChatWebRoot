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
 * ���ķ�����
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		// Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = "δ֪����Ϣ���ͣ�";
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "�����͵����ı���Ϣ";
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// �����ı���Ϣ������
			textMessage.setContent(respContent);
			// ���ı���Ϣ����ת����xml
			respXml = MessageUtil.messageToXml(textMessage);
			// �¼�����
			 if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ��ע
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "лл���Ĺ�ע��";
					// �����ı���Ϣ������
					textMessage.setContent(respContent);
					// ���ı���Ϣ����ת����xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// ȡ����ע
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
				}
				// ɨ���������ά��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO ����ɨ���������ά���¼�
				}
				// �ϱ�����λ��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO �����ϱ�����λ���¼�
				}
				// �Զ���˵�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// �¼�KEYֵ���봴���˵�ʱ��keyֵ��Ӧ
					String eventKey = requestMap.get("EventKey");
					// ����keyֵ�ж��û�����İ�ť
					if (eventKey.equals("article")) {
						Article article = new Article();
						article.setTitle("ˮ����ͷ");
						article.setDescription("���¼�ʱ�У��Ѿ������졣��֪���Ϲ��ڡ���Ϧ�Ǻ��ꣿ�����˷��ȥ��Ω����¥����ߴ���ʤ��������Ū��Ӱ���������˼䣿  \nת��󣬵�粻��������ߡ���Ӧ�кޡ����³����ʱԲ�����б�����ϣ���������Բȱ�����¹���ȫ����Ը�˳��ã�ǧ�ﹲ���ꡣ");
						article.setPicUrl("");
						article.setUrl("http://baike.baidu.com/link?url=sDmQGEGJoYfi8oGipjoVvamLNiuLKDa1G89_SMUb8icRIqMp1Zm1bzOFr2oMYnVaI102ck6NC00_VQ8UFXRxTtyQ5KbmNw7DwIARhtaljnwve8IgFMfP9Ae2ybAln_xj");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// ����ͼ����Ϣ
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("note")) {
						textMessage.setContent("����\n�������һ�仰��ť");
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
