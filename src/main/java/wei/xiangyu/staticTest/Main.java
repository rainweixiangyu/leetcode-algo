package wei.xiangyu.staticTest;

public class Main {
  public static void main(String[] args){
    Father father1 = new Son();
    father1.print();

    Father father2 = new Daughter();
    father2.print();
  }
}
