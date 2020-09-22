package com.company;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Random;
import java.util.Arrays;
import java.lang.Math;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Main {

    public static void main(String[] args) {

        // SELECTION SORT TEST
        System.out.println("SELECTION SORT:");
        selectionSortTimeTest();

        //MERGE SORT TEST
        System.out.println("MERGE SORT:");
        mergeSortTimeTest();

        //QUICK SORT TEST
        System.out.println("QUICK SORT:");
        quickSortTimeTest();

        //RADIX SORT TEST
        System.out.println("RADIX-1 SORT:");
        radixSortTimeTest(1);
        System.out.println("RADIX-2 SORT:");
        radixSortTimeTest(2);
        System.out.println("RADIX-3 SORT:");
        radixSortTimeTest(3);

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

    public static void selectionSortTimeTest(){
        float dr = 0, pdr = 0;
        int N_prev = 0;
        char[][] arr;
        float k6time=0, k12time=0, k24time=0, k48time=0;
        long timeBefore, timeAfter, difference=0, maxTime = (long)Math.pow(2,36);
        int N = 10;
        System.out.println("             k = 6                         k = 12                        k = 24                        k = 48                                                ");
        System.out.println("    N    |    Time    | Doubling Ratio |    Time    | Doubling Ratio |    Time    | Doubling Ratio |    Time    | Doubling Ratio | Predicted Doubling Ratio |");

        while (difference < maxTime){
            System.out.printf("%8d |", N);
            // sort with k = 6
            arr = generateTestList(N,6,1,255);
            timeBefore = getCpuTime();
            selectionSort(arr,N);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k6time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k6time = difference;

            //sort with k = 12
            arr = generateTestList(N,12,1,255);
            timeBefore = getCpuTime();
            selectionSort(arr,N);
            timeAfter = getCpuTime();
            difference = timeAfter-timeBefore;
            if (N != 10){
                dr = difference/k12time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k12time = difference;

            // sort with k = 24
            arr = generateTestList(N,24,1,255);
            timeBefore = getCpuTime();
            selectionSort(arr,N);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k24time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k24time = difference;

            //sort with k = 48
            arr = generateTestList(N,48,1,255);
            timeBefore = getCpuTime();
            selectionSort(arr,N);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k48time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k48time = difference;

            if (N != 10){
                pdr = (float)(Math.pow(N,2)/Math.pow(N_prev,2));
                System.out.printf("%25.2f |\n", pdr);
            }
            else{
                System.out.printf("           na             |\n");
            }
            N_prev = N;
            N = N*2;
        }

    }

    public static void mergeSortTimeTest(){
        float dr = 0, pdr = 0;
        int N_prev = 0;
        char[][] arr;
        float k6time=0, k12time=0, k24time=0, k48time=0;
        long timeBefore, timeAfter, difference=0, maxTime = (long)Math.pow(2,36);
        int N = 10;
        System.out.println("             k = 6                         k = 12                        k = 24                        k = 48                                                ");
        System.out.println("    N    |    Time    | Doubling Ratio |    Time    | Doubling Ratio |    Time    | Doubling Ratio |    Time    | Doubling Ratio | Predicted Doubling Ratio |");

        while (difference < maxTime){
            System.out.printf("%8d |", N);
            // sort with k = 6
            arr = generateTestList(N,6,1,255);
            timeBefore = getCpuTime();
            mergeSort(arr,0,N-1,6);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k6time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k6time = difference;

            //sort with k = 12
            arr = generateTestList(N,12,1,255);
            timeBefore = getCpuTime();
            mergeSort(arr,0,N-1,12);
            timeAfter = getCpuTime();
            difference = timeAfter-timeBefore;
            if (N != 10){
                dr = difference/k12time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k12time = difference;

            // sort with k = 24
            arr = generateTestList(N,24,1,255);
            timeBefore = getCpuTime();
            mergeSort(arr,0,N-1,24);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k24time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k24time = difference;

            //sort with k = 48
            arr = generateTestList(N,48,1,255);
            timeBefore = getCpuTime();
            mergeSort(arr,0,N-1,48);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k48time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k48time = difference;

            if (N != 10){
                pdr = (N*(float)Math.log(N))/(N*(float)Math.log(N_prev));
                System.out.printf("%25.2f |\n", pdr);
            }
            else{
                System.out.printf("           na             |\n");
            }
            N_prev = N;
            N = N*2;
        }

    }


    public static void radixSortTimeTest(int d){
        float dr = 0, pdr = 0;
        int N_prev = 0;
        char[][] arr;
        float k6time=0, k12time=0, k24time=0, k48time=0;
        long timeBefore, timeAfter, difference=0, maxTime = (long)Math.pow(2,30);
        int N = 10;
        System.out.println("             k = 6                         k = 12                        k = 24                        k = 48                                                ");
        System.out.println("    N    |    Time    | Doubling Ratio |    Time    | Doubling Ratio |    Time    | Doubling Ratio |    Time    | Doubling Ratio | Predicted Doubling Ratio |");

        while (difference < maxTime){
            System.out.printf("%8d |", N);
            // sort with k = 6
            arr = generateTestList(N,6,1,255);
            timeBefore = getCpuTime();
            radixSort(arr,N,6,d);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k6time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k6time = difference;

            //sort with k = 12
            arr = generateTestList(N,12,1,255);
            timeBefore = getCpuTime();
            radixSort(arr,N,12,d);
            timeAfter = getCpuTime();
            difference = timeAfter-timeBefore;
            if (N != 10){
                dr = difference/k12time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k12time = difference;

            // sort with k = 24
            arr = generateTestList(N,24,1,255);
            timeBefore = getCpuTime();
            radixSort(arr,N,24,d);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k24time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k24time = difference;

            //sort with k = 48
            arr = generateTestList(N,48,1,255);
            timeBefore = getCpuTime();
            radixSort(arr,N,48,d);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k48time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k48time = difference;

            if (N != 10){
                pdr = (N*48)/(N_prev*48);
                System.out.printf("%25.2f |\n", pdr);
            }
            else{
                System.out.printf("           na             |\n");
            }
            N_prev = N;
            N = N*2;
        }

    }

    public static void quickSortTimeTest(){
        float dr = 0, pdr = 0;
        int N_prev = 0;
        char[][] arr;
        float k6time=0, k12time=0, k24time=0, k48time=0;
        long timeBefore, timeAfter, difference=0, maxTime = (long)Math.pow(2,36);
        int N = 10;
        System.out.println("             k = 6                         k = 12                        k = 24                        k = 48                                                ");
        System.out.println("    N    |    Time    | Doubling Ratio |    Time    | Doubling Ratio |    Time    | Doubling Ratio |    Time    | Doubling Ratio | Predicted Doubling Ratio |");

        while (difference < maxTime){
            System.out.printf("%8d |", N);
            // sort with k = 6
            arr = generateTestList(N,6,1,255);
            timeBefore = getCpuTime();
            quickSort(arr,0,N-1);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k6time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k6time = difference;

            //sort with k = 12
            arr = generateTestList(N,12,1,255);
            timeBefore = getCpuTime();
            quickSort(arr,0,N-1);
            timeAfter = getCpuTime();
            difference = timeAfter-timeBefore;
            if (N != 10){
                dr = difference/k12time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k12time = difference;

            // sort with k = 24
            arr = generateTestList(N,24,1,255);
            timeBefore = getCpuTime();
            quickSort(arr,0,N-1);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k24time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k24time = difference;

            //sort with k = 48
            arr = generateTestList(N,48,1,255);
            timeBefore = getCpuTime();
            quickSort(arr,0,N-1);
            timeAfter = getCpuTime();
            difference = timeAfter - timeBefore;
            if (N != 10){
                dr = difference/k48time;
                System.out.printf("%11d |%15.3f |", difference,dr);
            }
            else{
                System.out.printf("%11d |      na        |", difference);
            }
            k48time = difference;

            if (N != 10){
                pdr = (N*(float)Math.log(N))/(N*(float)Math.log(N_prev));
                System.out.printf("%25.2f |\n", pdr);
            }
            else{
                System.out.printf("           na             |\n");
            }
            N_prev = N;
            N = N*2;
        }

    }

    public static long getCpuTime(){
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

}
