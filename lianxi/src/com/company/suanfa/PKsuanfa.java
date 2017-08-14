package com.company.suanfa;

/**
 * Created by weicong on 17-8-5.
 */
public  class  PKsuanfa {
    private int ab;
    private int ag;
    private int bb;
    private int bg;
    bubing a1=new bubing();
    gongjian a2=new gongjian();
    bubing b1=new bubing();
    gongjian b2=new gongjian();
    private String jieguo;
    public  PKsuanfa(int a,int b,int c,int d){
        this.ab=a;
        this.ag=b;
        this.bb=c;
        this.bg=d;
        init();
        da();
    }
    private void init(){
        a1.setnumber(ab);
        a2.setnumber(ag);
        a1.setprosition(-1);
        a2.setprosition(-1);
        b1.setnumber(bb);
        b2.setnumber(bg);
        b1.setprosition(11);
        b2.setprosition(11);
    }
    private  void da(){
        for(int i=0;i<10;i++){
            if (a1.getnumber()>0){
            a1.setprosition(abs(a1.getprosition()+1));}
            if(a2.getnumber()>0){
            a2.setprosition(abs(a2.getprosition()+1));}
            if (b1.getnumber()>0){
            b1.setprosition(abs(b1.getprosition()-1));}
            if(b2.getnumber()>0){
            b2.setprosition(abs(b2.getprosition()-1));}
            if(b2.getprosition()-a1.getprosition()<4&&a1.getnumber()>0){
                a1.setnumber(a1.getnumber()-b2.getnumber());
                b2.setprosition(b2.getprosition()+1);
            }
            if(b1.getprosition()-a2.getprosition()<4&&b1.getnumber()>0){
                b1.setnumber(b1.getnumber()-a2.getnumber());
                a2.setprosition(a2.getprosition()+1);
            }
            if(b2.getprosition()-a2.getprosition()<=4&&a1.getnumber()==0&&b1.getnumber()==0){
                if(b2.getnumber()>a2.getnumber()){
                    b2.setnumber(b2.getnumber()-a2.getnumber());
                    a2.setnumber(0);
                }else {
//                if(b2.getnumber()<a2.getnumber()){
                    a2.setnumber(a2.getnumber()-b2.getnumber());
                    b2.setnumber(0);
                }
            }
            if(a1.getprosition()==b1.getprosition()){
                if(a1.getnumber()>b1.getnumber()){
                    a1.setnumber(a1.getnumber()-b1.getnumber());
                    b1.setnumber(0);
                    a1.setprosition(a1.getprosition()-1);
                }
            }
            jieguo=jieguo+"#"+i+"|"+a1.getprosition()+"|"+a1.getnumber()+"|"+a2.getprosition()+"|"+
                    a2.getnumber()+"|"+b1.getprosition()+"|"+b1.getnumber()+"|"+b2.getprosition()+"|"+
                    b2.getnumber();

            System.out.println(i+"     "+a1.getprosition()+"   "+a1.getnumber()+"   "+a2.getprosition()+"   "+
                    a2.getnumber()+"   "+b1.getprosition()+"   "+b1.getnumber()+"   "+b2.getprosition()+"   "+
                    b2.getnumber());
            if (a1.getnumber()<=0&&a2.getnumber()<=0){
                break;
            }
            if(b1.getnumber()<=0&&b2.getnumber()<=0){
                break;
            }
        }
    }
    public String chebing(){
        a1.setnumber(ab);
        a2.setnumber(ag);
        a1.setprosition(-1);
        a2.setprosition(-1);
        b1.setnumber(bb);
        b2.setnumber(bg);
        b1.setprosition(11);
        b2.setprosition(11);


        return jieguo;
    }

    public String chuanshu(){
        return jieguo;


    }
    private double abs(double i){
        if (i<0) {
            return  10 + i;
        }else return i;
    }

    public static void main(String[] args) {
        PKsuanfa a=new PKsuanfa(20,0,10,0);
//        a.init();
//        String

        System.out.println();
//        System.out.println(a.jieguo);
        System.out.println((double) 3/4);

    }
}
