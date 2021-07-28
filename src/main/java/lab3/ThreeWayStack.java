package lab3;

public class ThreeWayStack<T> {
  int length = 12;
  private final T[] arr = (T[]) new Object[length];
  private final int[] stackIndexes = new int[]{0, length / 3, length / 3 * 2};

  public void push(T element, int stackNumber) {
    arr[stackIndexes[stackNumber]] = element;
    stackIndexes[stackNumber]++;
  }

  public T pop(int stackNumber) {
    stackIndexes[stackNumber]--;
    T ret = arr[stackIndexes[stackNumber]];
    arr[stackIndexes[stackNumber]] = null;
    return ret;
  }

  public boolean isEmpty(int stackNumber) {
    return stackIndexes[stackNumber] == 0;
  }

  public static void main(String[] args) {
    ThreeWayStack<Integer> stack = new ThreeWayStack<>();
    stack.push(1, 0);
    stack.push(2, 1);
    stack.push(3, 2);
    for (int i = 0; i < 3; i++) {
      System.out.println("Printing for " + i);
      while (!stack.isEmpty(i)) {
        System.out.println(stack.pop(i));
      }
      System.out.println();
    }
  }
}
