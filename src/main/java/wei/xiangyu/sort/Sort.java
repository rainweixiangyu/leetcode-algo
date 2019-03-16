package wei.xiangyu.sort;

import java.util.Arrays;

public interface Sort {

  default void printArray(int[] nums){
    Arrays.stream(nums).forEach(num -> System.out.print(num));
  }

  void sort(int[] nums) throws Exception;

  default void exchange(int[] nums, int a, int b){
    int tmp = nums[a];
    nums[a] = nums[b];
    nums[b] = tmp;
  }
}
