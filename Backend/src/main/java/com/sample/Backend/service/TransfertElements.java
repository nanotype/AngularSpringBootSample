/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.Backend.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author Victor
 */
@Service
public class TransfertElements {
    
    public String passIntegerInteger(int int1, int int2) throws URISyntaxException{
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("int1", int1);
        bodyBuilder.part("int2", int2);
        
        URI uri = new URI("http://localhost:8080/multi/intInt");
        String response = WebClient.builder().build().post()
                .uri(uri)
                .bodyValue(bodyBuilder.build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
    
    public String passStringString(String str1, String str2) throws URISyntaxException{
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("str1", str1);
        bodyBuilder.part("str2", str2);
        
        URI uri = new URI("http://localhost:8080/multi/StringString");
        String response = WebClient.builder().build().post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .bodyValue(bodyBuilder.build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
    
    public String passStringMap(String json, HashMap<String, String> map) throws URISyntaxException{
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("json", json);
        bodyBuilder.part("map", map);
        
        URI uri = new URI("http://localhost:8080/multi/stringAndMap");
        String response = WebClient.builder().build().post()
                .uri(uri)
                .bodyValue(bodyBuilder.build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}
