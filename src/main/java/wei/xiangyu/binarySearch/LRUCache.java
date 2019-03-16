package wei.xiangyu.binarySearch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class LRUCache {
  public Queue<Integer> queue;
  public HashMap<Integer,Integer> map;
  public int capacity;

  public LRUCache(int capacity) {
    queue = new LinkedList<>();
    map = new HashMap<>();
    this.capacity = capacity;
  }

  public int get(int key) {
    if(map.containsKey(key)){
      queue.remove(key);
      queue.add(key);
      return map.get(key);
    }else{
      return -1;
    }
  }

  public void put(int key, int value) {
    if(queue.size() == this.capacity){
      int delKey;
      if(queue.contains(key)){
        queue.remove(key);
        delKey = key;
      }else{
        delKey = queue.remove();
      }
      queue.add(key);
      map.remove(delKey);
      map.put(key,value);
    }else{
      map.put(key,value);
      queue.remove(key);
      queue.add(key);
    }
  }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
