package wei.xiangyu.binarySearch;

import java.util.LinkedList;
import java.util.List;

public class HitCounter {
  public static final int WINDOW_SIZE = 300;
  public List<Integer> records;

  /** Initialize your data structure here. */
  public HitCounter() {
    records = new LinkedList<>();
  }

  /** Record a hit.
   @param timestamp - The current timestamp (in seconds granularity). */
  public void hit(int timestamp) {
    this.records.add(timestamp);
  }

  /** Return the number of hits in the past 5 minutes.
   @param timestamp - The current timestamp (in seconds granularity). */
  public int getHits(int timestamp) {
    int start = timestamp>WINDOW_SIZE ? timestamp-WINDOW_SIZE : 0;
    int count = 0;
    for(Integer ele : records){
      if(ele > start && ele <=timestamp){
        count++;
      }
    }

    return count;
  }
}
