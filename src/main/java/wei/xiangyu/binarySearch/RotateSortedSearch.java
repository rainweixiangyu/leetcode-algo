package wei.xiangyu.binarySearch;

import java.util.ArrayList;

public class RotateSortedSearch {
  public int search(int[] nums, int target) {
    if(null == nums || nums.length == 0){
      return -1;
    }

    return rotateSortedSearch(nums,0,nums.length-1,target);
  }

  public int rotateSortedSearch(int[] nums,int start,int end,int target){
    while(start <= end){
      int middle = start + (end - start)/2;

      if(nums[middle] == target){
        return middle;
      }else if(nums[middle] > target){
        if(nums[end] > nums[middle]){
          end = middle - 1;
        }else if(nums[start] > target){
          start = middle + 1;
        }else{
          end = middle - 1;
        }
      }else{
        if(nums[start] < nums[middle]){
          start = middle + 1;
        }else if(nums[end] < target){
          end = middle - 1;
        }else {
          start = middle + 1;
        }
      }
    }

    return -1;
  }

  public int findMin(int[] nums) {
    if(null == nums || nums.length == 0){
      return Integer.MIN_VALUE;
    }

    int start = 0;
    int end = nums.length - 1;
    int min = nums[start];
    while(start <= end ){
      int middle = start + (end - start)/2;
      if(nums[start] < nums[middle]){
        if(nums[start] < nums[end]){
          min = Math.min(min, nums[start]);
          break;
        }else{
          start = middle + 1;
        }
      }else{
        min = Math.min(min, nums[middle]);
        end = middle - 1;
      }
    }

    return min;
  }


}
