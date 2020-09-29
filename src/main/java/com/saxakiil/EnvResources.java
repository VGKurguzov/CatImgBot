package com.saxakiil;

public class EnvResources {
    public static String urlApi;
    public static String tokenApi;
    public static String usernameBot;
    public static String tokenBot;

    static{
        EnvResources.urlApi = System.getenv("urlApi");
        EnvResources.tokenApi = System.getenv("tokenApi");
        EnvResources.usernameBot = System.getenv("usernameBot");
        EnvResources.tokenBot = System.getenv("tokenBot");
    }
}
