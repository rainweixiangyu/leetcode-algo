package wei.xiangyu.algo;

import java.util.Arrays;
import java.util.Objects;

public class AlgoImpl {

  public boolean canJump(int[] nums){
    if(Objects.isNull(nums) || nums.length<=1){
      return true;
    }

    boolean[] result = new boolean[nums.length];
    result[0] = true;
    for(int i=1; i<nums.length; i++){
      for(int j=i-1; j>=0; j--){
        if(result[j] && j+nums[j]>=i){
          result[i] = true;
          break;
        }
      }

      if(!result[i]){
        return false;
      }
    }

    return result[nums.length-1];
  }


  public int coinChange(int[] coins, int amount){
    if(Objects.isNull(coins) || coins.length==0){
      return -1;
    }

    int[] result = new int[amount+1];
    result[0] = 0;
    for(int i=1; i<=amount; i++){
      int min = Integer.MAX_VALUE;
      for(int coin : coins){
        if((i-coin>=0) && (result[i-coin] != -1) && (result[i-coin]+1<min)){
          min = result[i-coin]+1;
        }
      }

      if(min == Integer.MAX_VALUE){
        result[i] = -1;
      }else {
        result[i] = min;
      }
}

    return result[amount];
  }


  //这个是错的
  public int lengthOfLIS(int[] nums){
    if(Objects.isNull(nums) || nums.length==0){
      return 0;
    }

    if(nums.length == 1){
      return 1;
    }

    int[] length = new int[nums.length];
    length[0] = 1;
    for(int i=1; i<nums.length; i++){
      int max = 1;
      for(int j=i-1; j>=0; j--){
        if(nums[i]>nums[j] && length[j]+1>max){
          max = length[j]+1;
        }
      }
      length[i] = max;
    }

    return length[nums.length-1];
  }

  public int climbStair(int n){
    if(n<=0){
      return 0;
    }

    if(n == 1){
      return 1;
    }

    int[] result = new int[n];
    result[0] = 1;
    result[1] = 2;

    for(int i=2; i<n; i++){
      result[i] = result[i-1] + result[i-2];
    }

    return result[n-1];
  }

  public int maxSubArray(int[] nums){
    if(Objects.isNull(nums) || nums.length==0){
      return 0;
    }

    if(nums.length == 1){
      return nums[0];
    }

    int max = nums[0];
    int[] sum = new int[nums.length];
    sum[0] = nums[0];
    for(int i=1; i<nums.length; i++){
      if(sum[i-1] > 0){
        sum[i] = sum[i-1] + nums[i];
      }else{
        sum[i] = nums[i];
      }

      if(max < sum[i]){
        max = sum[i];
      }
    }

    return max;
  }

  public int rob(int[] nums){
    if(Objects.isNull(nums) || nums.length==0){
      return 0;
    }

    if(nums.length == 1){
      return nums[0];
    }

    int[] maxRob = new int[nums.length];
    maxRob[0] = nums[0];
    maxRob[1] = nums[0]>nums[1]? nums[0]:nums[1];

    for(int i=2; i<nums.length; i++){
      maxRob[i] = maxRob[i-1];
      if(maxRob[i-2]+nums[i] > maxRob[i-1]){
        maxRob[i] = maxRob[i-2]+nums[i];
      }
    }

    return maxRob[nums.length-1];
  }

  public int longestCommonSubSequence(char[] a, char[] b){
    if(Objects.isNull(a)||a.length==0||Objects.isNull(b)||b.length==0){
      return 0;
    }

    int[][] result = new int[a.length+1][b.length+1];
    for(int i=0; i<=a.length; i++){
      result[i][0] = 0;
    }

    for(int j=0; j<=b.length; j++){
      result[0][j] = 0;
    }

    for(int i=1; i<=a.length; i++){
      for(int j=1; j<=b.length; j++){
        if(a[i-1] == b[j-1]){
          result[i][j] = result[i-1][j-1] + 1;
        }else{
          result[i][j] = Math.max(result[i-1][j], result[i][j-1]);
        }
      }
    }

    return result[a.length][b.length];
  }

  public int longestSubString(char[] a, char[] b){
    if(Objects.isNull(a) || Objects.isNull(b)){
      return -1;
    }

    if(a.length==0 || b.length==0){
      return 0;
    }

    int[][] result = new int[a.length+1][b.length+1];
    for(int i=0; i<=a.length; i++){
      result[i][0] = 0;
    }

    for(int j=0; j<=b.length; j++){
      result[0][j] = 0;
    }

    int max = 0;
    for(int i=1; i<=a.length; i++){
      for(int j=1; j<=b.length; j++){
        if(a[i-1] == b[j-1]){
          result[i][j] = result[i-1][j-1] + 1;
        }else{
          result[i][j] = 0;
        }

        if(max < result[i][j]){
          max = result[i][j];
        }
      }
    }

    return max;
  }

  public String longestPalindrome(String s){
    if(Objects.isNull(s)){
      return "";
    }

    if(s.length() == 1){
      return s;
    }

    int length = s.length();
    int[][] result = new int[length][length];
    int max = 1;
    int left = 0;

    for(int i=0; i<length; i++){
      result[i][i] = 1;
    }

    for(int i=1; i<length; i++){
      if(s.charAt(i-1) == s.charAt((i))){
        result[i-1][i] = 2;
      }else{
        result[i-1][i] = -1;
      }

      if(max < result[i-1][i]){
        max = result[i-1][i];
        left = i-1;
      }
    }

    for(int len=2; len <length; len++){
      for(int i=0; i<length-len; i++){
        if(s.charAt(i) == s.charAt(i+len) && result[i+1][i+len-1] != -1){
          result[i][i+len] = result[i+1][i+len-1] + 2;
        }else{
          result[i][i+len] = -1;
        }

        if(max < result[i][i+len]){
          max = result[i][i+len];
          left = i;
        }
      }
    }

    return s.substring(left,left+max);
  }


  public int minDistance(String word1, String word2){
    if(Objects.isNull(word1) || Objects.isNull(word2)){
      return -1;
    }

    int len1 = word1.length();
    int len2 = word2.length();
    int[][] result = new int[len1+1][len2+1];

    for(int i=0; i<=len1; i++){
      result[i][0] = i;
    }

    for(int j=0; j <=len2; j++){
      result[0][j] = j;
    }

    for(int i=1; i<=len1; i++){
      for(int j=1; j<=len2; j++){
        if(word1.charAt(i-1) == word2.charAt(j-1)){
          result[i][j] = result[i-1][j-1];
        }else{
          result[i][j] = Math.min(result[i-1][j-1],Math.min(result[i-1][j], result[i][j-1]))+1;
        }
      }
    }

    return result[len1][len2];
  }

  public int maxProfit(int[] prices){
    if(Objects.isNull(prices) || prices.length < 2){
      return 0;
    }

    int[] profit = new int[prices.length];
    profit[0] = 0;
    int max = 0;

    for(int i=1; i<prices.length; i++){
      if(profit[i-1] > 0){
        profit[i] = profit[i-1] + prices[i] - prices[i-1];
      }else{
        profit[i] = prices[i] - prices[i-1];
      }

      if(max < profit[i]){
        max = profit[i];
      }
    }

    return max;
  }
}
