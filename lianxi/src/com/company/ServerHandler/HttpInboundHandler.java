package com.company.ServerHandler;

import com.company.Businesslogic.Candeploy;
import com.company.redis.JedisPool;
import com.company.suanfa.AlgorithmComputingDistance;
import com.company.suanfa.PKsuanfa;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weicong on 17-8-4.
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter{

//    Jedis jedis = null;
//        com.company.redis.JedisPool.initepool();
//
//    jedis= com.company.redis.JedisPool.getJedis();
//    private Jedis jedis=JedisPool.getJedis();


    public void channelRead(ChannelHandlerContext ctx, Object msg){

        Jedis jedis=new  Jedis("127.0.0.1", 6379);


//        jedis= com.company.redis.JedisPool.getJedis();

        //读
//        ByteBuf result = (ByteBuf) msg;
//        byte[] result1 = new byte[result.readableBytes()];
//        result.readBytes(result1);
//        String resultStr = new String(result1);
        String resultStr=(String)msg;//客户端所有数据
        System.out.println("Client said:" + resultStr);
        Split split=new Split();
//        int mb;
//        int mg;
//        int db;
//        \}int dg;

        String response="earry"  ;
        String[] massages=split.getevery(resultStr);//massage0 总装态，剩下的时总状态的分状态
        if (massages[0].equals("AT"))//AT|uid,fid|50,50
        {
            Candeploy candeploy=new Candeploy();

            String[] mas2=split.getsome(massages[1]);//mas2[0]=uid,mas2[1]=fid
            if (jedis.get("Confirm no attack|fid")==null){
//                System.out.println("1111");
                String[] mas3=split.getevery(jedis.hget("flist",mas2[1]));//mas3=用碉堡id查到的所有信息   mas2[1]碉堡id
                String[] mas4=split.getsome(mas3[0]);//mas4碉堡坐标
                String[] mas5=split.getsome(massages[2]);
//                System.out.println("2222");
                int mx=Integer.parseInt(mas5[0]);//客户端给我传过来我的xy
                System.out.println(mx);
                int my=Integer.parseInt(mas5[1]);
                int fx=Integer.parseInt(mas4[0]);//碉堡的坐标 xy
                int fy=Integer.parseInt(mas4[1]);
//                System.out.println("33333");
                AlgorithmComputingDistance algorithmComputingDistance=new AlgorithmComputingDistance(fx,fy,mx,my);
                Double result=algorithmComputingDistance.aaa();//算法，计算距离
//                System.out.println("44444");
                if(algorithmComputingDistance.judge(result)){//算法，是否允许
                    jedis.set("确认无人攻打|fid","uid|允许派兵");
//                    System.out.println("55555");
                    response="可以进行下一步了";
                }else {
                    response="您的位置不能攻打碉堡";
//                    System.out.println("66666");
                }

            }else {
                response="有人在攻打";
            }
        }
        else if (massages[0].equals("AS")){//AS|UID,FID|10,20
            String fid=massages[1].split("|")[1];
            if(jedis.get("确认无人攻打|"+fid).equals("uid|允许派兵")){
                String[] bingli=massages[2].split(",");
                int mb=Integer.parseInt(bingli[0]);
                int mg=Integer.parseInt(bingli[1]);
                int mbb=Integer.parseInt(jedis.get("我兵库步兵"));
                int mbg=Integer.parseInt(jedis.get("我兵库弓箭"));
                if (mb<=mbb||mg<=mbg){
                    response="派兵成功";
                }
                jedis.set("确认无人攻打|"+fid,"uid|可以攻打");


            }else response="有人在攻打中";

        }
        else if(massages[0].equals("AA")){
            String fid =massages[1].split(",")[1];
            if(jedis.get("确认无人攻打|"+fid).equals("uid|可以攻打")){
                String[] bingli=massages[2].split(",");
                 int mb=Integer.parseInt(bingli[0]);
                 int mg=Integer.parseInt(bingli[1]);
                 int db=Integer.parseInt(bingli[0]);
                 int dg=Integer.parseInt(bingli[1]);
                PKsuanfa pKsuanfa=new PKsuanfa(mb,mg,db,dg);

                jedis.hset("战绩系统","我的战绩",pKsuanfa.chuanshu());
//                ctx.writeAndFlush(pKsuanfa.chuanshu());
                response=pKsuanfa.chuanshu();
                jedis.set("确认无人攻打|"+fid,"uid|正在攻打");
            }else {
                response="错误代码";
            }
        }
        else if (massages[0].equals("AZ")){
            String uid=massages[1].split(",")[1];
            String fid=massages[1].split(",")[1];
            if(jedis.get("确认无人攻打|"+fid).equals(uid+"正在攻打|可以增援撤兵")) {
                String[] a = jedis.hget("战绩系统", "我的战绩").split(",");
                int mb = Integer.parseInt(a[0]);
                int mg = Integer.parseInt(a[1]);
                int db = Integer.parseInt(a[3]);
                int dg = Integer.parseInt(a[4]);
                PKsuanfa pKsuanfa=new PKsuanfa(mb,mg,db,dg);
                jedis.hset("战绩系统","我的战绩",pKsuanfa.chuanshu());
                response=pKsuanfa.chuanshu();
            }else {
                response="错误代码";
            }
        }else if(massages[0].equals("AC")){
            String uid=massages[1].split(",")[1];
            String fid=massages[1].split(",")[1];
            String[] a=massages[2].split(",");
            int mb = Integer.parseInt(a[0]);
            int mg = Integer.parseInt(a[1]);
            int db = Integer.parseInt(a[3]);
            int dg = Integer.parseInt(a[4]);
            PKsuanfa pKsuanfa=new PKsuanfa(mb,mg,db,dg);
            jedis.hset("战绩系统","我的战绩",pKsuanfa.chebing());
            response=pKsuanfa.chebing();

        }















        if (massages[0].equals("collect")) { //返回  UID|CID(碉堡ID)|CGPS|CZT（占领状态）|TID（占领人ID）
            List<String> collect = jedis.hmget("collect", "UID:", "CID", "CGPS", "CZT", "TID");
            String[] mycoll = new String[]{"UID", "CID", "CGPS", "CZT", "TID"};
            for (int i = 0; i < collect.size(); i++) {
                mycoll[i] = String.valueOf(collect.get(i));
            }
            response = "collect" + "|" + mycoll[0] + "|" + mycoll[1]
                    + "|" + mycoll[2] + "|" + mycoll[3] + "|" + mycoll[4];



        }
//删除收藏     接收到Delect|collect|UID|CID
        if (massages[0].equals("Delect") && massages[1].equals("collect")) {
            while (jedis.hget("collect", "UID") .equals(massages[3]))  {
                jedis.hdel("collect");
            }
             response = "DelectCollect成功";


        }
//添加收藏   接收到 Add|Collect|UID|CID|CGPS|CZT|TID
        if(massages[0].equals("Add")&&massages[1].equals("collect")){
            Map<String, String> map = new HashMap<>();
            map.put("UID",massages[2]);
            map.put("CID",massages[3]);
            map.put("CGPS",massages[4]);
            map.put("CZT",massages[5]);
            map.put("TID",massages[6]);
            jedis.hmset("collect",map);
            response = "DelectCollect成功";


        }
//总战绩  接收到  zhanji|UID   返回 zhanji|CID|CGPS|SMOUT(并库里士兵数量)|STYPE（士兵类型）|TIME|TID（对方ID）
        if(massages[0].equals("zhanji")){
            List<String> jieshou= jedis.hmget("zhanji","CID","CGPS","SMOUT","STYPE","TIME","TID");
            String[] myzhanji = new String[]{ "CID", "CGPS", "SMOUT", "STYPE", "TIME", "TID"};
            for (int i = 0; i < jieshou.size(); i++) {
                myzhanji[i] = jieshou.get(i);
            }
            //Time t = new Time(myzhanji[4]);
            response = "zhanji" + "|" + myzhanji[0] + "|" + myzhanji[1]
                    + "|" + myzhanji[2] + "|" + myzhanji[3] + "|" + myzhanji[4]+ myzhanji[5];


        }


        if (massages[0].equals("abandon"))

        {

            String[] mas2=split.getsome(massages[1]);
            if(jedis.get("bk｜bubing|")==null){
                System.out.println("不可丢弃步兵");

            }else{
                String[] mas6=split.getevery(jedis.hget("blist",mas2[2]));//兵库ｉｄ
                String[] mas7=split.getsome(mas6[0]);//70步兵　7１弓箭手
                System.out.println("可以丢弃步兵");
                int a=Integer.parseInt(mas7[0]);
                System.out.println("得到要丢弃的步兵数量");
                String c=null;
                c=jedis.hget("bk","bubing");

                int d = 0;
                d = new Integer(c) - a;
                jedis.hset("bk","bubing", String.valueOf(d));
                System.out.println("成功丢弃弓箭手"+d);

            }

        }
        if (massages[0].equals("abandon"))

        {

            String[] mas2=split.getsome(massages[1]);
            if(jedis.get("bk｜gongjianshou|")==null){
                System.out.println("不可丢弃弓箭手");

            }else{
                String[] mas6=split.getevery(jedis.hget("blist",mas2[2]));//兵库ｉｄ
                String[] mas7=split.getsome(mas6[0]);//70步兵　7１弓箭手
                System.out.println("可以丢弃弓箭手");
                int b=Integer.parseInt(mas7[1]);
                System.out.println("得到要丢弃的弓箭手数量");
                jedis.hset("bk","gongjianshou", String.valueOf(b));

                System.out.println("成功丢弃弓箭手"+b);

            }

        }
        if (massages[0].equals("train")) {


            String[] mas2=split.getsome(massages[1]);
            String[] mas8=split.getsome(mas2[0]);
            int a=Integer.parseInt(mas8[0]);

        }
        if (massages[0].equals("shoucang"))

        {
            String[] mas2=split.getsome(massages[1]);//mas2[0]=uid,mas2[1]=fid
            int a=Integer.parseInt(mas2[1]);
            jedis.set("flist",mas2[1]);


//            if(suanfa2.judge(douhble a){
//            jedis.set("确认存在|fid","允许收藏");
//            System.out.println("55555");
//            response="可以进行下一步了";
//        }else{
//            response = "不存在，不可收藏";
//            System.out.println("66666");

        }
//        if (a[0].equals("BUSHU")){
//
//        }

//        写入
//        String response ="123" ;
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(encoded);
        ctx.write(response);
        ctx.flush();

//        ctx.flush();
        System.out.println("========================");
    }

}
