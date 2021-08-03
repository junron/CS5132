package lab5;

import java.util.Stack;

public class HardCopy {
  public static void main(String[] args) {
    Stack<Integer> s = new Stack<Integer>();
    s.push(1);
    s.push(2);
    s.push(4);
    // insert(s, 3);
    enqueue(s, 10);
    dequeue(s);
    while (!s.isEmpty()) System.out.println(s.pop());
    
  }

  public static void insert(Stack<Integer> stack, int val) {
    if (stack.peek() < val) {
      stack.push(val);
      return;
    }
    int pop = stack.pop();
    insert(stack, val);
    stack.push(pop);
  }
  
  public static void enqueue(Stack<Integer> stack, int val){
    if(stack.isEmpty()){
      stack.push(val);
      return;
    }
    int pop = stack.pop();
    enqueue(stack, val);
    stack.push(pop);
  }
  
  public static int dequeue(Stack<Integer> stack){
    return stack.pop();
  }
  
}
