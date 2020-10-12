/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp37.bc37consume.entities;

import lombok.Data;

/**
 *
 * @author aqira
 */
@Data
public class Person {

    private String id, name, email, gender;
    private int age;

}
