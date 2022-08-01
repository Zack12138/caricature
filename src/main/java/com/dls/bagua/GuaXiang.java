package com.dls.bagua;

public enum GuaXiang {

    upup("上上卦",0),
    up("上卦",0),
    midup("中上卦",0),
    midmid("中中卦",0),
    middown("中下卦",0),
    down("下卦",0),
    downdown("下下卦",0);

    private String name;
    private int index;

    GuaXiang(String name, int index){
        this.name = name;
        this.index = index;
    }

}
