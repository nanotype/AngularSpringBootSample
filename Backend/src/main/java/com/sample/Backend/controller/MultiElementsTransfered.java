/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.Backend.controller;

import io.swagger.annotations.Api;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Victor
 */
@Api
@RestController
@RequestMapping("/multi")
public class MultiElementsTransfered {
    
    @PostMapping("/intInt")
    public ResponseEntity<String> integerAndInteger(@RequestParam("int1") int int1, @RequestParam("int2") int int2){
        return new ResponseEntity<>("Je lis : " + int1 + " et " + int2 + '\n', HttpStatus.OK);
    }
    
    @PostMapping("/StringString")
    public ResponseEntity<String> StringAndString(@RequestParam String str1, @RequestParam String str2){
        return new ResponseEntity<>("Je lis : " + str1 + " et " + str2 + '\n', HttpStatus.OK);
    }
    
    @PostMapping("/StringMap")
    public ResponseEntity<String> stringAndMap(@RequestBody MultiValueMap<String, String> body){
        String json = body.getFirst("json");
        String map = body.getFirst("map");
        return new ResponseEntity<>("je voit " + json + " et " + map.toString(), HttpStatus.OK);
    }
}
