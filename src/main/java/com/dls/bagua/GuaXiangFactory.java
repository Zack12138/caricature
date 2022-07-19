package com.dls.bagua;

import com.dls.util.SingleFactory;

public class GuaXiangFactory extends SingleFactory<GuaXiangFactory> {

    private GuaXiangFactory(){}



    protected static SingleFactory init() {
        return new GuaXiangFactory();
    }

    public static GuaXiangFactory getInstance(){
        return (GuaXiangFactory) SingleFactory.getInstance().getFactory(GuaXiangFactory.class);
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }
}
