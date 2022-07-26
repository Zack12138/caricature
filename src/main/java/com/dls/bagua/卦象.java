package com.dls.bagua;

public enum 卦象 {

    上上卦("上上卦",0),
    上卦("中下卦",0),
    中上卦("中下卦",0),
    中中卦("中下卦",0),
    中下卦("中下卦",0),
    下卦("中下卦",0),
    下下卦("中下卦",0);

    private String name;
    private int index;

    卦象(String name,int index){
        this.name = name;
        this.index = index;
    }

}
