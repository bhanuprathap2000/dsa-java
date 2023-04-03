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

        int maxSumSubArrayBruteForce= findMaxSumSubArrayBruteForce(3,new int[]{2,1,5,1,3,2});
        System.out.println("maxSumSubArrayBruteForce");
        System.out.println(maxSumSubArrayBruteForce);

        int maxSumSubArraySlidingWindow= findMaxSumSubArraySlidingWindow(3,new int[]{2,1,5,1,3,2});
        System.out.println("maxSumSubArraySlidingWindow");
        System.out.println(maxSumSubArraySlidingWindow);

        int minSubArray = findMinSubArray(7, new int[]{2, 1, 5, 2, 3, 2});
        System.out.println("minSubArray");
        assert  minSubArray==2;
        System.out.println(minSubArray);




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
     * */
    public static double[] findAveragesSlidingWindow(int k,int[] arr){
        double[] result = new double[arr.length-k+1];
        double windowSum=0;
        int windowStart=0;

        for(int windowEnd=0;windowEnd<arr.length;windowEnd++){
            windowSum=windowSum+arr[windowEnd];

            //slide the window when we calculated the previous sub array of average
            if(windowEnd>=k-1){
                result[windowStart]= windowSum/k; //calculate the average
                windowSum= windowSum-arr[windowStart]; //subtract the element going out of sliding window
                windowStart++; // slide the window

            }

        }


        return result;
    }

    /**
     *  A basic brute force solution will be to calculate the sum of all ‘k’ sized subarrays of the given array,
     *  to find the subarray with the highest sum. We can start from every index of the given array
     *  and add the next ‘k’ elements to find the sum of the subarray.
     *
     * The time complexity of the above algorithm will be O(N*K), where ‘N’ is the total number of elements in the given array.
     * */
    public static int findMaxSumSubArrayBruteForce(int k,int[] arr){
        int maxSum=0,windowSum;

        for(int i=0;i<=arr.length-k;i++){
            windowSum=0;
            for(int j=i;j<i+k;j++){
                windowSum=windowSum+arr[j];
            }
            //calculate the max between the window sum of size k and max sum till now
            if(windowSum>maxSum){
                maxSum=windowSum;
            }
        }
        return maxSum;
    }

    /**
     * A better approach
     * If you observe closely, you will realize that to calculate the sum of a contiguous subarray we can utilize the sum of the previous subarray.
     * For this, consider each subarray as a Sliding Window of size ‘k’. To calculate the sum of the next subarray,
     * we need to slide the window ahead by one element. So to slide the window forward and calculate the sum of the new position of the sliding window,
     * we need to do two things:
     *
     * Subtract the element going out of the sliding window i.e., subtract the first element of the window.
     * Add the new element getting included in the sliding window i.e., the element coming right after the end of the window.
     * This approach will save us from re-calculating the sum of the overlapping part of the sliding window.
     * */
    public static int findMaxSumSubArraySlidingWindow(int k,int[] arr){
        int maxSum=0,windowSum=0,windowStart=0;

        for(int windowEnd=0;windowEnd<arr.length;windowEnd++){
            windowSum=windowSum+arr[windowEnd];
            if(windowEnd>=k-1){
                maxSum=Math.max(windowSum,maxSum);
                windowSum=windowSum-arr[windowStart];
                windowStart++;
            }
        }
        return maxSum;
    }

    /**
     * This problem follows the Sliding Window pattern and we can use a similar strategy as discussed in Maximum Sum Subarray of Size K. There is one difference though: in this problem, the size of the sliding window is not fixed. Here is how we will solve this problem:
     *
     * First, we will add-up elements from the beginning of the array until their sum becomes greater than or equal to ‘S’.
     * These elements will constitute our sliding window. We are asked to find the smallest such window having a sum greater than or equal to ‘S’. We will remember the length of this window as the smallest window so far.
     * After this, we will keep adding one element in the sliding window (i.e. slide the window ahead), in a stepwise fashion.
     * In each step, we will also try to shrink the window from the beginning. We will shrink the window until the window’s sum is smaller than ‘S’ again. This is needed as we intend to find the smallest window. This shrinking will also happen in multiple steps; in each step we will do two things:
     * Check if the current window length is the smallest so far, and if so, remember its length.
     * Subtract the first element of the window from the running sum to shrink the sliding window.
     *
     * For time complexity when there are for loops and while loops don't always consider that it will be O(n^2) analyse it propelry like for one iteration of forloop how many times
     * inside loop will run.
     *
     * Time Complexity
     * The time complexity of the above algorithm will be
     * O(N). The outer for loop runs for all elements and the inner while loop processes each element only once, therefore the time complexity of the algorithm will be
     * O(N+N) which is asymptotically equivalent to
     * O(N).
     *
     * Space Complexity
     * The algorithm runs in constant space
     * O(1).
     * */
    public static int findMinSubArray(int s,int[] arr){

        int minLength=Integer.MAX_VALUE; //to get the right min value for first time we initialize with the largest possible value.
        int windowStart=0;
        int windowSum=0;
        for(int windowEnd=0;windowEnd<arr.length;windowEnd++){

            windowSum= windowSum+arr[windowEnd];

            while (windowSum>=s){
                minLength= Math.min(minLength,windowEnd-windowStart+1);
                windowSum=windowSum-arr[windowStart];
                windowStart++;
            }
        }

        return minLength==Integer.MAX_VALUE?0:minLength;
    }

    /**
     *  We can use the hashmap as the frequency counter.
     *  Like we can use when we need to remember the unique elements or count of the elements.
     * One thing I observed is that in sliding window we need to scan the entire array or string once with the help of the window
     * and every time before sliding the window we will either remember minimum so far or maximum so far and return that at last
     *
     * */


}
