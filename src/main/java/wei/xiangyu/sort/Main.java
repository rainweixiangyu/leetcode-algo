package wei.xiangyu.sort;

import java.util.Arrays;

public class Main {
  private static int[] nums = {4,6,2,9,5,8,1,7,4};

  public static void main(String[] args){

    try {
      InsertSort insert = new InsertSort(nums);
      insert.sort(insert.getNums());

      BubbleSort bubble = new BubbleSort(nums);
      bubble.sort(bubble.getNums());

      MergeSort merge = new MergeSort(nums);
      merge.sort(merge.getNums());

      QuickSort quick = new QuickSort(nums);
      quick.sort(quick.getNums());

      HeapSort heap = new HeapSort(nums);
      heap.sort(heap.getNums());

    }catch (Exception e){
      System.out.println("Exception is caught:" + e);
    }
  }
}
