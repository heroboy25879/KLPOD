package com.hitachi.com.klpod.Utility;

public class MasterServiceFunction {
    private String urlService = "http://172.23.191.13/KLwebservice/";
    private String methodService = "MethodService.svc";
    //about url web service
    private String GetUserLogin = urlService + methodService + "/GetUserLogin";

    public String getGetUserLogin() {
        return GetUserLogin;
    }
}
