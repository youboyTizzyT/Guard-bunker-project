package com.company.ServerHandler;

/**
 * Created by weicong on 17-8-9.
 */
public class Split {
    public String[] getevery(String a){
        String[] aaa=a.split("\\|");
        return aaa;
    }
    public String[] getsome(String a){
        String[] aaa=a.split("\\,");
        return aaa;

    }
    public String[] geteach(String a){
        String[] aaa=a.split("\\#");
        return aaa;

    }
}
