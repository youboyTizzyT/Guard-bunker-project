package com.company.suanfa;

/**
 * Created by weicong on 17-8-9.
 */
public class AlgorithmComputingDistance {//计算距离算法
    double x1;
    double x2;
    double y1;
    double y2;
    public AlgorithmComputingDistance(double x1,double y1,double x2,double y2){
//        aaa(x1,y1,x2,y2);
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        Exeception(x1,y1,x2,y2);
    }

    public static void main(String[] args) {
        AlgorithmComputingDistance algorithmComputingDistance=new AlgorithmComputingDistance(1,1,1,1);
    }
    private void Exeception (double a,double b,double c,double d){
        if(a==c||b==d) {
            //改进
            x1=0.0;
            x2=0.0;
            y1=0.0;
            y2=0.0;
        }
    }
    public Double aaa(){
        //改进
        Double b;
        b=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)+(y1-y2));

        return b;

    }
    public boolean judge(Double a){
        if (a>100){
            return false;
        }else {
            return true;
        }
    }
}
