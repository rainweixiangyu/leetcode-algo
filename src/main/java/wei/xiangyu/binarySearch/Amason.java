package wei.xiangyu.binarySearch;

import java.util.*;

import wei.xiangyu.binarySearch.Recursion.ListNode;

public class Amason {
  public int firstUniqChar(String s) {
    if (s == null || s.isEmpty()) {
      return -1;
    }

    char[] charArray = s.toCharArray();
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < charArray.length; i++) {
      String ele = charArray[i] + "";
      if (map.containsKey(ele)) {
        map.put(ele, Integer.MAX_VALUE);
      } else {
        map.put(ele, i);
      }
    }

    Integer minPos = Integer.MAX_VALUE;
    for (Integer ele : map.values()) {
      if (minPos > ele) {
        minPos = ele;
      }
    }

    if (minPos == Integer.MAX_VALUE) {
      return -1;
    }

    return minPos;
  }

  public void reverseWords(char[] str) {
    if (str == null || str.length == 0) {
      return;
    }

    reverse(str, 0, str.length - 1);
    int start = 0;
    int end = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == ' ') {
        end = i - 1;
        reverse(str, start, end);
        start = i + 1;
      }
    }

    end = str.length - 1;
    reverse(str, start, end);
  }

  public void reverse(char[] str, int start, int end) {
    while (start < end) {
      char tmp = str[start];
      str[start] = str[end];
      str[end] = tmp;
      start++;
      end--;
    }
  }

  public int[] twoSum(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return new int[]{-1, -1};
    }

    Arrays.sort(nums);
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      if (nums[start] + nums[end] == target) {
        return new int[]{start, end};
      } else if (nums[start] + nums[end] < target) {
        start++;
      } else {
        end--;
      }
    }

    return new int[]{-1, -1};
  }

  public int maxSubArrayLen(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 0);
    int sum = 0;
    int max = 0;

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      int lookfor = sum - k;
      if (map.containsKey(lookfor)) {
        max = Math.max(max, i - map.get(lookfor));
      }

      if (!map.containsKey(sum)) {
        map.put(sum, i);
      }
    }

    return max;
  }

  public static int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");

    int i = 0;
    while (i < v1.length && i < v2.length) {
      if (Integer.parseInt(v1[i]) < Integer.parseInt(v2[i])) {
        return -1;
      } else if (Integer.parseInt(v1[i]) > Integer.parseInt(v2[i])) {
        return 1;
      } else {
        i++;
      }
    }

    while (i < v1.length) {
      if (Integer.parseInt(v1[i]) > 0) {
        return 1;
      } else {
        i++;
      }
    }

    while (i < v2.length) {
      if (Integer.parseInt(v2[i]) > 0) {
        return -1;
      } else {
        i++;
      }
    }

    return 0;
  }

  public String numberToWords(int num) {
    String result = convertHundred(num % 1000);
    String[] level = new String[]{"Thousand", "Million", "Billion"};
    int i = 0;
    while (num / 1000 > 0) {
      num = num / 1000;
      if (num % 1000 > 0) {
        if (result.isEmpty()) {
          result = convertHundred(num % 1000) + " " + level[i];
        } else {
          result = convertHundred(num % 1000) + " " + level[i] + " " + result;
        }
      }
      i++;
    }

    result.trim();

    return result.isEmpty() ? "Zero" : result;
  }

  public String convertHundred(int num) {
    String[] nums = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    int hundred = num / 100;
    int left = num % 100;
    int sigle = num % 10;
    StringBuilder result = new StringBuilder();
    result.append(nums[hundred]);
    result.append(nums[hundred] == "" ? "" : " " + "Hundred");
    if (hundred > 0 && left > 0) {
      result.append(" ");
    }

    if (left < 20) {
      result.append(nums[left]);
    } else if (sigle > 0) {
      result.append(tens[left / 10] + " " + nums[sigle]);
    } else {
      result.append(tens[left / 10]);
    }

    return result.toString();
  }

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }

    int size = lists.length;
    while (size > 1) {
      int k = (size + 1) / 2;
      for (int i = 0; i < size / 2; i++) {
        lists[i] = mergeTwoLists(lists[i], lists[i + k]);
      }
      size = k;
    }

    return lists[0];
  }

  public ListNode mergeTwoLists(ListNode first, ListNode second) {
    if (first == null) {
      return second;
    }

    if (second == null) {
      return first;
    }
    ListNode head;
    if (first.val < second.val) {
      head = first;
      first = first.next;
    } else {
      head = second;
      second = second.next;
    }

    ListNode pre = head;
    while (first != null && second != null) {
      if (first.val < second.val) {
        pre.next = first;
        first = first.next;
      } else {
        pre.next = second;
        second = second.next;
      }
      pre = pre.next;
    }

    if (first != null) {
      pre.next = first;
    }

    if (second != null) {
      pre.next = second;
    }

    return head;
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public int closestValue(TreeNode root, double target) {
    if (root == null) {
      return -1;
    }

    int value = root.val;
    double min = Math.abs(value - target);
    while (root != null) {
      if (target == root.val) {
        return root.val;
      } else if (target > root.val) {
        if (min > Math.abs(root.val - target)) {
          min = Math.abs(root.val - target);
          value = root.val;
        }
        root = root.right;
      } else {
        if (min > Math.abs(root.val - target)) {
          min = Math.abs(root.val - target);
          value = root.val;
        }
        root = root.left;
      }
    }

    return value;
  }

  public int sumNumbers(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int sum = sumSubTree(root, 0);

    return sum;
  }

  public int sumSubTree(TreeNode root, int preSum) {
    int sum = preSum * 10 + root.val;
    if (root.left != null && root.right != null) {
      return sumSubTree(root.left, sum) + sumSubTree(root.right, sum);
    } else if (root.left != null) {
      return sumSubTree(root.left, sum);
    } else if (root.right != null) {
      return sumSubTree(root.right, sum);
    } else {
      return sum;
    }
  }


  public TreeNode constructMaximumBinaryTree(int[] nums) {
    if (nums == null || nums.length == 0) {
      return null;
    }

    return constructTree(nums, 0, nums.length - 1);
  }

  public TreeNode constructTree(int[] nums, int start, int end) {
    TreeNode root;
    if (start > end) {
      return null;
    }
    if (start == end) {
      return new TreeNode(nums[start]);
    }

    int maxPos = findMaxValuePos(nums, start, end);
    root = new TreeNode(nums[maxPos]);
    if (start < maxPos) {
      root.left = constructTree(nums, start, maxPos - 1);
    }
    if (maxPos < end) {
      root.right = constructTree(nums, maxPos + 1, end);
    }

    return root;
  }

  public int findMaxValuePos(int[] nums, int start, int end) {
    int max = nums[start];
    int pos = start;
    for (int i = start + 1; i <= end; i++) {
      if (max < nums[i]) {
        max = nums[i];
        pos = i;
      }
    }

    return pos;
  }

  public boolean findTarget(TreeNode root, int k) {
    if (root == null) {
      return false;
    }

    Integer[] nums = inorderTree(root);
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      if (nums[start] + nums[end] == k) {
        return true;
      } else if (nums[start] + nums[end] < k) {
        start++;
      } else {
        end--;
      }
    }

    return false;
  }

  public Integer[] inorderTree(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    TreeNode current = root;
    List<Integer> result = new LinkedList<>();

    while (!stack.isEmpty() || current != null) {
      if (current != null) {
        stack.push(current);
        current = current.left;
      } else {
        current = stack.pop();
        result.add(current.val);
        current = current.right;
      }
    }
    Integer[] nums = new Integer[result.size()];
    return result.toArray(nums);
  }

  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
      return true;
    } else if (s == null || t == null) {
      return false;
    }

    if (isSameTree(s, t)) {
      return true;
    }

    return isSameTree(s.left, t) || isSameTree(s.right, t);
  }

  public boolean isSameTree(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
      return true;
    } else if (s == null || t == null) {
      return false;
    } else if (s.val != t.val) {
      return false;
    } else {
      return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
  }

  public int[] productExceptSelf(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new int[]{};
    }

    int len = nums.length;

    int[] left = new int[len];
    left[0] = nums[0];
    for (int i = 1; i < len; i++) {
      left[i] = left[i - 1] * nums[i];
    }

    int[] right = new int[len];
    right[len - 1] = nums[len - 1];
    for (int j = len - 2; j >= 0; j--) {
      right[j] = right[j + 1] * nums[j];
    }

    int[] result = new int[len];
    result[0] = right[1];
    result[len - 1] = left[len - 2];
    for (int k = 1; k < len - 1; k++) {
      result[k] = left[k - 1] * right[k + 1];
    }

    return result;
  }

  public class TreeNodeCol {
    TreeNode root;
    int col;

    public TreeNodeCol(TreeNode root, int col) {
      this.col = col;
      this.root = root;
    }
  }

  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> result = new LinkedList<>();
    if (root == null) {
      return result;
    }

    int min = 0;
    int max = 0;
    Queue<TreeNodeCol> queue = new LinkedList<>();
    Map<Integer, List<Integer>> map = new HashMap<>();
    TreeNodeCol rootTree = new TreeNodeCol(root, 0);
    queue.add(rootTree);
    while (queue.size() != 0) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNodeCol ele = queue.poll();
        Integer col = ele.col;
        if (map.containsKey(col)) {
          map.get(col).add(ele.root.val);
        } else {
          List<Integer> list = new LinkedList<>();
          list.add(ele.root.val);
          map.put(col, list);
          if (col > max) {
            max = col;
          }

          if (col < min) {
            min = col;
          }
        }

        if (ele.root.left != null) {
          TreeNodeCol left = new TreeNodeCol(ele.root.left, ele.col - 1);
          queue.add(left);
        }

        if (ele.root.right != null) {
          TreeNodeCol right = new TreeNodeCol(ele.root.right, ele.col + 1);
          queue.add(right);
        }
      }
    }

    for (int i = min; i <= max; i++) {
      if (map.containsKey(i)) {
        result.add(map.get(i));
      }
    }

    return result;
  }

  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null) {
      return null;
    }

    LinkedList<TreeNode> stack = new LinkedList<>();
    TreeNode current = root;
    boolean flag = false;
    while (!stack.isEmpty() || current != null) {
      if (current != null) {
        stack.push(current);
        current = current.left;
      } else {
        current = stack.pop();
        if (flag) {
          return current;
        }
        if (current.val == p.val) {
          flag = true;
        }
        current = current.right;
      }
    }

    return null;
  }


  public List<String> letterCombinations(String digits) {
    List<String> result = new LinkedList<>();
    if (digits == null || digits.isEmpty()) {
      return result;
    }

    char[] digitA = digits.toCharArray();

    Map<Character, Character[]> record = new HashMap<>();
    record.put('2', new Character[]{'a', 'b', 'c'});
    record.put('3', new Character[]{'d', 'e', 'f'});
    record.put('4', new Character[]{'g', 'h', 'i'});
    record.put('5', new Character[]{'j', 'k', 'l'});
    record.put('6', new Character[]{'m', 'n', 'o'});
    record.put('7', new Character[]{'p', 'q', 'r', 's'});
    record.put('8', new Character[]{'t', 'u', 'v'});
    record.put('9', new Character[]{'w', 'x', 'y', 'z'});

    return combinedStrings(digitA, 0, record);
  }

  public List<String> combinedStrings(char[] digits, int pos, final Map<Character, Character[]> record) {
    List<String> result = new LinkedList<>();
    if (digits.length == pos) {
      result.add("");
      return result;
    }

    for (Character myChar : record.get(digits[pos])) {
      for (String ele : combinedStrings(digits, pos + 1, record)) {
        String newEle = myChar + ele;
        result.add(newEle);
      }
    }
    return result;
  }


  public boolean knows(int a, int b) {
    return true;
  }

  public int findCelebrity(int n) {
    int cadidate = 0;
    //每一次比较只有两种情况，knows(a, b)是true的话，那么a肯定不是candidate; knows(a, b)是false的话，那么b肯定不是candidate.
    // 所以candidate之前之后的都不是，然后我们再验证candidate是不是正解。
    for (int i = 1; i < n; i++) {
      if (knows(cadidate, i)) {
        cadidate = i;
      }
    }

    for (int i = 0; i < n; i++) {
      if (cadidate != i && (!knows(i, cadidate) || knows(cadidate, i))) {
        return -1;
      }
    }

    return cadidate;
  }

  public boolean validTree(int n, int[][] edges) {
    if (edges == null) {
      return false;
    }

    if ((n - 1) != edges.length) {
      return false;
    }

    int[] roots = new int[n];
    for (int i = 0; i < n; i++) {
      roots[i] = -1;
    }

    for (int[] edge : edges) {
      int x = findRoot(edge[0], roots);
      int y = findRoot(edge[1], roots);
      if (x == y) {
        return false;
      } else {
        roots[x] = y;
      }
    }

    return true;
  }

  public int findRoot(int node, int[] roots) {
    while (roots[node] != -1) {
      node = roots[node];
    }

    return node;
  }

  public String alienOrder(String[] words) {
    return null;
  }

  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    List<Integer> result = new LinkedList<>();
    if (positions == null || positions.length == 0) {
      return result;
    }

    int[][] root = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        root[i][j] = -1;
      }
    }

    int islandCount = 0;
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int[] pos : positions) {
      int row = pos[0];
      int col = pos[1];
      int num = row * n + col;
      if(root[row][col] == -1){
        root[row][col] = num;
        islandCount++;
      }

      for (int[] dir : dirs) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        if(newRow<0 || newRow>=m || newCol<0 || newCol>=n || root[newRow][newCol]==-1){
          continue;
        }
        int x = find(root, row, col, n);
        int y = find(root, newRow, newCol, n);
        if(x != y){
          root[y/n][y%n] = x;
          islandCount--;
        }
      }

      result.add(islandCount);
    }

    return result;
  }

  public int find(int[][] root, int row, int col, int len){
    int num = row*len+col;
    while(root[row][col] != num){
      num = root[row][col];
      row = num/len;
      col = num%len;
    }

    return num;
  }

  public int findKthLargest(int[] nums, int k) {
    if(nums==null || nums.length<k){
      return -1;
    }

    return findKthEle(nums,0,nums.length-1,k);
  }

  public int findKthEle(int[] nums,int start,int end,int target){
    if(start == end){
      return nums[start];
    }

    int current = nums[end];
    int pos = start-1;
    for(int i=start; i<end; i++){
      if(nums[i] <= current){
        int tmp = nums[i];
        nums[i] = nums[pos+1];
        nums[pos+1] = tmp;
        pos++;
      }
    }

    if(pos+1==target){
      return current;
    }else if(pos+1 > target){
      return findKthEle(nums,start,pos-1,target);
    }else{
      return findKthEle(nums,pos+1,end,target-pos-1);
    }
  }

  public int[] maxSlidingWindow(int[] nums, int k) {
    if(nums==null || nums.length==0 || nums.length<k){
      return new int[]{};
    }

    int[] result = new int[nums.length-k+1];
    if(k==1){
      return nums;
    }

    int max = nums[0];
    int pos = 0;
    for(int i=1; i<nums.length;i++){
      if(max < nums[i]){
        max = nums[i];
        pos = i;
      }

      if(i>=k-1){
        if(pos <= i-k){
          pos = findMaxPos(nums,i-k+1,i);
          max = nums[pos];
        }

        result[i-k+1] = max;
      }
    }

    return result;
  }

  public int findMaxPos(int[] nums,int start,int end){
    int max = nums[start];
    int pos = start;
    for(int i=start+1; i<=end; i++){
      if(nums[i] > max){
        max = nums[i];
        pos = i;
      }
    }

    return pos;
  }

  //ZigZag Conversion
  public String convert(String s, int numRows){
    String[] strs = new String[numRows];
    for(int i=0; i<numRows; i++){
      strs[i] = new String("");
    }

    if(numRows == 1){
      return s;
    }

    boolean direction = true;
    char[] sChar = s.toCharArray();
    int row = 0;
    for(int i=0; i<sChar.length; i++){
      String ele = sChar[i]+"";
      if(direction){
        strs[row] += ele;
        row++;

        if(row == numRows){
          direction = false;
          row = numRows-2;
        }
      }else{
        strs[row]+=ele;
        row--;
        if(row == -1){
          direction = true;
          row = 1;
        }
      }
    }

    for(int i=0; i<strs.length; i++){
      System.out.println(strs[i]);
    }

    String result = strs[0];
    for(int i=1; i<strs.length; i++){
      result += strs[i];
    }

    return result;
  }

  public String minWindow(String s, String t) {
    if(s==null){
      return null;
    }

    if(t==null || s.length()<t.length()){
      return "";
    }

    HashMap<Character,Integer> map = new HashMap<>();
    char[] tArray = t.toCharArray();
    for(int i=0;i<tArray.length;i++){
      char ele = tArray[i];
      if(map.containsKey(ele)){
        map.put(ele,map.get(ele)+1);
      }else{
        map.put(ele,1);
      }
    }

    char[] sArray = s.toCharArray();
    int min = Integer.MAX_VALUE;
    int pos = 0;
    int start = 0;
    while(start<sArray.length && !map.containsKey(sArray[start])){
      start++;
    }

    if(sArray.length-start<tArray.length){
      return "";
    }

    for(int i=start;i<sArray.length;i++){
      char ele = sArray[i];
      if(map.containsKey(ele)){
        map.put(ele,map.get(ele)-1);
        while(isCoverAll(map)){
          int len = i-start+1;
          if(min > len){
            min = len;
            pos = i-len+1;
          }

          char startEle = sArray[start];
          if(map.containsKey(startEle)){
            map.put(startEle,map.get(startEle)+1);
          }
          start++;
          while(start<sArray.length && !map.containsKey(sArray[start])){
            start++;
          }
        }
      }
    }

    if(min == Integer.MAX_VALUE){
      return "";
    }

    return s.substring(pos,pos+min);
  }

  public boolean isCoverAll(HashMap<Character,Integer> map){
    for(Integer value : map.values()){
      if(value > 0){
        return false;
      }
    }

    return true;
  }


  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new LinkedList<>();
    if (candidates == null || candidates.length == 0) {
      return result;
    }

    result = combinedSum(candidates, 0, target);
    if(result==null){
      return new LinkedList<>();
    }

    return result;
  }

  public static List<List<Integer>> combinedSum(int[] candidates, int start, int target) {
    List<List<Integer>> result = new LinkedList<>();
    int num = candidates[start];

    if(start == candidates.length-1){
      if(target%num==0){
        List<Integer> last = new LinkedList<>();
        while(target>=num){
          last.add(num);
          target -= num;
        }
        result.add(last);
        return result;
      }else{
        return null;
      }
    }

    for(int i=0; i*num<=target;i++){
      List<List<Integer>> pre = combinedSum(candidates,start+1,target-i*num);
      if(pre!=null){
        for(List<Integer> ele : pre){
          List<Integer> newEle = new LinkedList<>();
          for(int j=0;j<i;j++){
            newEle.add(num);
          }
          newEle.addAll(ele);
          result.add(newEle);
        }
      }
    }

    return result;
  }


  public class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
  }

  public int minMeetingRooms(Interval[] intervals) {
    if(intervals==null || intervals.length==0){
      return 0;
    }

    int[] node = new int[intervals.length*2];
    int i = 0;
    for(Interval ele : intervals){
      node[i++] = ele.start;
      node[i++] = ele.end;
    }

    Arrays.sort(node);
    int max = 0;
    int start = node[0];
    int end;
    for(int j=1; j<node.length; j++){
      end = node[j];
      if(start != end) {
        int count = 0;
        for (Interval ele : intervals) {
          if (start >= ele.start && end <= ele.end) {
            count++;
          }
        }
        if (max < count) {
          max = count;
        }
      }
      start = end;
    }

    return max;
  }

  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> result = new LinkedList<>();
    if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0 || wordList.size() == 0) {
      return result;
    }

    List<String> pre = new LinkedList<>();
    pre.add(beginWord);
    Set<String> visited = new HashSet<>();
    find(pre, endWord, wordList,result,visited);

    int min = Integer.MAX_VALUE;
    for(List<String> ele : result){
      if(min > ele.size()){
        min = ele.size();
      }
    }

    List<List<String>> res = new LinkedList<>();
    for(int i=0; i<result.size();i++){
      List<String> ele = result.get(i);
      if(ele.size() == min){
        res.add(ele);
      }
    }

    return res;
  }

  public void find(List<String> pre, String target, List<String> wordList, List<List<String>> result,Set<String> visited) {
    String last = pre.get(pre.size() - 1);
    if (last.equals(target)) {
      result.add(pre);
      return;
    }

    List<String> nextString = new LinkedList<>();
    for (String word : wordList) {
      if (!visited.contains(word) && isOnlyOneDiff(last, word)) {
        nextString.add(word);
      }
    }
    //add visited
    visited.addAll(nextString);

    for(String word : nextString){
      List<String> newPre = new LinkedList<>();
      newPre.addAll(pre);
      newPre.add(word);
      find(newPre,target,wordList,result,visited);
    }

    //remove added
    for(String word : nextString){
      visited.remove(word);
    }
  }

  public boolean isOnlyOneDiff(String last, String word){
    int diffCount = 0;
    for(int i=0; i<last.length(); i++){
      if(last.charAt(i) != word.charAt(i)){
        diffCount++;
        if(diffCount>1){
          return false;
        }
      }
    }

    if(diffCount == 1){
      return true;
    }else{
      return false;
    }
  }

  public static int[] maxSlidingWindow2(int[] nums, int k) {
    if(nums==null || nums.length==0){
      return new int[]{};
    }

    if(nums.length < k){
      return new int[]{};
    }

    int[] result = new int[nums.length-k+1];
    Deque<Integer> deque = new ArrayDeque<>();
    for(int i=0;i<nums.length;i++){
      //当前面最大值无效，即超出范围时，从queue中去除该最大值
      if(!deque.isEmpty() && (i-k)==deque.getFirst()){
        deque.pollFirst();
      }
      //保证queue中最开始的位置一直记录最大值
      while(!deque.isEmpty() && nums[i]>nums[deque.getLast()]){
        deque.pollLast();
      }
      deque.addLast(i);

      //从queue的首位置获取当前最大值
      if(i>=k-1){
        result[i-k+1] = nums[deque.getFirst()];
      }
    }

    return result;
  }

  public static void main(String[] args){
    int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
    int k = 3;
    int[] result;

    result = maxSlidingWindow2(nums,k);

    for(int ele : result){
      System.out.println(ele);
    }
  }
}
