package com.flotte.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyResponse {
    private String profil;
    private List<Map<String, String>> error = new ArrayList<>();
    private Object data = null;

    public List<Map<String, String>> getError() {
        return error;
    }

    public void setProfil(String p){
        this.profil = p;
    }

    public String getProfil(){
        return this.profil;
    }

    public void setError(List<Map<String, String>> error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void addError(String status, String errorMessage) {
        Map<String, String> newError = new HashMap<>();
        newError.put("status", status);
        newError.put("message", errorMessage);
        error.add(newError);
    }
}

