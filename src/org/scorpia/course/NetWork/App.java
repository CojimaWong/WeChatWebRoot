package org.scorpia.course.NetWork;

/**
 * Created by xiaow on 2017/3/12.
 */

public class App {
    private String id;
    private String name;
    private String version;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getVersion(){
        return version;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setVersion(String version){
        this.version=version;
    }

    public void setId(String id){
        this.id=id;
    }


}
