package wei.xiangyu.sort;

import java.util.Objects;

public class HeapSort implements Sort {
  private int[] nums;

  public HeapSort(int[] nums){
    this.nums = nums.clone();
    System.out.println("\nHeapSort:");
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

    buildHeap(nums);

    for(int i=0; i<nums.length-1; i++){
      exchange(nums, 0, nums.length-i-1);
      keepHeap(nums,0,nums.length-i-1);
    }

    System.out.println("\nAfter:");
    printArray(nums);
  }

  public void buildHeap(int[] nums){
    for(int i=((nums.length/2)-1); i>=0; i--){
      keepHeap(nums,i,nums.length);
    }
  }

  public void keepHeap(int[] nums, int pos, int len){
    int left = 2*pos+1;
    int right = 2*pos+2;

    if(left>=len){
      return;
    }else if(right>=len){
      if(nums[left] > nums[pos]){
        exchange(nums, left, pos);
        keepHeap(nums,left,len);
      }
    }else{
      if((nums[left] >= nums[right]) && (nums[left] > nums[pos])){
        exchange(nums, left, pos);
        keepHeap(nums,left,len);
      }else if((nums[right] > nums[left]) && (nums[right] > nums[pos])){
        exchange(nums, right, pos);
        keepHeap(nums,right,len);
      }
    }
  }
}
