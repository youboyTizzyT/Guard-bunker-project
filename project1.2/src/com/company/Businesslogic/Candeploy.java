package com.company.Businesslogic;

import com.company.ServerHandler.Split;
import com.company.suanfa.AlgorithmComputingDistance;
import redis.clients.jedis.Jedis;

/**
 * Created by weicong on 17-8-14.
 */
public class Candeploy {
    public void Candeploy(){

    }
    public String candeploy( String massages1,String massages2){//AT|uid,fid|50,50 到指定位置
        Jedis jedis=new  Jedis("127.0.0.1", 6379);
        Split split=new Split();
        String response;
        String[] mas2=split.getsome(massages1);//mas2[0]=uid,mas2[1]=fid
        String[] mas5=split.getsome(massages2);
        if (jedis.get("MAKETRUE|"+mas2[1])==null){
                System.out.println("1111");
            String[] mas3=jedis.hget("BUNKER",mas2[1]).split("\\,");//mas3=用碉堡id查到的所有信息   mas3[0]碉堡坐标
//            System.out.println(mas3[0]+"   "+mas3[1]);
//            String[] mas4=split.getsome(mas3[0]);//mas4碉堡坐标

                System.out.println("2222");
            int mx=Integer.parseInt(mas5[0]);//客户端给我传过来我的xy
            System.out.println(mx);
            int my=Integer.parseInt(mas5[1]);
            int fx=Integer.parseInt(mas3[0]);//碉堡的坐标 xy
            int fy=Integer.parseInt(mas3[1]);
                System.out.println("33333");
            AlgorithmComputingDistance algorithmComputingDistance=new AlgorithmComputingDistance(fx,fy,mx,my);
            Double result=algorithmComputingDistance.aaa();//算法，计算距离
                System.out.println("44444");
            if(algorithmComputingDistance.judge(result)){//算法，是否允许
                jedis.set("MAKETRUE|"+mas2[1],mas2[0]+"|ALLOW TROOPS");
                    System.out.println("55555");
                response="TRUE|ALLOWDISPATH";
            }else {
                response="FALSE|No LOCATION TO THE BUNKER";
                    System.out.println("66666");
            }

        }else {
            response="FALSE|SOMEONE IS ATTACKING";
        }
//        jedis.close();
        return response;
    }
}
