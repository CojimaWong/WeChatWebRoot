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
 * �˵���������
 * 
 * @author scorpia
 * @date 2013-10-17
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * ����˵��ṹ
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("һƪ����");
		btn11.setType("click");
		btn11.setKey("article");

		ClickButton btn12 = new ClickButton();
		btn12.setName("һ�仰");
		btn12.setType("click");
		btn12.setKey("note");

		ViewButton btn13 = new ViewButton();
		btn13.setName("�ҵ���վ");
		btn13.setType("view");
		btn13.setUrl("http://119.29.213.109:82");

		ClickButton btn14 = new ClickButton();
		btn14.setName("��ȡ����������");
		btn14.setType("click");
		btn14.setKey("data");



		ViewButton btn21 = new ViewButton();
		btn21.setName("�ٶ�");
		btn21.setType("view");
		btn21.setUrl("http://www.baidu.com");

		ClickButton btn22 = new ClickButton();
		btn22.setName("һ�׸�");
		btn22.setType("click");
		btn22.setKey("music");

		ViewButton btn31 = new ViewButton();
		btn31.setName("�����ǿ�");
		btn31.setType("view");
		btn31.setUrl("http://www.gamersky.com");

		ViewButton btn32 = new ViewButton();
		btn32.setName("4399");
		btn32.setType("view");
		btn32.setUrl("http://www.4399.com");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("��������");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13,btn14, });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("������վ");
		mainBtn2.setSub_button(new Button[] { btn21,btn22});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("��Ϸ��վ");
		mainBtn3.setSub_button(new Button[] { btn31, btn32 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "wx2768984b0a6aed8a";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "4e52b02b59b0931b962dac243292755f";

		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// �����˵�
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// �жϲ˵��������
			if (result)
				log.info("�˵������ɹ���");
			else
				log.info("�˵�����ʧ�ܣ�");
		}
	}
}
