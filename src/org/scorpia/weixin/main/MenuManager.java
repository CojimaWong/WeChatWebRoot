package org.scorpia.weixin.main;

import org.scorpia.course.menu.Button;
import org.scorpia.course.menu.ClickButton;
import org.scorpia.course.menu.ComplexButton;
import org.scorpia.course.menu.Menu;
import org.scorpia.course.menu.ViewButton;
import org.scorpia.course.pojo.Token;
import org.scorpia.course.util.CommonUtil;
import org.scorpia.course.util.MenuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 * 
 * @author scorpia
 * @date 2013-10-17
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("一篇文章");
		btn11.setType("click");
		btn11.setKey("article");

		ClickButton btn12 = new ClickButton();
		btn12.setName("一句话");
		btn12.setType("click");
		btn12.setKey("note");

		ViewButton btn13 = new ViewButton();
		btn13.setName("我的网站");
		btn13.setType("view");
		btn13.setUrl("http://119.29.213.109:82");

		ClickButton btn14 = new ClickButton();
		btn14.setName("读取服务器数据");
		btn14.setType("click");
		btn14.setKey("data");



		ViewButton btn21 = new ViewButton();
		btn21.setName("百度");
		btn21.setType("view");
		btn21.setUrl("http://www.baidu.com");

		ClickButton btn22 = new ClickButton();
		btn22.setName("一首歌");
		btn22.setType("click");
		btn22.setKey("music");

		ViewButton btn31 = new ViewButton();
		btn31.setName("游民星空");
		btn31.setType("view");
		btn31.setUrl("http://www.gamersky.com");

		ViewButton btn32 = new ViewButton();
		btn32.setName("4399");
		btn32.setType("view");
		btn32.setUrl("http://www.4399.com");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("技术交流");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13,btn14, });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("搜索网站");
		mainBtn2.setSub_button(new Button[] { btn21,btn22});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("游戏网站");
		mainBtn3.setSub_button(new Button[] { btn31, btn32 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx2768984b0a6aed8a";
		// 第三方用户唯一凭证密钥
		String appSecret = "4e52b02b59b0931b962dac243292755f";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}
	}
}
