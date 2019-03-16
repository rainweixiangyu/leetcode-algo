package wei.xiangyu.binarySearch;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class MyStack {

  public int[] dailyTemperatures(int[] T) {
    if(T==null){
      return null;
    }

    int[] result = new int[T.length];
    LinkedList<Integer> stack = new LinkedList<>();
    for(int ele:T){
      stack.push(ele);
    }

    int distance = T.length-1;
    while(!stack.isEmpty()){
      Integer ele = stack.pop();
      for(int i=0; i<distance;i++){
        if(T[i] < ele){
          result[i] = distance-i;
        }
      }
      distance--;
    }

    return result;
  }

  public int findTargetSumWays(int[] nums, int S) {
    if(nums==null || nums.length==0){
      return 0;
    }

    int count = countTargetSumWays(nums,nums.length,S);

    return count;
  }

  public int countTargetSumWays(int[] nums,int pos, int target){
    int count;
    int ele = nums[pos-1];
    if(pos == 1){
      if(target==ele || target == -ele){
        count = 1;
      }else{
        count = 0;
      }
    }else{
      count = countTargetSumWays(nums, pos-1, target+ele) +
              countTargetSumWays(nums, pos-1, target-ele);
    }

    return count;
  }

  public String decodeString(String s) {
    if(s == null){
      return null;
    }

    LinkedList<Integer> numStack = new LinkedList<>();
    LinkedList<String> strStack = new LinkedList<>();
    int idx = 0;
    while(idx<s.length()){
      if(Character.isDigit(s.charAt(idx))){
        int num = 0;
        while(Character.isDigit(s.charAt(idx))){
          num = 10*num + Integer.parseInt(s.charAt(idx)+"");
          idx++;
        }
        numStack.push(num);
      }else if(s.charAt(idx) == ']'){
        int num = numStack.pop();
        String subStr = "";
        while(!strStack.isEmpty() && !strStack.peek().equals("[")){
          subStr = strStack.pop() + subStr;
        }
        if(strStack.peek().equals("[")) {
          strStack.pop();//pop [
        }

        String tmpStr = "";
        while(num != 0){
          tmpStr += subStr;
          num--;
        }
        strStack.push(tmpStr);
        idx++;
      }else{
        strStack.push(s.charAt(idx)+"");
        idx++;
      }
    }

    String str = "";
    while(!strStack.isEmpty()){
      str = strStack.pop() + str;
    }

    return str;
  }

  public class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    if(image==null || image.length==0 || image[0].length==0){
      return null;
    }

    LinkedList<Point> stack = new LinkedList<>();
    int row = image.length;
    int col = image[0].length;
    boolean[][] visited = new boolean[row][col];
    Point initial = new Point(sr,sc);
    int color = image[sr][sc];
    stack.push(initial);

    while(!stack.isEmpty()){
      Point pos = stack.pop();
      visited[pos.x][pos.y] = true;
      image[pos.x][pos.y] = newColor;

      if(pos.x+1<row && image[pos.x+1][pos.y] == color && !visited[pos.x+1][pos.y]){
        stack.push(new Point(pos.x+1,pos.y));
      }

      if(pos.x-1>=0 && image[pos.x-1][pos.y] == color && !visited[pos.x-1][pos.y]){
        stack.push(new Point(pos.x-1,pos.y));
      }

      if(pos.y+1<col && image[pos.x][pos.y+1] == color && !visited[pos.x][pos.y+1]){
        stack.push(new Point(pos.x,pos.y+1));
      }

      if(pos.y-1>=0 && image[pos.x][pos.y-1] == color && !visited[pos.x][pos.y-1]){
        stack.push(new Point(pos.x,pos.y-1));
      }
    }

    return image;
  }

  public int[][] updateMatrix(int[][] matrix) {
    if(matrix==null || matrix.length==0 || matrix[0].length==0){
      return null;
    }

    Queue<Point> queue = new LinkedList<>();
    int row = matrix.length;
    int col = matrix[0].length;

    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        if(matrix[i][j]==0){
          queue.add(new Point(i,j));
        }else{
          matrix[i][j] = Integer.MAX_VALUE;
        }
      }
    }

    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
    while(!queue.isEmpty()){
      Point pos = queue.remove();
      for(int[] dir:dirs){
        int newRow = pos.x+dir[0];
        int newCol = pos.y+dir[1];
        if(newRow<0 || newRow>=matrix.length || newCol<0 || newCol>=matrix[0].length){
          continue;
        }

        if(matrix[newRow][newCol]> (matrix[pos.x][pos.y]+1)){
          matrix[newRow][newCol] = matrix[pos.x][pos.y]+1;
          queue.add(new Point(newRow,newCol));
        }
      }
    }

    return matrix;
  }

  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    if(rooms==null || rooms.isEmpty()){
      return true;
    }
    int size = rooms.size();
    boolean[] visited = new boolean[size];
    LinkedList<Integer> stack = new LinkedList<>();
    stack.push(0);
    while(!stack.isEmpty()){
      int pos = stack.pop();
      visited[pos] = true;
      List<Integer> keys = rooms.get(pos);
      for(Integer key : keys){
        if(!visited[key]){
          stack.push(key);
        }
      }
    }

    for(boolean flag : visited){
      if(!flag){
        return false;
      }
    }

    return true;
  }
}
