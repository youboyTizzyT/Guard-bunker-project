package com.company.ServerHandler;

import com.company.Businesslogic.*;
import com.company.redis.JedisPool;
import com.company.suanfa.AlgorithmComputingDistance;
import com.company.suanfa.GeneratingUID;
import com.company.suanfa.PKsuanfa;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Jedis;

import java.security.KeyStore;
import java.util.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

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


        String[] massages=resultStr.split("\\|");//massage0 总装态，剩下的时总状态的分状态
        String response="";
        System.out.println(massages[0].equals("LOGIN"));
        /*程斌*/
        //登陆login|手机号|密码    LOGIN|111|222
        if (massages[0].equals("LOGIN")) {

            if (jedis.get(massages[1])==null) {
                response = "FALSE|NOT FOUND ID";
            }
            else if (jedis.get(massages[1])!=null&&jedis.get(massages[1]).equals(massages[2])) {
                response = "TRUE|CAN LOGIN";
            } else {
                response = "FALSE|PWD IS NOT TRUE";
            }


        }
//signup|手机号|密码   GETVC|111|222
        else if (massages[0].equals("GETVC")) {
            if (jedis.get(massages[1]) != null) {
                response = "FALSE|YOU PHONE NUM DOSE REGISTER";
            }
            if (jedis.get(massages[1]) == null) {
                VerificationCode yzm = new VerificationCode();
                //调用短信接口发送验证码短信到用户手机
                jedis.set(massages[1]+"|"+massages[2], yzm.getVerificationCode());
                jedis.expire(massages[1]+"|"+massages[2],300);
                response="TRUE|PLASE WAIT MASSAGE";
            }
        }//没问题
//        System.out.println(111111);   REGISTER|111|222|123123
        else if (massages[0].equals("REGISTER")) {
//            System.out.println(jedis.get(massages[1]+"|"+massages[2]));
//            System.out.println(massages[3]);
//            System.out.println(jedis.get(massages[1]+"|"+massages[2]).equals(massages[3]));
            if (jedis.get(massages[1]+"|"+massages[2]).equals(massages[3])){
//                System.out.println(333333);
                jedis.set(massages[1],massages[2]);
                jedis.del(massages[1]+"|"+massages[2]);

                response="TRUE REGISTER";
            }else {
                response="FALSE|PLASE AGAIN IN";

            }
        }//没问题




//        friendsearch
        //FRIENDLIST|uid       FRIENDLIST|111
        else if (massages[0].equals("FRIENDLIST")) {/*好友查询*/

            if(jedis.hget("FRIENDLIST",massages[1])==null){
                jedis.hset("FRIENDLIST",massages[1],"");
                response="TRUE|YOUDON`HAVE FRIEND";
            }//第一次打开
            else {
                String[] friendString = jedis.hget("FRIENDLIST", massages[1]).split("\\|");
                response = "TRUE|YOU FRIENDS :";
                for (String a : friendString) {
                    response = response + a + "\t";
                }
            }

        }//没问题
//        List
//friendadd  k friend|uid v uid


//        ADDFRIEND|uid|对方udi    ADDFRIEND|111|222
        if (massages[0].equals("ADDFRIEND")){
            if(jedis.hget("FRIENDLIST",massages[1])==null){
                jedis.hset("FRIENDLIST",massages[1],"");
            }//第一次打开
            if (jedis.get(massages[2])!=null){//确认这个个好友id
                if (jedis.hget("FRIENDLIST",massages[1]).equals("")){
                    jedis.hset("FRIENDLIST", massages[1],
                           massages[2]);
                    response="TRUE|FRIEND ADD SUCCESS";
                }else {
                    String e="";
                    String[] d = jedis.hget("FRIENDLIST", massages[1]).split("\\|");
                    for (String a : d) {
                        if (a.equals(massages[2])) {
                            e = a;
                            continue;
                        }

                    }if (e.equals("")) {
                        jedis.hset("FRIENDLIST", massages[1], jedis.hget("FRIENDLIST", massages[1])+"|" + massages[2]);
                        response = "TRUE|FRIEND ADD SUCCESS";
                    }else {
                        response = "FLASE|HE IS YOUR FRIEND";
                    }
                }
            }else {
                response="FLASE|PLASE MAKE UID ";//OR HE DOSE YOU FRIEND";
            }
        }//判断有问题，如果对方输入的不是uid，如果对方输入的uid是你的好友。
