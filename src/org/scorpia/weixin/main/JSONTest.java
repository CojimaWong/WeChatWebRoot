package org.scorpia.weixin.main;

import org.scorpia.course.NetWork.App;
import org.scorpia.course.NetWork.JSONOperate;
import org.scorpia.course.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;


public class JSONTest {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);


    public static void main(String[] args){
        List<App> apps;
        JSONOperate json=new JSONOperate();
        apps=json.sendRequestWithOKHttp();
        StringBuilder builder=new StringBuilder();
        for(App app:apps){
            builder.append("Id:"+app.getId()+"\nName:"+app.getName()+"\nVersion"+app.getVersion()+"\n");
        }
        log.info(builder.toString());
    }
}
