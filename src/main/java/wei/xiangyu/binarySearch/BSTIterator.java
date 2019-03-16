package wei.xiangyu.binarySearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BSTIterator {

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode root;
  public List<Integer> nums;

  public BSTIterator(TreeNode root) {
    this.root = root;
    inorderSearch(root,nums);
  }

  public void inorderSearch(TreeNode root,List<Integer> nums){
    List<TreeNode> stack = new LinkedList<TreeNode>();
    TreeNode current = root;
    while(!stack.isEmpty() || current!=null){
      if(current != null){
        ((LinkedList<TreeNode>) stack).push(current);
        current = current.left;
      }else{
        current = ((LinkedList<TreeNode>) stack).pop();
        nums.add(current.val);
        current = current.right;
      }
    }
  }

  /** @return the next smallest number */
  public int next() {
    Integer value = this.nums.get(0);
    this.nums.remove(0);
    return value;
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return nums.size()>0;
  }
}
