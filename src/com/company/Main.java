package com.company;

import java.util.Random;
import java.util.Arrays;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
	    char[][] output;
	    //VISUAL TESTING
        /*
	    output = generateTestList(10,10,65,90); // testing generation
        System.out.println("UNSORTED LIST: ");
	    for (int i = 0; i < 10; ++i){
	        String str = Arrays.toString(output[i])+" ";
	        System.out.print(str);
        }

	    char[][] ssArr = output.clone(), msArr = output.clone(), qsArr = output.clone(), rsArr = output.clone(),
                 rs2Arr = output.clone(), rs3Arr = output.clone();
        System.out.println("\nSELECTION SORT: ");
	    selectionSort(ssArr,10); // testing selection sort
        for (int i = 0; i < 10; ++i) {
            String str = Arrays.toString(ssArr[i]) + " ";
            System.out.print(str);
        }

        System.out.println("\nMERGE SORT: ");
        mergeSort(msArr,0,9,10);
        for (int i = 0; i < 10; ++i) {
            String str = Arrays.toString(msArr[i]) + " ";
            System.out.print(str);
        }

        System.out.println("\nQUICK SORT: ");
        quickSort(qsArr,0,9);
        for (int i = 0; i < 10; ++i){
            String str = Arrays.toString(qsArr[i]) + " ";
            System.out.print(str);
        }

        System.out.println("\nRADIX-1 SORT: ");
        radixSort(rsArr,10,10, 1);
        for (int i = 0; i < 10; ++i){
            String str = Arrays.toString(rsArr[i]) + " ";
            System.out.print(str);
        }

        System.out.println("\nRADIX-2 SORT: ");
        radixSort(rs2Arr,10,10, 2);
        for (int i = 0; i < 10; ++i){
            String str = Arrays.toString(rs2Arr[i]) + " ";
            System.out.print(str);
        }

        System.out.println("\nRADIX-3 SORT: ");
        radixSort(rs3Arr,10,10, 3);
        for (int i = 0; i < 10; ++i){
            String str = Arrays.toString(rs3Arr[i]) + " ";
            System.out.print(str);
        }*/

        // isSorted testing
        int N = 10000;
        int k = 16;
        char[][] array;
        System.out.printf("Generating list of size %d with key size %d...\n", N, k);
        array = generateTestList(N,k,1,255);
        char[][] ssArr = array.clone(), msArr = array.clone(), qsArr = array.clone(), rsArr = array.clone(), rs2Arr = array.clone(), rs3Arr = array.clone();
        //Selection sort
        System.out.println("Sorting with selection sort");
        selectionSort(ssArr,N);
        System.out.print("Verifying... ");
        if (isSorted(ssArr)){
            System.out.println("Sorted!");
        }else{
            System.out.println("Not sorted :(");
        }
        //Merge sort
        System.out.println("Sorting with merge sort");
        mergeSort(msArr,0,N-1,k);
        System.out.print("Verifying... ");
        if (isSorted(msArr)){
            System.out.println("Sorted!");
        }else{
            System.out.println("Not sorted :(");
        }

        //Quick sort
        System.out.println("Sorting with quick sort");
        quickSort(qsArr,0,N-1);
        System.out.print("Verifying... ");
        if (isSorted(qsArr)){
            System.out.println("Sorted!");
        }else{
            System.out.println("Not sorted :(");
        }

        //Radix-1 sort
        System.out.println("Sorting with radix-1 sort");
        radixSort(rsArr, N, k, 1);
        System.out.print("Verifying... ");
        if (isSorted(rsArr)){
            System.out.println("Sorted!");
        }else{
            System.out.println("Not sorted :(");
        }

        //Radix-2 sort
        System.out.println("Sorting with radix-2 sort");
        radixSort(rsArr, N, k, 2);
        System.out.print("Verifying... ");
        if (isSorted(rsArr)){
            System.out.println("Sorted!");
        }else{
            System.out.println("Not sorted :(");
        }

        //Radix-3 sort
        System.out.println("Sorting with radix-3 sort");
        radixSort(rsArr, N, k, 3);
        System.out.print("Verifying... ");
        if (isSorted(rsArr)){
            System.out.println("Sorted!");
        }else{
            System.out.println("Not sorted :(");
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
    public static void selectionSort(char[][] arr, int N){
        int min;

        // loop through list to sort them alphabetically
        for (int i = 0; i < N-1; ++i){
            min = i; // set the index we are trying to sort to the minimum

            for (int j = i +1; j < N; ++j){
                String str = Arrays.toString(arr[j]), minStr = Arrays.toString(arr[min]); // convert the character arrays to strings to compare
                if (str.compareTo(minStr) < 0){ // if current string is less than the minimum, store the index of it
                    min = j;
                }
            }

            swap(arr,min,i); // put strings in correct order
        }
    }

    public static void mergeSort(char[][] arr, int left, int right, int k){
        if (left < right){
            int middle = left + (right - left)/2; // locate the middle index of current list

            mergeSort(arr,left,middle,k); // merge sorts the left half of current list
            mergeSort(arr,middle+1,right,k); // merge sorts right half of current list

            //merging lists back together
            merge(arr,left, middle, right,k);

        }
    }
    public static void merge(char[][] arr, int left, int middle, int right, int k){
        // determine the sizes of the left and right halves of array section
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        // create new arrays to hold the left and right halves of array section
        char[][] leftArr = new char[leftSize][k];
        char[][] rightArr = new char[rightSize][k];

        // populate the arrays
        for (int i = 0; i < leftSize; ++i){
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < rightSize; ++j){
            rightArr[j] = arr[middle + 1 + j];
        }

        int i = 0, j = 0, x = left;
        // loop through the left and right arrays to sort this section of the array
        while (i < leftSize && j < rightSize){
            String leftStr = Arrays.toString(leftArr[i]), rightStr = Arrays.toString(rightArr[j]);
            if (leftStr.compareTo(rightStr) <= 0){ // if the current left array string is smaller than right array element, place in smallest available spot in overall array
                arr[x] = leftArr[i];
                i++;
            }
            else{ // if not, place right array string into smallest available spot in overall array
                arr[x] = rightArr[j];
                j++;
            }
            x++;
        }
        while (i < leftSize){ // if any leftover strings in left array, put them in overall array
            arr[x] = leftArr[i];
            ++i;
            ++x;
        }
        while (j < rightSize){ // if any leftover strings in right array, put them in overall array
            arr[x] = rightArr[j];
            ++j;
            ++x;
        }
    }

    public static void quickSort(char[][] arr, int low, int high){
        if (low < high){
            int index = quickSortHelper(arr, low, high); // picks a pivot element and places it in correct location in array

            quickSort(arr, low, index - 1); // quick sorts left half
            quickSort(arr, index + 1, high); // quick sorts right half
        }
    }

    public static int quickSortHelper(char[][] arr, int low, int high){
        char[] pivotPoint = arr[high]; // choose last string as pivot point
        int index = low - 1;

        // loop through to place pivot element in correct location
        for (int i = low; i <= high - 1; ++i){
            String str = Arrays.toString(arr[i]), pivot = Arrays.toString(pivotPoint);
            if (str.compareTo(pivot) < 0){ // if this is smaller than the pivot, place it before the pivot point
                index++; // move to next available location before pivot
                swap(arr,index,i); // place element before pivot
            }
        }
        swap(arr, index + 1, high); // places pivot point in correct location to allow for elements greater to be sorted correctly

        return index+1;
    }


    /*
    * Parts of this function is taken from algs4.cs.princeton.edu/51radix/LSD.java.html
    * I modified it to be compatible with different key segment sizes
    * */
    public static void radixSort(char[][] arr, int N, int k, int d){

        long chars = (long)Math.pow(2,8*d); // this determines how many possible values for char representation
        char[][] output = new char[N][k];


        for (int a = (k-1)/d; a >= 0; a--){
            int[] freq = new int[(int)chars+1];

            // loop through list of strings and count frequency of how many times a key segment appears
            for (int i = 0; i < N; i++){
                int s = 0; // this will store the int representation of the key segment
                for (int j = d-1; j >= 0; j--){
                    int x = 0;
                    s += (int)Math.pow(2,8*j) * arr[i][a+x]; // this calculate the int representation of the key segment
                    ++x;
                }
                freq[s+1]++; // increment a key segment when it appears
            }

            // compute the cumulates -- this tells us where strings will be in the output
            for (int i = 0; i < chars; i++){
                freq[i+1] += freq[i];
            }

            // move the strings where they are supposed to be
            for (int i = 0; i < N; i++){
                int s = 0;
                for (int j = d-1; j >= 0; j--){
                    int x = 0;
                    s += (int)Math.pow(2,8*j) * arr[i][a+x]; // this calculate the int representation of the key segment
                    ++x;
                }
                output[freq[s]++] = arr[i];
            }

            // copy back to array
            for (int i = 0; i < N; ++i){
                arr[i] = output[i];
            }
        }

    }


    public static void swap(char[][] arr, int a, int b){
        char[] temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static boolean isSorted(char[][] arr){
        boolean result = true;

        for (int i = 0; i < arr.length - 1; ++i){
            String str1 = Arrays.toString(arr[i]), str2 = Arrays.toString(arr[i+1]);
            if (str1.compareTo(str2) > 0){
                result = false;
                return result;
            }
        }

        return result;
    }

}
