package com.hirvorn.qtb_test.Enum;

/**
 * Created by laboratorio on 19/09/2016.
 */
public enum Vento {
    CALMO("calmo"),
    BAVA_DI_VENTO("bava di vento"),
    BREZZA_LEGGERA("brezza leggera"),
    BREZZA("brezza"),
    BREZZA_VIVACE("brezza vivace"),
    BREZZA_TESA("brezza tesa"),
    VENTO_FRESCO("vento fresco"),
    VENTO_FORTE("vento forte");

    private String vento;

    private Vento(String vento){
        this.vento = vento;
    }

    @Override public String toString(){
        return vento;
    }
}
