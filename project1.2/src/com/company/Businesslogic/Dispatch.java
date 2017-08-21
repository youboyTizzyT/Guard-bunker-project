package com.company.Businesslogic;

import com.company.ServerHandler.Split;
import redis.clients.jedis.Jedis;

/**
 * Created by weicong on 17-8-15.
 */
public class Dispatch {
    public String dispath(String massages1,String massages2){
        //DISPATCH|UID,FID|10,20
        Jedis jedis=new  Jedis("127.0.0.1", 6379);
        String response;
        String fid=massages1.split("\\,")[1];
        String uid=massages1.split("\\,")[0];
        if(jedis.get("MAKETRUE|"+fid).equals(uid+"|ALLOW TROOPS")){
            String[] bingli=massages2.split(",");
            int mb=Integer.parseInt(bingli[0]);
            int mg=Integer.parseInt(bingli[1]);
            int mbb=Integer.parseInt(jedis.hget(uid+"|HYOGOLIST","Infantry"));
            int mbg=Integer.parseInt(jedis.hget(uid+"|HYOGOLIST","Bow"));
            if (mb<=mbb&&mg<=mbg){
                response="TRUE|DISPATCH SUCCESS";
                jedis.set("MAKETRUE|"+fid,uid+"|CAN ATTACK");
                int fmg=mbg-mg;
                String fmg1= String.valueOf(fmg);
                int fmb=mbb-mb;
                String fmb1=String.valueOf(fmb);
                jedis.hset(uid+"|HYOGO","Bow",fmg1);
                jedis.hset(uid+"|HYOGO","Infantry",fmb1);

            }else {
                response="FALSE|YOU HAVA NO SOLDIERS";

            }
        }else response="FALSE|SOMEONE IS ATTACKING";

        jedis.close();
        return response;

    }
}
