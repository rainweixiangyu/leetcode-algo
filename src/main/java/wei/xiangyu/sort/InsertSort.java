package wei.xiangyu.sort;

import java.util.Objects;


public class InsertSort implements Sort{

  private int[] nums;

  public InsertSort(int[] nums){
    this.nums = nums.clone();
    System.out.println("\nInsertSort:");
    printArray(this.nums);
  }

  @Override
  public void sort(int[] nums) throws Exception{
    if(Objects.isNull(nums) || nums.length == 0){
      throw new IllegalArgumentException("Input nums shuld not be empty.");
    }

    if(nums.length == 1){
      return;
    }

    for(int i=1; i<nums.length; i++){
      int current = nums[i];
      int j = i-1;

      while(j>=0 && nums[j]>current){
        nums[j+1] = nums[j];
        j--;
      }

      nums[j+1] = current;
    }

    System.out.println("\nAfter:");
    printArray(nums);
  }

  public int[] getNums(){
    return this.nums;
  }
}
