package com.company.suanfa;

/**
 * Created by weicong on 17-8-14.
 */
public class julipaixu {
    private double lng1 = 0;
    private double lng2 = 0;
    private double lat1 = 0;
    private double lat2 = 0;
    private double EARTH_RADIUS = 6378.137;

    public julipaixu(double j1, double w1, double j2, double w2) {//lat1|lat2|lng1|lng2
        this.lng1 = j1;
        this.lat1 = w1;
        this.lng2 = j2;
        this.lat2 = w2;
        getDistance(lng1, lat1, lng2, lat2);
    }

    public  double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double juli = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        juli = juli * 6378137.0;
        juli = Math.round(juli * 10000) / 10000;
        return juli;
    }


}

