package com.hirvorn.qtb_test.Enum;

/**
 * Created by laboratorio on 19/09/2016.
 */
public enum VolumeDiVolo {
    V70("V70"),
    V150("V150");

    private String volume;

    private VolumeDiVolo(String volume){
        this.volume = volume;
    }

    @Override public String toString(){
        return volume;
    }
}
