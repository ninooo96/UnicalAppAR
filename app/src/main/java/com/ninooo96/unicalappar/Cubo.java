package com.ninooo96.unicalappar;

public class Cubo{
    int id;
    float inizioCubo;
    float fineCubo;

    public Cubo(float inizioCubo, float fineCubo,int id){
        this.inizioCubo = inizioCubo;
        this.fineCubo = fineCubo;
        this.id = id;
    }

    public float getInizioCubo() {
        return inizioCubo;
    }

    public float getFineCubo() {
        return fineCubo;
    }

    public int getId(){
        return id;
    }
}