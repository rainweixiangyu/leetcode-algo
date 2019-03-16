package wei.xiangyu.binarySearch;

import java.util.*;

public class RangeSearch {

  public int[] searchRange(int[] nums, int target) {
    int[] result = new int[]{-1,-1};
    if(null == nums || nums.length == 0){
      return result;
    }
    int start = 0;
    int end = nums.length - 1;
    if(nums[start] > target || nums[end] < target){
      return result;
    }

    while(start <= end){
      int middle = start + (end - start)/2;
      if(target == nums[middle]){
        start = middle;
        while(target == nums[start]){
          start--;
        }

        end = middle;
        while(target == nums[end]){
          end++;
        }

        return new int[]{start+1,end-1};
      }else if(target < nums[middle]){
        end = middle - 1;
      }else{
        start = middle + 1;
      }
    }

    return new int[]{-1,-1};
  }

  public char nextGreatestLetter(char[] letters, char target) {
    if(null == letters || letters.length == 0){
      return 'A';
    }

    if(letters[letters.length-1] <= target){
      return letters[0];
    }

    int start = 0;
    int end = letters.length - 1;
    while(start <= end){
      int middle = start + (end - start)/2;
      if(letters[middle] == target){
        while((middle+1<letters.length) && (letters[middle+1] == target)){
          middle++;
        }

        return middle==(letters.length-1)?letters[0]:letters[middle+1];
      }else if(letters[middle] < target){
        start = middle + 1;
      }else{
        end = middle - 1;
      }
    }

    return letters[start];
  }

  public int[] intersection(int[] nums1, int[] nums2) {
    if(nums1==null || nums2==null)
      return null;
    if(nums1.length==0 || nums2.length==0)
      return new int[0];

    Set<Integer> set=new HashSet<Integer>();
    for(int i=0;i<nums1.length;i++){
      set.add(nums1[i]);
    }

    List<Integer> res = new ArrayList<Integer>();
    for(int i=0;i<nums2.length;i++){
      if(set.contains(nums2[i])){
        res.add(nums2[i]);
        set.remove(nums2[i]);
      }
    }

    int[]a=new int[res.size()];
    for(int i=0;i<res.size();i++){
      a[i]=(int)res.get(i);
    }

    return a;
  }
}
