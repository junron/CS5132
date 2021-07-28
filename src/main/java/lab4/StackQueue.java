package lab4;

public class StackQueue<T> implements QueueADT<T> {
  private LinkedStack<T> s1 = new LinkedStack<>();
  private LinkedStack<T> s2 = new LinkedStack<>();
  private int count;

  @Override
  public void enqueue(T element) {
    s1.push(element);
    count++;
  }

  @Override
  public T dequeue() {
    if (isEmpty()) throw new EmptyCollectionException("queue");
    if (s2.isEmpty()) {
      move();
    }
    count--;
    return s2.pop();
  }

  // Move s1 into s2 (reverse order)
  private void move() {
    while (!s1.isEmpty()) {
      s2.push(s1.pop());
    }
  }

  @Override
  public T first() {
    if (isEmpty()) throw new EmptyCollectionException("queue");
    if (s2.isEmpty()) {
      move();
    }
    return s2.peek();
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public int size() {
    return count;
  }
}
