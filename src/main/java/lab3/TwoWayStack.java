package lab3;


public class TwoWayStack<E> implements TwoWayStackADT<E> {
  private E[] array = (E[]) new Object[10];
  private int topCount = 0;
  private int bottomCount = 0;

  @Override
  public boolean topEmpty() {
    return topCount == 0;
  }

  @Override
  public boolean bottomEmpty() {
    return bottomCount == 0;
  }

  @Override
  public E peekTop() {
    if (topEmpty()) throw new EmptyCollectionException("top stack");
    return array[topCount - 1];
  }

  @Override
  public E peekBottom() {
    if (bottomEmpty()) throw new EmptyCollectionException("bottom stack");
    return array[array.length - bottomCount];
  }

  @Override
  public void pushTop(E item) {
    if (checkExpandArray(topCount)) {
      expandCapacity();
    }
    array[topCount] = item;
    topCount++;
  }

  @Override
  public void pushBottom(E item) {
    if (checkExpandArray(array.length - bottomCount - 1)) {
      expandCapacity();
    }
    array[array.length - bottomCount - 1] = item;
    bottomCount++;
  }

  public boolean checkExpandArray(int index) {
    if (index >= array.length) return true;
    return array[index] != null;
  }

  public void expandCapacity() {
    E[] newArray = (E[]) new Object[array.length * 2];

    // Copy top stack
    for (int i = 0; i < topCount; i++) {
      newArray[i] = array[i];
    }

    // Copy bottom stack
    for (int i = 0; i < bottomCount; i++) {
      newArray[newArray.length - i - 1] = array[array.length - i - 1];
    }
    array = newArray;
  }

  @Override
  public E popTop() {
    E res = peekTop();
    array[topCount - 1] = null;
    topCount--;
    return res;
  }

  @Override
  public E popBottom() {
    E res = peekBottom();
    array[array.length - bottomCount] = null;
    bottomCount--;
    return res;
  }

  public static void main(String[] args) {
    TwoWayStack<String> mystacks = new TwoWayStack<String>();
    mystacks.pushBottom("bottom");
    mystacks.pushBottom("the");
    mystacks.pushBottom("through");
    mystacks.pushBottom("push");
    mystacks.pushBottom("stack");
    mystacks.pushBottom("one");
    mystacks.pushBottom("is");
    mystacks.pushBottom("This");
    mystacks.pushTop("top");
    mystacks.pushTop("the");
    mystacks.pushTop("from");
    mystacks.pushTop("stack");
    mystacks.pushTop("other");
    mystacks.pushTop("the");
    mystacks.pushTop("is");
    mystacks.pushTop("This");

    while (!mystacks.bottomEmpty()) {
      System.out.print(mystacks.popBottom() + " ");
    }
    System.out.println();

    while (!mystacks.topEmpty()) {
      System.out.print(mystacks.popTop() + " ");
    }
  }
}
