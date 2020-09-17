package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    char[][] output;
	    output = generateTestList(10,10,65,90); // testing generation
	    for (int i = 0; i < 10; ++i){
	        System.out.println(output[i]);
        }
    }

    public static char[][] generateTestList(int N, int k, int minV, int maxV){
        char[][] arr = new char[N][k+1];
        Random randChar = new Random();
        char insert;

        // fill array with strings
        for (int i = 0; i < N; ++i){
            for (int j = 0; j < k; ++j){
                insert = (char)(randChar.nextInt(maxV-minV) + minV); // generate random character
                arr[i][j] = insert;
            }
            arr[i][k] = 0; // end string with null byte
        }

        return arr;
    }
}
