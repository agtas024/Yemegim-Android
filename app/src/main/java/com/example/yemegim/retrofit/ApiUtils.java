package com.example.yemegim.retrofit;

public class ApiUtils {
    public static final String BASE_URL = "http://kasimadalan.pe.hu/yemekler/";

    public static YemeklerDaoInterface getYemeklerDaoInterface(){
        return RetrofitClient.getClient(BASE_URL).create(YemeklerDaoInterface.class);
    }
}
