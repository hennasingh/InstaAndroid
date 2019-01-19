package com.coder.instaandroid.network;

public class WebRepository {

    private static volatile WebRepository sWebRepository;

    private WebRepository(){}

    public static WebRepository getInstance(){
        if(sWebRepository==null){
            sWebRepository = new WebRepository();
        }
        return sWebRepository;
    }
}
