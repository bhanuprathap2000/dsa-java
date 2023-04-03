package org.example.slidingwindow;

import java.util.Arrays;

public class SlidingWindow {

    public static void main(String[] args) {

        double[] resultBruteForce= findAveragesBruteForce(5,new int[]{1,3,2,6,-1,4,1,8,2});
        System.out.println("Result BruteForce");
        System.out.println(Arrays.toString(resultBruteForce));


    }

    /**
     * Given an array, find the average of all contiguous sub-arrays of size ‘K’ in it.
     *
     * n is array length and k is the size of sub array
     * The outer for loop runs for <=n-k times
     * The inner for loop runs for <=k-1 times but this has to be relative to the outer for loop means if iteration of outer for loop is
     * i then inner for loop condition will be true until the inner for loop iteration will be less than or equal to <=i+k-1
     *
     * For example take K=5
     *
     * A brute-force algorithm will be to calculate the sum of every k-element contiguous sub-array of the given array
     * and divide the sum by k to find the average.
     *
     * Basically in brute force approach we are not using the already calculated sum we are just discarding it and again
     * calculating the entire sum instead what we could have done is remove the value from array which is not part of
     * next sub array and add the next element and this way we have reused the previously calculated sum. This is what we
     * essentially do in sliding window.
     *
     *
     * Time complexity: Since for every element of the input array, we are calculating the sum of its next ‘K’ elements,
     * the time complexity of the above algorithm will be O(N*K) where ‘N’ is the number of elements in the input array.
     * */
    public static double[] findAveragesBruteForce(int k,int[] arr){

        double[] result = new double[arr.length-k+1];
        for(int i=0;i<=arr.length-k;i++){
            double sum=0; //everytime reset when calculating for average for next contiguous sub array
            for(int j=i;j<=i+k-1;j++){
                sum=sum+arr[j];
            }
            result[i]= sum/k; //calculate the average
        }
        return result;
    }

}
