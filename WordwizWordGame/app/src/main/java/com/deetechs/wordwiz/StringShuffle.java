package com.deetechs.wordwiz;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.lang.Math;
import java.util.Random;
/**
 *
 * @author Benjamin
 */
public class StringShuffle {
    public  String shuffle(String s) {
        String shuffledString = "";
        while (s.length() != 0) {
         int index = (int) Math.floor(generateRand() * s.length());
           char c = s.charAt(index);
            s = s.substring(0, index) + s.substring(index + 1);
            shuffledString += c;            
        }
        return shuffledString;
    }
 public double generateRand(){
        Random rand = new Random();       
        return rand.nextDouble();       
    }
}
