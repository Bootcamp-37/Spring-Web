/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Deo Lahara
 */

@Controller
public class Sb2AdminController {
    
    @GetMapping("/tables")
    public String index(){
        return "tables";
    
    }
    
}
