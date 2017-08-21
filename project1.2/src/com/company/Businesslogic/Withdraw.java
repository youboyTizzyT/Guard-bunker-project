package com.company.Businesslogic;

import com.company.suanfa.PKsuanfa;
import com.company.suanfa.bubing;
import redis.clients.jedis.Jedis;

/**
 * Created by weicong on 17-8-17.
 */
public class Withdraw {
    //WITHDRAW|uid,fid
    public String withdraw(String massages1,String massages2){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        String response="";
        String uid = massages1.split("\\,")[0];
        String fid = massages1.split("\\,")[1];
//        String[] a = massages2.split("\\,");
        String[] chushibingli=jedis.get("MAKETRUE|"+uid).split("\\,");
        int db=Integer.parseInt(chushibingli[1]);
        int mb=Integer.parseInt(chushibingli[0]);
        bubing bubing1=new bubing();
        bubing1.setnumber(mb);
        bubing1.setprosition(0);
        bubing bubing2=new bubing();
        bubing2.setnumber(db);
        bubing2.setprosition(10);
        PKsuanfa pKsuanfa=new PKsuanfa(bubing1,bubing2);
//            pKsuanfa.jisuan();
        jedis.publish("MAKETRUE|"+fid,"WITHDRAW|"+pKsuanfa.jisuan());
        jedis.set("MAKETRUE|"+fid,pKsuanfa.jisuan());
        response=pKsuanfa.jisuan()+"%"+uid+"0";
        //        PKsuanfa pKsuanfa = new PKsuanfa(mb, mg, db, dg);
//        jedis.hset("战绩系统", "我的战绩", pKsuanfa.chebing());
//        response = pKsuanfa.chebing();
        return response;
    }
}
