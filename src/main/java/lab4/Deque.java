package lab4;


public class Deque<T> implements DequeADT<T> {
  private static final int DEFAULT_SIZE = 10;
  private T[] array = (T[]) new Object[DEFAULT_SIZE];
  private int count = 0;
  private int first = DEFAULT_SIZE / 2;
  private int last = first - 1;

  @Override
  public int size() {
    return count;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public T getFirst() {
    if (isEmpty()) throw new EmptyCollectionException("deque");
    return array[first];
  }

  @Override
  public T getLast() {
    if (isEmpty()) throw new EmptyCollectionException("deque");
    return array[last];
  }

  @Override
  public void addFirst(T element) {
    if (first == 0) {
      resize();
    }
    array[first - 1] = element;
    first--;
    count++;
  }

  @Override
  public void addLast(T element) {
    if (last + 1 == array.length) {
      resize();
    }
    array[last + 1] = element;
    last++;
    count++;
  }

  @Override
  public T removeFirst() {
    if (isEmpty()) throw new EmptyCollectionException("deque");
    T res = array[first];
    array[first] = null;
    first++;
    count--;
    return res;
  }

  @Override
  public T removeLast() {
    if (isEmpty()) throw new EmptyCollectionException("deque");
    T res = array[last];
    array[last] = null;
    last--;
    count--;
    return res;
  }

  private void resize() {
    T[] newArray = (T[]) new Object[array.length + DEFAULT_SIZE * 2];
    for (int i = 0; i < array.length; i++) {
      newArray[DEFAULT_SIZE + i] = array[i];
    }
    first += DEFAULT_SIZE;
    last += DEFAULT_SIZE;
    array = newArray;
  }

  public static void main(String[] args) {
    Deque<String> d = new Deque<>();
    for (int i = 0; i < 10; i++) {
      d.addLast("hello");
    }
    System.out.println(d.removeFirst());
  }
}
