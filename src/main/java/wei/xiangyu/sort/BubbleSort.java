package wei.xiangyu.sort;

import java.util.Objects;

public class BubbleSort implements Sort {
  private int[] nums;

  public BubbleSort(int[] nums){
    this.nums = nums.clone();
    System.out.println("\nBubbleSort:");
    printArray(this.nums);
  }

  public int[] getNums(){
    return this.nums;
  }

  @Override
  public void sort(int[] nums) throws Exception{
    if(Objects.isNull(nums) || nums.length==0){
      throw new IllegalArgumentException("Input nums should not be empty");
    }

    if(nums.length == 1){
      return;
    }

    for(int i=1; i<nums.length; i++){
      for(int j=0; j<(nums.length-i); j++){
        if(nums[j] > nums[j+1]){
          exchange(nums, j, j+1);
        }
      }
    }

    System.out.println("\nAfter:");
    printArray(nums);
  }
}
