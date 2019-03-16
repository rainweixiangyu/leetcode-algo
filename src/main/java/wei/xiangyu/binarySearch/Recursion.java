package wei.xiangyu.binarySearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Recursion {

  public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode swapPairs(ListNode head) {
    if(head == null){
      return null;
    }

    ListNode newHead = swap(head);

    return newHead;
  }

  public ListNode swap(ListNode head){
    if(head.next == null){
      return head;
    }

    ListNode nextRound = head.next.next;
    ListNode newHead = head.next;
    head.next.next = head;

    if(nextRound != null){
      head.next = swap(nextRound);
    }else{
      head.next = null;
    }

    return newHead;
  }

  public List<Integer> getRow(int rowIndex) {
    List<Integer> result = new LinkedList<>();

    if(rowIndex == 0){
      result.add(1);
      return result;
    }

    if(rowIndex == 1){
      result.add(1);
      result.add(1);
      return result;
    }

    List<Integer> pre = getRow(rowIndex-1);
    result.add(1);
    for(int i=0;i<rowIndex-1;i++){
      result.add(pre.get(i)+pre.get(i+1));
    }
    result.add(1);

    return result;
  }

  public int kthGrammar(int N, int K) {
    if (N == 1) return 0;
    if (K % 2 == 0) return 1- kthGrammar(N - 1, K / 2);
    else return kthGrammar(N - 1, (K + 1) / 2);
  }

  private class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public List<TreeNode> generateTrees(int n) {
    if (n <= 0) return new ArrayList<TreeNode>();
    return generateSubTree(1, n);
  }
  public ArrayList<TreeNode> generateSubTree(int start, int end) {
    ArrayList<TreeNode> result = new ArrayList<TreeNode>();
    if (start > end) {
      result.add(null);
      return result;
    }
    for (int rootVal = start; rootVal <= end; rootVal++)
      for (TreeNode leftSubTreeRoot : generateSubTree(start, rootVal - 1))
        for (TreeNode rightSubTreeRoot : generateSubTree(rootVal + 1, end)) {
          TreeNode root = new TreeNode(rootVal);
          root.left = leftSubTreeRoot;
          root.right = rightSubTreeRoot;
          result.add(root);
        }
    return result;
  }

  public boolean isMatch(String text, String pattern) {
    if (pattern.isEmpty()) return text.isEmpty();
    boolean first_match = (!text.isEmpty() &&
            (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

    if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
      return (isMatch(text, pattern.substring(2)) ||
              (first_match && isMatch(text.substring(1), pattern)));
    } else {
      return first_match && isMatch(text.substring(1), pattern.substring(1));
    }
  }
}
