package at.ac.fhcampuswien;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    public boolean checkPassword(String password){
        char charArray[]=password.toCharArray();
        int countCapitalLetters=0;
        int countSmallLetters=0;
        int countNumbers=0;
        int countOtherSymbols=0;
        int countConsequentNumbers=0; // variable>0 if there are more than 2 consequent numbers.
        int countEqualNumbers=0; //variable>0 if there are more than 3 consequent equal numbers.
        if(password.length()>8 & password.length()<25){
            for (int i=0; i<charArray.length;i++){
                // test small letters (ASCII Code)
                if (charArray[i]>96 & charArray[i]<123){
                    countSmallLetters++;
                }
                //test capital letters
                else if(charArray[i]>64 & charArray[i]<91){
                    countCapitalLetters++;
                }
                //test numbers
                else if(charArray[i]>47 & charArray[i]<58){
                    //check consequent numbers
                    if(i<=charArray.length-3){
                        if( (charArray[i+1] == charArray[i] + 1 & charArray[i+1] < 57) & (charArray[i+2] == charArray[i+1] + 1)){
                            countConsequentNumbers++;
                        }
                    }
                    //check consequent numbers that are equal
                    if(i<=charArray.length-4){
                        if ( (charArray[i+1] == charArray[i]) & (charArray[i+2] == charArray[i]) & (charArray[i+3] == charArray[i])){
                            countEqualNumbers++;
                        }
                    }
                    countNumbers++;
                }
                //test special symbols
                else if(charArray[i]=='(' || charArray[i]==')' || charArray[i]=='#' || charArray[i]=='?' || charArray[i]=='!' || charArray[i]=='%' || charArray[i]=='/' || charArray[i]=='@'){
                    countOtherSymbols++;
                }
            }

            if(countCapitalLetters>0 & countSmallLetters>0){
                if(countNumbers>0){
                    if(countOtherSymbols>0){
                        if(countConsequentNumbers==0){
                            if(countEqualNumbers==0){
                                return true;
                            }
                        }
                    }
                }
            }
             //Password must consist of both capital and small letters

        }
        return false;
    }
    @Test
    @DisplayName("Length is less than 8")
    public void testLength1(){
        assertFalse(checkPassword("a"));
    }
    @Test
    @DisplayName("Length is more than 25")
    public void testLength2(){
        assertFalse(checkPassword("aaaaaaaaaaaaaaaaaaaaaaaaaa")); //number of "a"`s is 26
    }
    @Test
    @DisplayName("Length is in bounds")
    public void testLength3(){
        assertTrue(checkPassword("Incredible1("));
    }
    @Test
    @DisplayName("Password without capital letters")
    public void testCapitals(){
        assertFalse(checkPassword("fantastic"));
    }

    @Test
    @DisplayName("Password without small letters")
    public void testSmallLetters(){
        assertFalse(checkPassword("FANTASTIC"));
    }

    @Test
    @DisplayName("Password with  both capital and small letters")
    public void testAllLetters(){
        assertTrue(checkPassword("Fantastic1("));
    }

    @Test
    @DisplayName("Password without numbers")
    public void testNumbers(){
        assertFalse(checkPassword("Fantastic"));
    }

    @Test
    @DisplayName("Password without other Symbols")
    public void testSpecialSymbols(){
        assertFalse(checkPassword("Fantastic1"));
    }

    @Test
    @DisplayName("Password with consequent numbers")
    public void testConsequentNumbers1(){
        assertFalse(checkPassword("Fantastic123("));
    }

    @Test
    @DisplayName("Password without consequent numbers (maximal amount allowed)")
    public void testConsequentNumbers2(){
        assertTrue(checkPassword("Fantastic12("));
    }

    @Test
    @DisplayName("Password with equal numbers")
    public void testEqualNumbers1(){
        assertFalse(checkPassword("Fantastic1111("));
    }

    @Test
    @DisplayName("Password without equal numbers (max amount allowed)")
    public void testEqualNumbers2(){
        assertTrue(checkPassword("Fantastic111("));
    }

    @Test
    @DisplayName("Testing random passwords 1")
    public void testRandomPassword1(){
        assertTrue(checkPassword("AbobaBigBrain1()"));
    }

    @Test
    @DisplayName("Testing random passwords 2")
    public void testRandomPassword2(){
        assertFalse(checkPassword("IdontCareFor1111)"));
    }

    @Test
    @DisplayName("Testing random passwords 3")
    public void testRandomPassword3(){
        assertFalse(checkPassword("IdontCareFor11123)"));
    }

    @Test
    @DisplayName("Testing random passwords 4")
    public void testRandomPassword4(){
        assertTrue(checkPassword("WhatIf1212124@"));
    }
}
