package com.company.Businesslogic;

import com.company.ServerHandler.Split;
import com.company.suanfa.PKsuanfa;
import com.company.suanfa.bubing;
import com.company.suanfa.gongjian;
import redis.clients.jedis.Jedis;

/**
 * Created by weicong on 17-8-15.
 */

//jRedis.publish
public class Attack {
    //attack|uid,fid|10,20
    public String attack(String massages1,String massages2){
        Jedis jedis=new  Jedis("127.0.0.1", 6379);
        String response="";

        String uid =massages1.split("\\,")[0];
        String fid =massages1.split("\\,")[1];
        if(jedis.get("MAKETRUE|"+fid).equals(uid+"|CAN ATTACK")){
            String[] bingli=massages2.split("\\,");
            int mb=Integer.parseInt(bingli[0]);
//            int mg=Integer.parseInt(bingli[1]);
            int db=Integer.parseInt(bingli[1]);
//            int dg=Integer.parseInt(bingli[1]);
            bubing bubing1=new bubing();
            bubing1.setnumber(mb);
            bubing1.setprosition(0);
            bubing bubing2=new bubing();
            bubing2.setnumber(db);
            bubing2.setprosition(10);
//            gongjian gongjian1=new gongjian();
//            gongjian1.setnumber(mg);
//            gongjian1.setprosition(0);
//            gongjian gongjian2=new gongjian();
//            gongjian2.setnumber(dg);
//            gongjian2.setprosition(10);
//            PKsuanfa pKsuanfa=new PKsuanfa(bubing1,gongjian1,bubing2,gongjian2);
PKsuanfa pKsuanfa=new PKsuanfa(bubing1,bubing2);
                response="TRUE|IS ATTACK";
//            PKsuanfa pKsuanfa=new PKsuanfa(a,b);
//            System.out.println(pKsuanfa.getjieguo());
//            jedis.lpush("RECORD|"+uid,pKsuanfa.chuanshu());
//                ctx.writeAndFlush(pKsuanfa.chuanshu());
//            response=pKsuanfa.chuanshu();
            jedis.publish("MAKETRUE|"+fid,pKsuanfa.jisuan());
            jedis.set("MAKETRUE|"+fid,massages2);
        }else {
            response="错误代码";
        }

        return response;
    }
}

