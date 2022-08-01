package com.dls.bagua;


import static com.dls.bagua.BaGua.*;
import static com.dls.bagua.GuaXiang.*;

public enum GuaXiang_64 {

    gua_1(qian, qian,"乾为天", "刚健中正", upup,"象曰：困龙得水好运交，不由喜气上眉梢，一切谋望皆如意，向后时运渐渐高。这个卦是同卦（下乾上乾）相叠。象征天，喻龙（德才的君子），又象征纯粹的阳和健，表明兴盛强健。乾卦是根据万物变通的道理，以“元、亨、利、贞”为卦辞，示吉祥如意，教导人遵守天道的德行。"),
    gua_2(li, kan,"坤为地","", upup,"象曰：肥羊失群入山岗，饿虎逢之把口张，适口充肠心欢喜，卦若占之大吉昌。这个卦是同卦（下坤上坤）相叠，阴性。象征地（与乾卦相反），顺从天。承载万物，伸展无穷无尽。坤卦以雌马为象征，表明地道生育抚养万物，而又依天顺时，性情温顺。它以“先迷后得”证明“坤”顺从“乾”，依随“乾”，才能把握正确方向，遵循正道，获取吉利。"),
    gua_3(li, kan,"","", middown,""),
    gua_4(li, kan,"","", middown,""),
    gua_5(li, kan,"","", middown,"");



    private BaGua up;

    private BaGua down;


    private String name;


    private String desc;

    private GuaXiang guaXiang;

    /**
     * 解卦
     */
    private String jieGua;


    GuaXiang_64(BaGua up, BaGua down, String name, String desc, GuaXiang guaxiang, String jiegua){
        this.up =up;
        this.down =down;
        this.name =name;
        this.desc =desc;
        this.guaXiang =guaxiang;
        this.jieGua =jiegua;
    }

}
