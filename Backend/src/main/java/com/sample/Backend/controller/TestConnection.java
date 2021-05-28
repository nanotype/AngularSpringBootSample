/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.Backend.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.sample.Backend.service.TransfertElements;
import io.swagger.annotations.Api;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Victor
 */
@Api
@RestController
@RequestMapping("/")
public class TestConnection {
    
    String json = "{\"id\":1, \"name\":\"ProductName\", \"images\":[\"id\":11, \"imageName\":\"xCh-rhy\"}, {\"id\":31, \"imageName\":\"fjs-eun\"}]}";
    
    @Autowired
    private TransfertElements transfertElements;
    
    @GetMapping(value = "/status")
    public ResponseEntity<String> pong() 
    {
        return new ResponseEntity<String>("Réponse du serveur: "+HttpStatus.OK.name(), HttpStatus.OK);
    }
    
    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld(){
        return new ResponseEntity<String>("Hello World !!!", HttpStatus.OK);
    }
    
    @GetMapping("/envoiString")
    public ResponseEntity<String> testEnvoiString() throws URISyntaxException{
        RestTemplate restTemplate = new RestTemplate();
        String body = "[{\"id\":1,\"first_name\":\"Antone\",\"last_name\":\"Hartle\",\"email\":\"ahartle0@samsung.com\",\"gender\":\"Agender\",\"ip_address\":\"214.226.189.172\"}]";
        
        URI uri = new URI("http://localhost:8080/receptionString");
        return restTemplate.postForEntity(uri, body, String.class);
    }
    
    @GetMapping("/envoiJson")
    public ResponseEntity<String> testEnvoiJson() throws URISyntaxException{
        RestTemplate restTemplate = new RestTemplate();
        //String body = "[{\"id\":1,\"first_name\":\"Antone\",\"last_name\":\"Hartle\",\"email\":\"ahartle0@samsung.com\",\"gender\":\"Agender\",\"ip_address\":\"214.226.189.172\"}]";
        String body = "{\"id\":1, \"name\":\"ProductName\", \"images\":[\"id\":11, \"imageName\":\"xCh-rhy\"}, {\"id\":31, \"imageName\":\"fjs-eun\"}]}";
        
        Gson gson = new Gson();
        JsonArray json = gson.fromJson(body, JsonArray.class);
        
        URI uri = new URI("http://localhost:8080/receptionJson");
        return restTemplate.postForEntity(uri, json, String.class);
    }
    
    @PostMapping("/receptionString")
    public ResponseEntity<String> testReceptionString(@RequestBody String testString){
        System.out.println(testString);
        return new ResponseEntity<String>("j'ai lu : " + testString, HttpStatus.OK);
    }
    
    @PostMapping("/receptionJson")
    public ResponseEntity<String> testReceptionJson(@RequestBody JsonArray testJson){
        System.out.println(testJson.toString());
        return new ResponseEntity<String>("j'ai lu : " + testJson.toString(), HttpStatus.OK);
    }
    
    @GetMapping("/testMultiPass")
    public ResponseEntity<String> testMultiplePassingJson() throws URISyntaxException{
        String result = "";
        
        HashMap<String, String> map = new HashMap<>();
        map.put("erreur", "ceci est une bétise");
        map.put("betise2", "je suis une langouste");
        
        result += transfertElements.passIntegerInteger(12, 24);
        /*result += transfertElements.passStringString("test", "blmabla");*/
//        result += transfertElements.passStringMap(json, map);
        
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
