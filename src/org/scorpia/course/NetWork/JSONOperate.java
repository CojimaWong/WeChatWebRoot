package org.scorpia.course.NetWork;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONOperate {

    public List<App> sendRequestWithOKHttp (){
        List<App> apps=new ArrayList<>();
        try{
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .url("http://10.104.6.29:82/get_data.json")
                    .build();
            Response response=client.newCall(request).execute();
            String responseData=response.body().string();
            apps=parseXMLWithJSONObject(responseData);
                }catch (Exception e){
        }

        return apps;
    }

    public List<App> parseXMLWithJSONObject(String jsonData){
        List<App> apps=new ArrayList<>();
        App app;
        try{
            JSONArray jsonArray=new JSONArray(jsonData);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
                String version=jsonObject.getString("version");
                app=new App();
                app.setId(id);
                app.setName(name);
                app.setVersion(version);
                apps.add(app);
            }

        }catch (Exception e){
        }
        return apps;
    }

}
