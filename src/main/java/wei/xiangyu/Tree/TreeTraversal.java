package wei.xiangyu.Tree;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class TreeTraversal {

  public List<Integer> preOrderTreeTraversal(TreeNode root){
    if(Objects.isNull(root)){
      return null;
    }

    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    while(stack.size()!=0 || current!=null){
      if(current != null){
        result.add(current.val);
        stack.push(current);
        current = current.left;
      }else{
        current = stack.pop();
        current = current.right;
      }
    }

    return result;
  }


  public List<Integer> inOrderTreeTraversal(TreeNode root) {
    if (Objects.isNull(root)) {
      return null;
    }

    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    while(stack.size()!=0 || current!=null){
      if(current != null){
        stack.push(current);
        current = current.left;
      }else{
        current = stack.pop();
        result.add(current.val);
        current = current.right;
      }
    }

    return result;
  }

  public List<Integer> postOrderTreeTraversal(TreeNode root) {
    if (Objects.isNull(root)) {
      return null;
    }

    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> statck = new Stack<>();
    TreeNode current = root;

    while(statck.size()!=0 || current!=null){
      if(current != null){
        result.add(current.val);
        statck.push(current);
        current = current.right;
      }else{
        current = statck.pop();
        current = current.left;
      }
    }

    Collections.reverse(result);
    return result;
  }

}
