package com.company.suanfa;

/**
 * Created by weicong on 17-8-5.
 */
public  class  PKsuanfa {
    private bubing ab;
    private bubing bb;
    String jieguo="";
    public PKsuanfa(bubing a,bubing b){
        this.bb=b;
        this.ab=a;
        jisuan();
    }

    public static void main(String[] args) {
        bubing a=new bubing();
        bubing b=new bubing();
        a.setnumber(10);
        a.setprosition(0);
        b.setnumber(20);
        b.setprosition(10);
        PKsuanfa pKsuanfa=new PKsuanfa(a,b);
        System.out.println(pKsuanfa.getjieguo());
        int shijian=2;
        String w="";
        for(int i=shijian;i<10;i++){
            String[] q=pKsuanfa.getjieguo().split("\\#"+shijian+"\\,");
            if (q[1].equals(null)){
                break;
            }
            w=w+q[0];
        }
        System.out.println(w);
    }
    public String jisuan(){
        for (int i=0;i<10;i++){
            System.out.println(i+"\t"+ab.getnumber()+"\t"+ab.getprosition()+"\t"+bb.getnumber()+"\t"+bb.getprosition());
            jieguo=jieguo+i+","+ab.getnumber()+","+ab.getprosition()+","+bb.getnumber()+","+bb.getprosition()+"#";
            if (bb.getprosition()==ab.getprosition()){
                ab.setnumber(ab.getnumber()-bb.getnumber());
                bb.setnumber(bb.getnumber()-ab.getnumber());
            }
            else{
                bb.setprosition(bb.getprosition()-1);
                ab.setprosition(ab.getprosition()+1);
            }
            if (bb.getnumber()<=0||ab.getnumber()<=0){
                bb.setnumber(0);
                ab.setnumber(0);
                break;
            }
        }
        return jieguo;
    }
    public String getjieguo(){

        return jieguo;
    }
    public String zengyuan(bubing ab)

    {

        bb.setnumber(bb.getnumber()+ab.getnumber());
        jieguo=jieguo+jisuan();


        return jieguo;
    }
//    private bubing ab;
//    private gongjian ag;
//    private bubing bb;
//    private gongjian bg;
//
//
//    private String jieguo="";
//    public  PKsuanfa(bubing a,gongjian b,bubing c,gongjian d){
//        this.ab=a;
//        this.ag=b;
//        this.bb=c;
//        this.bg=d;
//
//        init();
//
//    }
//    private void init(){
//    }
//    public String jisuan(){
//        for (int i=0;i<10;i++) {
//
//            if (bb.getprosition()-ag.getprosition()<=4&&bb.getnumber()!=0&&ag.getnumber()>=0){//判断是否能构成弓箭手打步兵
//                ag.setprosition(ag.getprosition()-1);//弓箭手停滞运动
//                bb.setnumber(bb.getnumber()-ag.getnumber());//步兵减员
//
//            }
//            if (bb.getnumber()<=0){//如果步兵死亡
//                bb.setnumber(0);
//                bb.setprosition(bb.getprosition()+1);
//                if (bg.getprosition()-ag.getprosition()<=4&&bg.getnumber()>=0&&ag.getnumber()>=0){//如果弓箭手能相互打架
//                    int c=bg.getnumber();
//                    bg.setnumber(c-ag.getnumber());
//                    ag.setnumber(ag.getnumber()-c);//双方减员
//                }else {//弓箭手前行
//                    ag.setprosition(ag.getprosition()+1);
//                }
//
//            }
//
//            if (bg.getprosition()-ab.getprosition()<=4&&bg.getnumber()!=0&&ab.getnumber()>=0){//判断是否能构成弓箭手打步兵
//                bg.setprosition(bg.getprosition()-1);//弓箭手停滞运动
//                ab.setnumber(ab.getnumber()-bg.getnumber());//步兵减员
//
//            }
//            if (ab.getnumber()<=0){//如果步兵死亡
//                ab.setnumber(0);
//                ab.setprosition(ab.getprosition()+1);
//                if (ag.getprosition()-bg.getprosition()<=4&&bg.getnumber()>=0&&ag.getnumber()>=0){//如果弓箭手能相互打架
//                    int c=bg.getnumber();
//                    bg.setnumber(c-ag.getnumber());
//                    ag.setnumber(ag.getnumber()-c);//双方减员
//                }else {//弓箭手前行
//                    ag.setprosition(ag.getprosition()+1);
//                }
//
//            }
//
//
//
//            ab.setprosition(ab.getprosition() + 1);
//            ag.setprosition(ag.getprosition() + 1);
//            bb.setprosition(bb.getprosition() - 1);
//            bg.setprosition(bg.getprosition() - 1);
////            if (bg.getprosition()-ab.getprosition()<=4){
////                bg.setprosition(bg.getprosition()+1);
////                ab.setnumber(ab.getnumber()-bg.getnumber());
////                if (ab.getnumber()==0){
////                    if (bg.getprosition()-ag.getprosition()<=4){//如果弓箭手能相互打架
////                        int c=bg.getnumber();
////                        bg.setnumber(c-ag.getnumber());
////                        ag.setnumber(ag.getnumber()-c);//双方减员
////                    }else {//弓箭手前行
////                        ag.setprosition(ag.getprosition()+1);
////                    }
////                }
////            }
//             jieguo=jieguo+(i+1)+"   "+ab.getnumber()+"   "+ab.getprosition()+"   "+ag.getnumber()+"   "+ag.getprosition()+"   "+bb.getnumber()+"   "+bb.getprosition()+"   "+bg.getnumber()+"   "+bg.getprosition()+"\n";
//
//            if (ab.getnumber()<=0&&ag.getnumber()<=0){
//                break;
//            }
//            if (bb.getnumber()<=0&&bg.getnumber()<=0){
//                break;
//            }
//
//
//        }
//
//
//
//        return jieguo;
//    }
//
//    public String chuanshu(){
//        return jieguo;
//
//
//    }
//    private double abs(double i){
//        if (i<0) {
//            return  10 + i;
//        }else return i;
//    }
//
//    public static void main(String[] args) {
//        bubing a1=new bubing();
//        a1.setprosition(0);
//        a1.setnumber(10);
//        gongjian a2=new gongjian();
//        a2.setprosition(0);
//        a2.setnumber(10);
//        bubing a3=new bubing();
//        a3.setprosition(10);
//        a3.setnumber(10);
//        gongjian a4=new gongjian();
//        a4.setprosition(10);
//        a4.setnumber(10);
//        PKsuanfa a=new PKsuanfa(a1,a2,a3,a4);
////        a.init();
////        String
//        System.out.println(a.jisuan());
////        System.out.println();
//////        System.out.println(a.jieguo);
////        System.out.println((double) 3/4);
//
//    }
}
