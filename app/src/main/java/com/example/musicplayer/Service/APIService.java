package com.example.musicplayer.Service;

public class APIService {
    private static String base_url= "https://musicplayeruit.000webhostapp.com/Server/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
