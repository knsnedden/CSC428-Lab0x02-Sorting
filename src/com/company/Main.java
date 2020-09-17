package com.company;

import java.util.Random;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	    char[][] output;
	    output = generateTestList(10,10,65,90); // testing generation
	    for (int i = 0; i < 10; ++i){
	        String str = Arrays.toString(output[i])+" ";
	        System.out.print(str);
        }
        System.out.println();

	    selectionSort(output,10,10); // testing selection sort
        for (int i = 0; i < 10; ++i){
            String str = Arrays.toString(output[i])+" ";
            System.out.print(str);
        }
    }

    // Generate array of strings to be sorted
    public static char[][] generateTestList(int N, int k, int minV, int maxV){
        char[][] arr = new char[N][k];
        Random randChar = new Random();
        char insert;

        // fill array with strings
        for (int i = 0; i < N; ++i){
            for (int j = 0; j < k; ++j){
                insert = (char)(randChar.nextInt(maxV-minV) + minV); // generate random character
                arr[i][j] = insert;
            }
        }

        return arr;
    }

    //Simple sort: Selection Sort
    public static void selectionSort(char[][] arr, int N, int k){
        int min;

        // THIS IS IS WE ARE SUPPOSED TO SORT THE STRINGS THEMSELVES
        // loop through entire array to sort
        for (int i = 0; i < N; ++i){
            // loop through each string and sort the characters
            for (int j = 0; j < k-1; ++j){
                min = j; // set the index we are trying to sort to the minimum
                // look for the minimum char in the string
                for (int x = j+1; x < k; ++x){
                    if (arr[i][x] < arr[i][min]){
                        min = x; // if a smaller char is found, store the index
                    }
                }

                // once the smallest element has been found, move it to index j
                swap(arr, min, j, i);
            }
        }
        // THIS IS IF WE ARE COMPARING STRINGS TO ONE ANOTHER -- unclear if this is what we need or if it's each string by element
      /*  for (int i = 0; i < N-1; ++i){
            min = i;
            for (int j = i+1; j < N; ++j){
                String minStr = Arrays.toString(arr[min]);
                String comp = Arrays.toString(arr[j]);
                if (comp.compareTo(minStr) < 0){
                    min = j;
                }
            }
            swapTwo(arr,min,i);
        }*/
    }

    public static void swap(char[][] arr, int a, int b, int i){
        char temp = arr[i][a];
        arr[i][a] = arr[i][b];
        arr[i][b] = temp;
    }
    /*public static void swapTwo(char[][] arr, int a, int b){
        char[] temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }*/

}
