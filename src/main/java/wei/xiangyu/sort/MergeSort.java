package wei.xiangyu.sort;

import java.util.Objects;

public class MergeSort implements Sort {
  private int[] nums;

  public MergeSort(int[] nums){
    this.nums = nums.clone();
    System.out.println("\nMergeSort:");
    printArray(this.nums);
  }

  public int[] getNums(){
    return this.nums;
  }

  @Override
  public void sort(int[] nums) throws Exception{
    if(Objects.isNull(nums) || nums.length==0){
      throw new IllegalArgumentException("Input nums should not be empty.");
    }

    if(nums.length == 1){
      return;
    }

    mergeSort(nums,0,nums.length);

    System.out.println("\nAfter:");
    printArray(nums);
  }

  public void mergeSort(int[] nums, int start, int end){
    if(start+1 >= end){
      return;
    }

    int middle = start + (end-start)/2;
    mergeSort(nums,start,middle);
    mergeSort(nums,middle,end);

    merge(nums,start,middle,end);
  }

  public void merge(int[] nums, int start, int middle, int end){
    int[] tmp = new int[end-start];
    int i = start;
    int j = middle;
    int k = 0;

    while(i<middle && j<end){
      if(nums[i] < nums[j]){
        tmp[k++] = nums[i++];
      }else{
        tmp[k++] = nums[j++];
      }
    }

    while(i<middle){
      tmp[k++] = nums[i++];
    }

    while(j<end){
      tmp[k++] = nums[j++];
    }

    while(k>0){
      nums[--end] = tmp[--k];
    }
  }
}
