/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.golfkids.util;

import java.util.Date;

/**
 *
 * @author aubreyM
 */
public class TesterClass {
    
    public static void main (String[] args) {
        
        Student s = new Student();
        s.setBirthDate(new Date());
        s.setFirstName("Obby");
        s.setLastName("Mathloko");
        s.setEmail("ygkyjh@gmail.com");
        s.setStudentNumber(56789765);
        
        System.out.println("The object Student: " + s.getFirstName()
        + " " + s.getLastName()+ " StudentNumber; " + s.getStudentNumber());
    }
}
