/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rahmida.client2101082018.service;

import com.google.gson.Gson;
import com.rahmida.client2101082018.model.Buku;
import java.util.List;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

/**
 *
 * @author asus
 */
public class BukuService {
   private String URL = "http://localhost:9005";
    
    public Buku getBuku(Long bukuId){
        Buku buku = Unirest.get(URL+"/buku/"+bukuId)
                .asObject(Buku.class)
                .getBody();
        if(buku!=null){
            return buku;
        }
        return null;
    }
    
    public Buku saveBuku(Buku buku){
        HttpResponse<JsonNode> response = Unirest.post(URL + "/buku/")
                .header("content-type", "application/json")
                .body(buku)
                .asJson();
        Gson gson = new Gson();
        Buku a = gson.fromJson(response.getBody().toString(), Buku.class);
        return a;
    }
    
    public List<Buku> getAllBuku(){
        List<Buku> bukuList = Unirest.get(URL + "/buku/")
                .asObject(new GenericType<List<Buku>>(){})
                .getBody();
        return bukuList;
    }
    
    public Buku updateBuku(Buku buku){
        HttpResponse<JsonNode> response = Unirest.put(URL + "/buku/")
                .header("content-type", "application/json")
                .body(buku)
                .asJson();
        Gson gson = new Gson();
        Buku a = gson.fromJson(response.getBody().toString(), Buku.class);
        return a;
    }
    
    public void deleteBuku(Long bukuId){
        Unirest.delete(URL + "/buku/"+bukuId).asEmpty();
    }
}
