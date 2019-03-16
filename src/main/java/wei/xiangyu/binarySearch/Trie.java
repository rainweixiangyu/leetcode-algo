package wei.xiangyu.binarySearch;

import java.util.LinkedList;
import java.util.List;

public class Trie {
  public List<String> strs;

  /** Initialize your data structure here. */
  public Trie() {
    strs = new LinkedList<>();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    if(!strs.contains(word)){
      strs.add(word);
    }
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    for(String ele : strs){
      if(ele.equals(word)){
        return true;
      }
    }

    return false;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    for(String ele : strs){
      if(ele.startsWith(prefix)){
        return true;
      }
    }

    return false;
  }
}
