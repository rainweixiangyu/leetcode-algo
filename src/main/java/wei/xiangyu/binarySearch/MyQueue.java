package wei.xiangyu.binarySearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {

  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    int count = 0;
    int row = grid.length;
    int col = grid[0].length;
    boolean[][] visited = new boolean[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == '1' && !visited[i][j]) {
          count++;
          bfs(grid, visited, i, j);
        }
      }
    }

    return count;
  }

  public void bfs(char[][] grid, boolean[][] visited, int row, int col) {
    if ((row >= 0) && (row < grid.length) &&
            (col >= 0) && (col < grid[0].length) &&
            (!visited[row][col]) && (grid[row][col] == '1')
    ) {
      visited[row][col] = true;
    } else {
      return;
    }

    bfs(grid, visited, row + 1, col);
    bfs(grid, visited, row - 1, col);
    bfs(grid, visited, row, col + 1);
    bfs(grid, visited, row, col - 1);
  }

  public int openLock(String[] deadends, String target) {
    Queue<String> queue = new LinkedList<>();
    HashSet<String> set = new HashSet<String>(Arrays.asList(deadends));
    queue.add("0000");
    int step = -1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      step++;
      for (int i = 0; i < size; i++) {
        String cur = queue.poll();
        if (set.contains(cur)) continue;
        if (cur.compareTo(target) == 0) return step;
        set.add(cur);
        for (int j = 0; j < 4; j++) {
          for (int k = -1; k < 2; k += 2) {
            char[] temp = cur.toCharArray();
            temp[j] = (char)((temp[j] - '0' + k + 10) % 10 + '0');
            queue.add(new String(temp));
          }
        }
      }
    }
    return -1;
  }

  public int numSquares(int n) {
    int[] result = new int[n+1];
    result[0] = 0;
    result[1] = 1;

    for(int i=2; i<=n; i++){
      int min = Integer.MAX_VALUE;
      for(int j=1; j<i; j++){
        min = Math.min(min, result[j]+result[i-j]);
      }
      result[i] = min;
    }

    return result[n];
  }

  public static void main(String[] args){
    String ele = "0000";
    String result = (char)(ele.charAt(0)+1)+ele.substring(1);
    System.out.println("(ele.charAt(0)+1):"+(ele.charAt(0)+1));
    System.out.println("ele.substring(1):"+ele.substring(1));
    System.out.println("Result:"+result);
  }
}
