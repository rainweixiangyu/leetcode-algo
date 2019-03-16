package wei.xiangyu.sort;

import java.util.Objects;

public class QuickSort implements Sort {
  private int[] nums;

  public QuickSort(int[] nums){
    this.nums = nums.clone();
    System.out.println("\nQuickSort:");
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

    quickSort(nums,0,nums.length-1);

    System.out.println("\nAfter:");
    printArray(nums);
  }

  public void quickSort(int[] nums, int start, int end){
    if(start >= end){
      return;
    }

    int current = nums[end];
    int limit = start-1;
    for(int i=start; i<end; i++){
      if(nums[i] <= current){
        exchange(nums,limit+1, i);
        limit++;
      }
    }
    exchange(nums,limit+1,end);

    quickSort(nums,start,limit);
    quickSort(nums,limit+2,end);
  }
}
