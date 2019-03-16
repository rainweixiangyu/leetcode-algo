package wei.xiangyu.staticTest;

import java.time.LocalDateTime;

public abstract class Father {
  protected static final String HELLO = new String("Hello World!");

  public void print(){
    System.out.println(HELLO);
  }
}