//        deletefriend
        //DELFRIEND|uid|对方uid     DELFRIEND|111|222
        if (massages[0].equals("DELFRIEND")){
            if(jedis.hget("FRIENDLIST",massages[1])==null){

                response="FLASE|YOU DON`T MAY BE";
            }
            else if (jedis.get(massages[2])!=null) {
                String[] a=jedis.hget("FRIENDLIST",massages[1]).split("\\|");
                LinkedList res=new LinkedList() ;
                for(String c:a){
                    if (c.equals(massages[2])){
                        continue;
                    }else {
                        res.add(c);
                    }


                }
//            System.out.println(res);
                String qqq="";
                for (int c=0;c<res.size()-1;c++){
                    qqq=qqq+res.get(c)+"|";
                }
                qqq=qqq+res.get(res.size()-1);
                jedis.hset("FRIENDLIST",massages[1],qqq);
                response="TRUE|FRIEND DEL SUCCESS";
            }else {
                response="FALSE|FRIEND DEL UN SUCCESS";
            }
        }//继续更改












//我写的

//FIGHT|111,111|50,50
        if (massages[0].equals("FIGHT"))//FIGHT|uid,fid|50,50 到指定位置。
        {
            Candeploy candeploy=new Candeploy();
            response=candeploy.candeploy(massages[1],massages[2]);
        }//没问题    DISPATCH|111,111|10,0
        else if (massages[0].equals("DISPATCH")){//DISPATCH|UID,FID|10,20
            Dispatch dispatch=new Dispatch();
            response=dispatch.dispath(massages[1],massages[2]);
        }//没问题
//        else if(massages[0].equals("ATTACK")){
//            Attack attack=new Attack();
//            attack.attack(massages[1],massages[2]);
//
//        }
//        else if (massages[0].equals("REINFORCE")){
//            Reinforce reinforce=new Reinforce();
//            reinforce.reinforce(massages[1],massages[2]);
//
//        }else if(massages[0].equals("WITHDRAW")) {
//            Withdraw withdraw=new Withdraw();
//            withdraw.withdraw(massages[1],massages[2]);
//        }














/*敬东写的
*
* */
            //查看收藏COLLECT|UID|     COLLECT|111
        else if (massages[0].equals("COLLECT")) {//返回  UID|CID(碉堡ID)|CGP(坐标)|CZT（占领状态）|TID（占领人ID）
           if (jedis.hget("COLLECT",massages[1])==null){
               jedis.hset("COLLECT",massages[1],"");
               response = "TRUE|YOU DON`T HAVE COLLECT";
           }else {
               response = "TRUE|YOU COLLECT IS:" + jedis.hget("COLLECT", massages[1]);
           }
        }
//删除收藏     接收到Delcollect|UID|CID    DELCOLLECT|111|222
        else if (massages[0].equals("DELCOLLECT")) {
            LinkedList res=new LinkedList() ;
            String[] a=jedis.hget("COLLECT",massages[1]).split("\\|");
            for(String c:a){
                if (c.equals(massages[2])){
                    continue;
                }else {
                    res.add(c);
                }


            }
//            System.out.println(res);
            String qqq="";
            for (int c=0;c<res.size()-1;c++){
                qqq=qqq+res.get(c)+"|";
            }
            qqq=qqq+res.get(res.size()-1);
            jedis.hset("COLLECT",massages[1],qqq);
            response="TRUE|SUCCESS DEL";
//            System.out.println(qqq);

//           Map map=new HashMap();
//           for (int i=0;i<a.length;i++){
//           map.put(a[i],null);}
//           map.remove(massages[2]);
//            Set b=map.keySet();
//            Object[] result = b.toArray();
//            for (int i=0;i<result.length;i++){
//                response+=result[i]+"|";
//            }


        }//没有等？只能跑一次看看效果
//添加收藏   接收到 AddCollect|UID|fid     ADDCOLLECT|111|111
        else if(massages[0].equals("ADDCOLLECT")){

            if (jedis.hget("COLLECT",massages[1])==null){
                jedis.hset("COLLECT",massages[1],massages[2]);
                response="true";
            }else {
                System.out.println("1111");
                jedis.hset("COLLECT", massages[1], massages[2] + "|"+jedis.hget("COLLECT", massages[1]));
                response="true1";
            }
//            String[] a=jedis.hget("collect",massages[1]).split("\\|");
//            Map map=new HashMap();
//            for (int i=0;i<a.length;i++){
//                map.put(a[i],null);}
//            map.put(massages[2],null);
//            Set b=map.keySet();
//            Object[] result = b.toArray();
//            for (int i=0;i<result.length;i++){
//                response+=result[i]+"|";
//            }


        }//重复怎么办？
