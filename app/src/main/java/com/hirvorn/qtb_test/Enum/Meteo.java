package com.hirvorn.qtb_test.Enum;

/**
 * Created by laboratorio on 19/09/2016.
 */
public enum Meteo {
    SERENO("sereno"),
    POCO_NUVOLOSO("poco nuvoloso"),
    NUVOLOSO("nuvoloso");

    private String meteo;

    private Meteo(String meteo){
        this.meteo = meteo;
    }

    @Override public String toString(){
        return meteo;
    }
}
