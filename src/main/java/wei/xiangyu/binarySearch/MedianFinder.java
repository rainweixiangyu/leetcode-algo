package wei.xiangyu.binarySearch;

import java.util.LinkedList;
import java.util.List;

public class MedianFinder {
  public List<Integer> nums;
  public int size;

  /** initialize your data structure here. */
  public MedianFinder() {
    nums = new LinkedList<>();
    size = 0;
  }

  public void addNum(int num) {
    int pos = 0;
    for(Integer ele : this.nums){
      if(ele < num){
        pos++;
      }else{
        break;
      }
    }
    nums.add(pos, num);
    this.size++;
  }

  public double findMedian() {
    double pre = this.nums.get((this.size-1)/2);
    double post = this.nums.get((this.size)/2);

    return (pre+post)/2;
  }
}
