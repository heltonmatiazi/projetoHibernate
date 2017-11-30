/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author helton
 */
public class Gerador {
     public static String gerarCNPJ() {
        return null;
    }
    
    public static String randomString() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        int index;
        String result = "";
        for (int i = 0; i < 15; i++) {
            index = (int) (Math.random() * 26);
            char letter = letters.charAt(index);
            result += letter;
        } 
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(Gerador.randomString());
    }
}
