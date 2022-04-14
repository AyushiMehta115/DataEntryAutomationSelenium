package com.hackerrank.selenium;

public class DuplicateElements {

    public static void main(String[] args) {
        String str = "anasdsdssd";
        int[] num = new int[str.length()];
        int i, j;

        //Converts given string into character array
        char string[] = str.toCharArray();

        for(i = 0; i <str.length(); i++) {
            num[i] = 1;
            for(j = i+1; j <str.length(); j++) {
                if(string[i] == string[j]) {
                    num[i]++;

                    //Set string[j] to 0 to avoid printing visited character
                    string[j] = '0';
                }
            }
        }

        //Displays the each character and their corresponding numuency
        System.out.println("Characters and their corresponding numuencies");
       for(i = 0; i <num.length; i++) {
            if(string[i] != ' ' && string[i] != '0')
           System.out.println(string[i] + "-" + num[i]);
       }
    }
//        char[] strArr;
//
//        while (str.length() != 0) {
//            strArr = str.toCharArray();
//            char ch = strArr[0];
//            int count = 1;
//            for (int i = 1; i < strArr.length; i++) {
//                if (ch == strArr[i]) {
//                    count++;
//                }
//            }
//            if (((ch != ' ') && (ch != '\t'))) {
//                System.out.println(ch + " - " + count);
//            }
//            str = str.replace("" + ch, "");
//        }
    }





