package lab3;

import java.util.Arrays;

public class ReverseStack {
  public static <T> ArrayStack<T> reverseA(ArrayStack<T> stack) {
    ArrayStack<T> stack2 = new ArrayStack<>();
    while (!stack.isEmpty()) stack2.push(stack.pop());
    return stack2;
  }

  public static <T> void reverseB(ArrayStack<T> stack) {
    int n = stack.size();
    for (int i = 0; i < n-1; i++)
      moveBottomToTop(stack, 0, i);
  }

  public static <T> T moveBottomToTop(ArrayStack<T> stack, int i, int insertIndex) {
    T popped = stack.pop();
    if (stack.isEmpty()) return popped;
    T ret = moveBottomToTop(stack, i + 1, insertIndex);
    stack.push(popped);
    if (i == insertIndex) {
      stack.push(ret);
    }
    return ret;
  }

  public static void main(String[] args) {
    ArrayStack<Integer> stack = new ArrayStack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    ArrayStack<Integer> stack2 = reverseA(stack);

    while (!stack2.isEmpty()) System.out.println(stack2.pop());
    System.out.println();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    reverseB(stack);
    while (!stack.isEmpty()) System.out.println(stack.pop());
  }
}
