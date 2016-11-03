package com.example.nicolas.gadgeothek.service;

/**
 * Created by nicolas on 03.11.16.
 */

public class Server
{
    public static String server;

    public Server(String additional) {
        this.server = "http://mge10.dev.ifs.hsr.ch/"+additional;
        //this.server = "http://10.0.2.2:8080/"+additional;
    }
    public Server() {
        this.server = "http://mge10.dev.ifs.hsr.ch";
        //this.server = "http://10.0.2.2:8080";
    }

}
