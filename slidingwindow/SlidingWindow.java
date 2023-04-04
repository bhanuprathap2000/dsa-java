package org.example.slidingwindow;

import java.util.Arrays;

public class SlidingWindow {

    public static void main(String[] args) {

        double[] resultBruteForce= findAveragesBruteForce(5,new int[]{1,3,2,6,-1,4,1,8,2});
        System.out.println("Result BruteForce");
        System.out.println(Arrays.toString(resultBruteForce));

        double[] resultSlidingWindow= findAveragesSlidingWindow(5,new int[]{1,3,2,6,-1,4,1,8,2});
        System.out.println("Result SlidingWindow");
        System.out.println(Arrays.toString(resultSlidingWindow));



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
        double sum=0;
        for(int i=0;i<=arr.length-k;i++){
            sum=0; //everytime reset when calculating for average for next contiguous sub array
            for(int j=i;j<=i+k-1;j++){
                sum=sum+arr[j];
            }
            result[i]= sum/k; //calculate the average
        }
        return result;
    }

    /**
     * In brute force approach there is a inefficiency is that for any two consecutive subarrays of size ‘5’,
     * the overlapping part (which will contain four elements) will be evaluated twice.
     *
     * The efficient way to solve this problem would be to visualize each contiguous subarray as a sliding window of ‘5’ elements.
     * This means that when we move on to the next subarray, we will slide the window by one element. So, to reuse the sum
     * from the previous subarray, we will subtract the element going out of the window and add the element now being
     * included in the sliding window. This will save us from going through the whole subarray to find the sum and, as a result,
     * the algorithm complexity will reduce to

     * O(N).
     *
     * In some problems, the size of the sliding window is not fixed. We have to expand or shrink the window based on the problem constraints.
     * */
    public static double[] findAveragesSlidingWindow(int k, int[] arr) {
        double[] result = new double[arr.length - k + 1]; //create a array to store the average of all sub array of size k

        int windowStart = 0;
        int windowSum = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {

            //For first k times the if condition is not executed because we have not found the array of size k, once we found the process of sliding window starts
            //First we need to form sub array of size k as per question and then sliding window technique is used.
            //Sliding window technique means we do some kind of process on the window and then slide the window(increment window start and window end) we do this until we cover all windows
            windowSum = windowSum + arr[windowEnd];

            //This means once we have found the sub array of k elements then we can calculate the average until then just calculate the sum
            if (windowEnd >= k - 1) {
                result[windowStart]= windowSum/k; //calculate the average
                windowSum= windowSum-arr[windowStart]; //subtract the element going out of sliding window
                windowStart++; // slide the window
            }
        }


        return result;
    }

}
