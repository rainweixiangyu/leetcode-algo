package wei.xiangyu.binarySearch;

import java.util.HashMap;
import java.util.Hashtable;

public class Goujia {
  public static int repeatedStringMatch(String A, String B) {
    int aLen = A.length();
    int bLen = B.length();
    int count = 1;
    StringBuilder stringBuilder = new StringBuilder(A);

    while (stringBuilder.length() < bLen) {
      stringBuilder.append(A);
      count++;
    }

    if (stringBuilder.indexOf(B) != -1) {
      return count;
    }

    if (stringBuilder.append(A).indexOf(B) != -1) {
      return count + 1;
    }

    return -1;
  }

  public static int kEmptySlots(int[] flowers, int k) {
    if (flowers == null || flowers.length < (k + 2)) {
      return -1;
    }

    int len = flowers.length;
    int[] position = new int[len];
    for (int day = 0; day < len; day++) {
      position[flowers[day] - 1] = day + 1;
    }

    int satifyDay = Integer.MAX_VALUE;

    for (int j = 0; j < (len - k - 1); j++) {
      int start = j;
      int end = j + k + 1;
      int max = Math.max(position[start], position[end]);
      boolean flag = true;
      for (int i = start + 1; i < end; i++) {
        if (position[i] < max) {
          flag = false;
          break;
        }
      }

      if (flag) {
        satifyDay = Math.min(satifyDay, Math.max(position[start], position[end]));
      }
    }
    if (satifyDay == Integer.MAX_VALUE) {
      return -1;
    } else {
      return satifyDay;
    }
  }

  public String nextClosestTime(String time) {
    int current = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    for (int i = 1; i <= 24 * 60; i++) {
      int minute = (current + i) % 60;
      int hour = ((current + i) / 60) % 24;
      String newTime = (hour >= 10 ? "" : "0") + String.valueOf(hour) + ":" + (minute >= 10 ? "" : "0") + String.valueOf(minute);
      boolean flag = true;
      for (int j = 0; j < 5; j++) {
        if (time.indexOf(newTime.charAt(j) + "") == -1) {
          flag = false;
        }
      }

      if (flag) {
        return newTime;
      }
    }
    return null;
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public int longestUnivaluePath(TreeNode root) {
    int result = 0;
    longestPath(root,result);

    return result;
  }

  public int longestPath(TreeNode root, int result){
    if(root==null){
      return 0;
    }

    int left  = longestPath(root.left,result);
    int right = longestPath(root.right,result);
    if(root.left!=null && root.val==root.left.val){
      left = left + 1;
    }else{
      left = 0;
    }
    if(root.right!=null && root.val==root.right.val){
      right = right + 1;
    }else{
      right = 0;
    }
    if(result < (left+right)){
      result = left + right;
    }

    return Math.max(left,right);
  }

  public int trap(int[] height) {
    if(height==null || height.length<3){
      return 0;
    }

    int left = 0;
    int right = height.length-1;
    int result = 0;
    while(left<right){
      while(left<right && height[left]<height[left+1]){
        left++;
      }

      while(left<right && height[right]<height[right-1]){
        right--;
      }

      int min = Math.min(height[left],height[right]);
      if(min == height[left]){
        left++;
        while(left<right && height[left]<min){
          result += min-height[left];
          left++;
        }
      }else{
        right--;
        while(left<right && height[right]<min){
          result += min-height[right];
          right--;
        }
      }
    }
    return result;
  }

  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if(s==null){
      return 0;
    }
    if(s.length()<k){
      return s.length();
    }

    HashMap<Character,Integer> map = new HashMap<>();
    char[] sArray = s.toCharArray();
    int result = 0;
    int start = 0;
    for(int i=0; i<sArray.length; i++) {
      char ele = sArray[i];
      if (map.containsKey(ele)) {
        map.put(ele, map.get(ele) + 1);
      } else {
        map.put(ele, 1);
      }

      if (map.size() < k) {
        result = Math.max(result, i - start + 1);
      } else if (map.size() == k) {
        int count = map.values().stream().reduce(0, (a, b) -> a + b);
        result = Math.max(result, count);
      } else {
        while (map.size() > k) {
          if (map.get(sArray[start]) == 1) {
            map.remove(sArray[start]);
          } else {
            map.put(sArray[start], map.get(sArray[start]) - 1);
          }
          start++;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    String time = "19:34";

    String newTime = nextClosestTime(time);
    System.out.println(newTime);
    Hashtable hashtable

  }


}
