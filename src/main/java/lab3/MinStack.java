package lab3;

import java.util.Arrays;

public class MinStack<E extends Comparable<E>> {

  private ArrayStack<E> stack = new ArrayStack<>();
  private ArrayStack<E> minStack = new ArrayStack<>();
  private E min;

  //complete the code required for MinStack to work
  public void push(E element) {
    if (min == null || element.compareTo(min) < 0) {
      min = element;
      minStack.push(element);
    }
    stack.push(element);
  }


  public E peek() {
    return stack.peek();
  }

  public E pop() {
    E res = stack.pop();
    if(res == min()){
      minStack.pop();
    }
    return res;
  }

  public E min() {
    return minStack.peek();
  }
}

