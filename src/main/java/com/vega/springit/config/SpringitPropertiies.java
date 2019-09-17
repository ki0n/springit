package com.vega.springit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springid")
public class SpringitPropertiies {

    /**
     * this es our welcome message
     */
    private String welcomeMsg = "Hello, World";

    public String getWelcomeMsg(){
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg){
        this.welcomeMsg = welcomeMsg;
    }
}
