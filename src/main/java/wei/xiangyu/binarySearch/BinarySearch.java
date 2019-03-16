package wei.xiangyu.binarySearch;

import java.util.Arrays;
import java.util.stream.Stream;

public class BinarySearch {
  public int search(int[] nums, int target) {
    if(nums==null || nums.length == 0){
      return -1;
    }

    int start = 0;
    int end = nums.length - 1;

    return binarySearch(nums,start,end,target);
  }

  public int binarySearch(int[] nums,int start,int end,int target){

    while(start <= end){
      int middle = start + (end - start)/2;
      if(nums[middle] == target){
        return middle;
      }else if(nums[middle] < target){
        start = middle + 1;
      }else{
        end = middle -1;
      }
    }

    return -1;
  }

  public int[] twoSum(int[] numbers, int target) {
    int[] result = new int[]{-1,-1};

    int start = 0;
    int end = numbers.length - 1;
    while(start < end){
      if(numbers[start]+numbers[end] == target){
        result = new int[]{start+1,end+1};
        return result;
      }else if(numbers[start]+numbers[end] < target){
        start++;
      }else{
        end--;
      }
    }

    return result;
  }

  public int findDuplicate(int[] nums) {
    int big = nums.length - 1;
    int small = 1;
    while(small < big){
      int middle = small+(big-small)/2;
      int count = 0;
      for(int i=0; i<nums.length; i++){
        if(nums[i] <= middle){
          count++;
        }
      }
      if(count <= middle){
        small = middle+1;
      }else{
        big = middle;
      }
    }

    return small;
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int len = nums1.length + nums2.length;

    if(len%2 == 1){
      return findKth(nums1,nums2,0,nums1.length,0,nums2.length,len/2+1);
    }else{
      return (findKth(nums1,nums2,0,nums1.length,0,nums2.length,len/2) +
              findKth(nums1,nums2,0,nums1.length,0,nums2.length,len/2+1))/2.0f;
    }
  }

  public double findKth(int[] nums1,int[] nums2,int start1,int len1,int start2, int len2,int k){
    if(len1 > len2){
      return findKth(nums2,nums1,start2,len2,start1,len1,k);
    }

    if(len1 == 0){
      return nums2[start2+k-1];
    }

    if(k == 1){
      return Math.min(nums1[start1],nums2[start2]);
    }

    int p1 = Math.min(k/2, len1);
    int p2 = k-p1;

    if(nums1[start1+p1-1] > nums2[start2+p2-1]){
      return findKth(nums1, nums2, start1, len1, start2 + p2, len2 - p2, k - p2);
    }else if(nums1[start1+p1-1] < nums2[start2+p2-1]){
      return findKth(nums1, nums2, start1 + p1, len1 - p1, start2, len2, k - p1);
    }else{
      return nums1[start1+p1-1];
    }
  }

  public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);
    int smallDis = 0;
    int bigDis = nums[nums.length-1]-nums[0];

    while(smallDis < bigDis) {
      int distance = smallDis + (bigDis-smallDis)/2;
      int count = findCountLessEqualThanDistance(nums, distance, k);
      if (count >= k) {
        bigDis = distance;
      } else{
        smallDis = distance+1;
      }
    }

    return smallDis;
  }

  public int findCountLessEqualThanDistance(int[] nums, int distance, int k){
    int count = 0;
    for(int i=0; i<nums.length; i++){
      for(int j=i+1;j<nums.length; j++){
        if((nums[j]-nums[i]) <= distance){
          count++;
        }else{
          break;
        }
      }
      if(count >= k){
        return count;
      }
    }

    return count;
  }

  public int splitArray(int[] nums, int m) {
    int small = 0;
    int big = 0;
    for(int e : nums){
      if(e > small){
        small = e;
      }
      big += e;
    }

    while(small < big){
      int dis = small+(big-small)/2;
      int count = splitArrayLessThan(nums,dis);
      if(count <= m){
        big = dis;
      }else{
        small = dis+1;
      }
    }

    return small;
  }

  public int splitArrayLessThan(int[] nums,int distance){
    int count = 1;
    int sum = 0;
    for(int ele: nums){
      sum += ele;
      if(sum > distance){
        sum = ele;
        count++;
      }
    }

    return count;
  }
}
