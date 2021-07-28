package lab2;

import java.util.Iterator;

public class HeadLinkedList<T> implements ListADT<T> {
  private int count;
  private LinearNode<T> head;

  /**
   * Creates an empty list.
   */
  public HeadLinkedList() {
    count = 0;
    head = null;
  }

  /**
   * Removes the first element in this list and returns a reference
   * to it.  Throws an EmptyListException if the list is empty.
   *
   * @return a reference to the first element of
   * this list
   * @throws EmptyCollectionException if an empty collection exception occurs
   */
  public T removeFirst() throws EmptyCollectionException {
    if (isEmpty()) throw new EmptyCollectionException("List");

    LinearNode<T> result = head;
    head = head.getNext();
    count--;

    return result.getElement();
  }

  /**
   * Removes the last element in this list and returns a reference
   * to it.  Throws an EmptyListException if the list is empty.
   *
   * @return the last element in this list
   * @throws EmptyCollectionException if an empty collection exception occurs
   */
  public T removeLast() throws EmptyCollectionException {
    if (isEmpty()) throw new EmptyCollectionException("List");

    LinearNode<T> previous = null;
    LinearNode<T> current = head;

    while (current.getNext() != null) {
      previous = current;
      current = current.getNext();
    }

    previous.setNext(null);
    LinearNode<T> result = current;
    count--;

    return result.getElement();
  }

  @Override
  public Iterator<T> iterator() {
    return new LinkedIterator<>(head, count);
  }

  /**
   * Removes the first instance of the specified element from this
   * list and returns a reference to it.  Throws an EmptyListException
   * if the list is empty.  Throws a NoSuchElementException if the
   * specified element is not found in the list.
   *
   * @param targetElement the element to be removed from the list
   * @return a reference to the removed element
   * @throws EmptyCollectionException if an empty collection exception occurs
   */
  public T remove(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
    if (isEmpty()) throw new EmptyCollectionException("List");

    boolean found = false;
    LinearNode<T> previous = null;
    LinearNode<T> current = head;

    while (current != null && !found) if (targetElement.equals(current.getElement())) found = true;
    else {
      previous = current;
      current = current.getNext();
    }

    if (!found) throw new ElementNotFoundException("List");

    if (size() == 1)              //list has only 1 element
      head = null;
    else if (current.equals(head))      //delete first node (head)
      head = current.getNext();
    else if (current.getNext() == null)      //delete last node (tail)
    {
      previous.setNext(null);
    } else                    //delete middle node
      previous.setNext(current.getNext());

    count--;

    return current.getElement();
  }

  /**
   * Returns true if the specified element is found in this list and
   * false otherwise.  Throws an EmptyListException if the specified
   * element is not found in the list.
   *
   * @param targetElement the element that is sought in the list
   * @return true if the element is found in
   * this list
   * @throws EmptyCollectionException if an empty collection exception occurs
   */
  public boolean contains(T targetElement) throws EmptyCollectionException {
    if (isEmpty()) throw new EmptyCollectionException("List");

    boolean found = false;
    Object result;

    LinearNode<T> current = head;

    while (current != null && !found) if (targetElement.equals(current.getElement())) found = true;
    else current = current.getNext();

    return found;
  }

  /**
   * Returns true if this list is empty and false otherwise.
   *
   * @return true if this list is empty
   */
  public boolean isEmpty() {
    return (count == 0);
  }

  /**
   * Returns the number of elements in this list.
   *
   * @return the integer representation of the number of elements in this list
   */
  public int size() {
    return count;
  }


  public LinearNode<T> getHead() {
    return head;
  }

  /**
   * Returns a string representation of this list.
   *
   * @return a string representation of this list
   */
  public String toString() {
    LinearNode<T> current = head;
    String result = "";

    while (current != null) {
      result = result + (current.getElement()).toString(); //+ "\n";
      current = current.getNext();
    }

    return result;
  }

  /**
   * Returns the first element in this list.
   *
   * @return the first element in this list
   */
  public T first() {
    return head.getElement();
  }

  /**
   * Returns the last element in this list.
   *
   * @return the last element in this list
   */
  public T last() {
    LinearNode<T> current = head;
    while (current.getNext() != null) {
      current = current.getNext();
    }
    return current.getElement();
  }

  public LinearNode<T> tail() {
    LinearNode<T> current = head;
    while (current.getNext() != null) {
      current = current.getNext();
    }
    return current;
  }

  /**
   * Adds element after target element.
   */
  public void addAfter(T element, T targetElement) throws ElementNotFoundException {
    LinearNode<T> current = head;
    while (!current.getElement().equals(targetElement)) {
      current = current.getNext();
      if (current == null) {
        throw new ElementNotFoundException("list");
      }
    }
    LinearNode<T> node = new LinearNode<>(element, current.getNext());
    current.setNext(node);
    count++;
  }

  public void addToRear(T element) {
    LinearNode<T> node = new LinearNode<>(element, null);
    if (isEmpty()) {
      head = node;
    } else {
      LinearNode<T> last = this.tail();
      last.setNext(node);
    }
    count++;
  }


  public void addToFront(T element) {
    head = new LinearNode<T>(element, head);
    count++;
  }

  // H -> E -> A -> P
  // H <- E A -> P
  // H <- E <- A P
  // H <- E <- A <- P
  public void reverse() {
    // Empty or 1 element list is it's own reverse
    if (isEmpty() || size() == 1) {
      return;
    }
    LinearNode<T> curr = this.head.getNext();
    LinearNode<T> prev = this.head;
    // Prev will become new tail node
    prev.setNext(null);
    while (curr.getNext() != null) {
      LinearNode<T> next = curr.getNext();
      curr.setNext(prev);
      prev = curr;
      curr = next;
    }
    curr.setNext(prev);
    this.head = curr;
  }
  
  // Reverse a HeadLinkedList
  public void reverse2() {
    if(isEmpty() || size() == 1) {
      return;
    }
    LinearNode<T> current = head;
  }
  

  public HeadLinkedList<T> frontBackSplit() {
    if (isEmpty() || this.head.getNext() == null) {
      return this;
    }
    HeadLinkedList<T> b = new HeadLinkedList<>();
    LinearNode<T> p1 = this.head;
    LinearNode<T> p2 = p1.getNext();
    while (p2 != null && p2.getNext() != null) {
      p1 = p1.getNext();
      b.addToFront(removeFirst());
      p2 = p2.getNext();
      if (p2 == null) {
        break;
      }
      p2 = p2.getNext();
    }
    b.addToFront(removeFirst());
    b.reverse();
    return b;
  }


}
