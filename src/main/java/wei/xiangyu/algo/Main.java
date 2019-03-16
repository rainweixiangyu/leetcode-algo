package wei.xiangyu.algo;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

  public static void main(String[] args){
    AlgoImpl algo = new AlgoImpl();
    int[] nums1 = new int[]{2,3,1,1,4};
    boolean result1 = algo.canJump(nums1);
    System.out.println("Result1:"+result1);

    int[] nums2 = new int[]{3,2,1,0,4};
    boolean result2 = algo.canJump(nums2);
    System.out.println("Result2:"+result2);

    int[] coins = new int[]{1,2,5};
    int amount = 11;
    int count1 = algo.coinChange(coins,amount);
    System.out.println("Count1:"+count1);

    int[] coins2 = new int[]{2};
    int amount2 = 3;
    int count2 = algo.coinChange(coins2,amount2);
    System.out.println("Count2:"+count2);

    int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101,18};
    int maxLength = algo.lengthOfLIS(nums);
    System.out.println("MaxLength:"+maxLength);

    nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
    int maxNum = algo.maxSubArray(nums);
    System.out.println("MaxSuBArray:"+maxNum);

    String str1 = "babad";
    String palindrome1 = algo.longestPalindrome(str1);
    System.out.println("Palindrome1:"+palindrome1);

    String str2 = "cbbd";
    String palindrome2 = algo.longestPalindrome(str2);
    System.out.println("Palindrome2:"+palindrome2);

    int[] input1 = new int[]{7, 1, 5, 3, 6, 4};
    int profit1 = algo.maxProfit(input1);
    System.out.println("Profit1:"+profit1);

    int[] input2 = new int[]{7, 6, 4, 3, 1};
    int profit2 = algo.maxProfit(input2);
    System.out.println("Profit2:"+profit2);

    ConcurrentHashMap map = new ConcurrentHashMap();
    ThreadLocal threadLocal = new ThreadLocal();

  }
}
