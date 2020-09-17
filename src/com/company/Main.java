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

	    char[][] ssArr = output, msArr = output;
	    for (int i = 0; i < 10; ++i) {
            selectionSort(ssArr[i],10); // testing selection sort
            String str = Arrays.toString(ssArr[i])+" ";
            System.out.print(str);
        }

        System.out.println();
        for (int i = 0; i < 10; ++i){
            mergeSort(ssArr[i],0,9);
            String str = Arrays.toString(msArr[i])+" ";
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
    public static void selectionSort(char[] arr, int k){
        int min;


        // loop through string and sort the characters
        for (int i = 0; i < k-1; ++i) {
            min = i; // set the index we are trying to sort to the minimum
            // look for the minimum char in the string
            for (int j = i + 1; j < k; ++j) {
                if (arr[j] < arr[min]) {
                    min = j; // if a smaller char is found, store the index
                }
            }

            // once the smallest element has been found, move it to index j
            swap(arr, min, i);
        }
    }

    public static void mergeSort(char[] arr, int left, int right){
        if (left < right){
            int middle = left + (right - left)/2; // locate the middle index of current list

            mergeSort(arr,left,middle); // merge sorts the left half of current list
            mergeSort(arr,middle+1,right); // merge sorts right half of current list

            //merging lists back together
            merge(arr,left, middle, right);

        }
    }
    public static void merge(char[] arr, int left, int middle, int right){
        // determine the sizes of the left and right halves of array section
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        // create new arrays to hold the left and right halves of array section
        char[] leftArr = new char[leftSize];
        char[] rightArr = new char[rightSize];

        // populate the arrays
        for (int i = 0; i < leftSize; ++i){
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < rightSize; ++j){
            rightArr[j] = arr[middle + 1 + j];
        }

        int i = 0, j = 0, k = left;
        // loop through the left and right arrays to sort this section of the array
        while (i < leftSize && j < rightSize){
            if (leftArr[i] <= rightArr[j]){ // if the current left array element is smaller than right array element, place in smallest available spot in overall array
                arr[k] = leftArr[i];
                i++;
            }
            else{ // if not, place right array element into smallest available spot in overall array
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < leftSize){ // if any leftover elements in left array, put them in overall array
            arr[k] = leftArr[i];
            ++i;
            ++k;
        }
        while (j < rightSize){ // if any leftover elements in right array, put them in overall array
            arr[k] = rightArr[j];
            ++j;
            ++k;
        }
    }


    public static void swap(char[] arr, int a, int b){
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
