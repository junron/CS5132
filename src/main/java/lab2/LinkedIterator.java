package lab2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedIterator<T> implements Iterator<T> {
  private LinearNode<T> current;
  private int count;

  public LinkedIterator(LinearNode<T> current, int count) {
    this.current = current;
    this.count = count;
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public T next() {
    if(!hasNext())
      throw new NoSuchElementException();
    T next = current.getElement();
    current = current.getNext();
    return next;
  }
}
