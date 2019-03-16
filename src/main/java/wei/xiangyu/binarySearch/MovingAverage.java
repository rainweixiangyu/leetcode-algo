package wei.xiangyu.binarySearch;

public class MovingAverage {
  public int count;
  public int size;
  public int[] nums;
  public int pos;
  public double sum;
  /** Initialize your data structure here. */
  public MovingAverage(int size) {
    this.count = 0;
    this.size = size;
    this.nums = new int[this.size];
    this.pos = 0;
    this.sum = 0;
  }

  public double next(int val) {
    this.count = this.count+1>=this.size?this.size:this.count+1;
    this.sum = this.sum+val-nums[this.pos];
    this.nums[this.pos] = val;
    this.pos = (this.pos+1)%this.size;

    return this.sum/this.count;
  }
}
