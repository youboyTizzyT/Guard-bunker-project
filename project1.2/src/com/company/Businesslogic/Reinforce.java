package com.company.Businesslogic;

import com.company.suanfa.PKsuanfa;
import com.company.suanfa.bubing;
import redis.clients.jedis.Jedis;

/**
 * Created by weicong on 17-8-17.
 */
//jRedis.publish
public class Reinforce {

    //REINFORCE|uid,fid|10
    public String reinforce(String massages1,String massages2) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String response="";
        String uid = massages1.split("\\,")[1];
        String fid = massages1.split("\\,")[1];
//        if (jedis.get("MAKETRUE|" + fid)!=null) {
            int a=Integer.parseInt(massages2);
            String[] chushibingli=jedis.get("MAKETRUE|" + fid).split("\\,");
//        System.out.println(chushibingli[0]);
//            bubing bubing=new bubing();
            int db=Integer.parseInt(chushibingli[1]);
            int mb=Integer.parseInt(chushibingli[0]);
            bubing bubing1=new bubing();
            bubing1.setnumber(mb);
            bubing1.setprosition(0);
            bubing bubing2=new bubing();
            bubing2.setnumber(db);
            bubing2.setprosition(10);
            PKsuanfa pKsuanfa=new PKsuanfa(bubing1,bubing2);
            bubing bubing3=new bubing();
            bubing3.setprosition(0);
            bubing3.setnumber(Integer.parseInt(massages2));
            pKsuanfa.zengyuan(bubing3);
//            pKsuanfa.jisuan();
            response="TRUE|IS REINFORCE";
            jedis.publish("MAKETRUE|"+fid,pKsuanfa.jisuan());
//            jedis.set("MAKETRUE|"+fid,massages2);


//        } else {
//            response = "错误代码";
//        }
        return response;
    }

}