//总战绩  接收到  zhanji|UID   返回 zhanji|CID|CGPS|SMOUT(并库里士兵数量)|STYPE（士兵类型）|TIME|TID（对方ID）
//        else if(massages[0].equals("zhanji")){
//            List<String> jieshou= jedis.hmget("zhanji","CID","CGPS","SMOUT","STYPE","TIME","TID");
//            String[] myzhanji = new String[]{ "CID", "CGPS", "SMOUT", "STYPE", "TIME", "TID"};
//            for (int i = 0; i < jieshou.size(); i++) {
//                myzhanji[i] = jieshou.get(i);
//            }
//            //Time t = new Time(myzhanji[4]);
//            String zhanji = "zhanji" + "|" + myzhanji[0] + "|" + myzhanji[1]
//                    + "|" + myzhanji[2] + "|" + myzhanji[3] + "|" + myzhanji[4]+ myzhanji[5];
//            ByteBuf sc = ctx.alloc().buffer(4 * zhanji.length());
//            sc.writeBytes(zhanji.getBytes());
//            ctx.write(sc);
//        }





//wangshengxue
        //HYOGOLIST|uid    HYOGOLIST|111
        else if(massages[0].equals("HYOGOLIST")){
            if (jedis.hgetAll(massages[1]+"|HYOGOLIST").isEmpty()){
                jedis.hset(massages[1]+"|HYOGOLIST","Infantry","10");
                jedis.hset(massages[1]+"|HYOGOLIST","Bow","10");
            }

            response="TRUE|YOU Infantry:"+jedis.hget(massages[1]+"|HYOGOLIST","Infantry")
                    +"\t"+"YOU Bow:"+jedis.hget(massages[1]+"|HYOGOLIST","Bow")+"\n";
            if (jedis.get("TRAIN|"+massages[1])!=null){
                response=response+"YOU TRAINED"+jedis.get("TRAIN|"+massages[1]);
            }
        }    //HYOGOTRAIN|uid|10
        //HYOGOTRAIN|uid|数量    jedis.expire(massages[1]+"|"+massages[2],300);
        else if (massages[0].equals("HYOGOTRAIN"))

        {if (jedis.get("TRAIN|"+massages[1])!=null){
            response="FLASE|YOU CAN`T TRAIN";
        }else {
            jedis.set("TRAIN|"+massages[1],massages[2]);
            jedis.expire("TRAIN|"+massages[1],3);
            response="TRUE|TRAIN";
            new Thread(){
                public void run() {
                    while(true){
                        if (jedis.get("TRAIN|"+massages[1])==null){
                            String mb=jedis.hget(massages[1]+"|HYOGOLIST","Infantry");
                            int a=Integer.parseInt(mb);
                            int b=Integer.parseInt(massages[2]);
                            jedis.hset(massages[1]+"|HYOGOLIST","Infantry", String.valueOf(a+b));
                            jedis.del("TRAIN|"+massages[1]);
                            ctx.writeAndFlush("TRUE|TRAIN IS OK!");
                            break;
                        }
                    }

                }

            }.start();
        }
        }//DELHYOGO|uid|10
        else if (massages[0].equals("DELHYOGO")){
            String a=jedis.hget(massages[1]+"|HYOGOLIST","Infantry");
            int b=Integer.parseInt(a);
            int c=Integer.parseInt(massages[2]);
            if (b>c){
                jedis.hset(massages[1]+"|HYOGOLIST","Infantry", String.valueOf(b-c));
                response="TRUE|DEL SUCCESS";
            }else{
                response="FLASE|DEL FLOEWEFadsfd";
            }
        }
//        if (massages[0].equals("shoucang"))
//
//        {
//            String[] mas2=split.getsome(massages[1]);//mas2[0]=uid,mas2[1]=fid
//            int a=Integer.parseInt(mas2[1]);
//            jedis.set("flist",mas2[1]);
//            if (massages[0].equals("shoucang"))
//
//            {
//                String[] mass2=split.getsome(massages[1]);//mas2[0]=uid,mas2[1]=fid
//                int a1=Integer.parseInt(mass2[1]);
//                jedis.set("flist",mass2[1]);
//
//
//                if(suanfa2.judge(douhble a){
//                jedis.set("确认存在|fid","允许收藏");
//                System.out.println("55555");
//                response="可以进行下一步了";
//            }else{
//                response = "不存在，不可收藏";
//                System.out.println("66666");
//
//            }

//            if(suanfa2.judge(douhble a){
//            jedis.set("确认存在|fid","允许收藏");
//            System.out.println("55555");
//            response="可以进行下一步了";
//        }else{
//            response = "不存在，不可收藏";
//            System.out.println("66666");


//        if (a[0].equals("BUSHU")){
//
//        }

//        写入
//        String response ="123" ;
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(encoded);
//        jedis.close();
//        response="ATTACK"+response;
        System.out.println(response);
        ctx.write(response);
        ctx.flush();
//        jedis.close();
//
//        ctx.flush();
        System.out.println("========================");
    }

}
