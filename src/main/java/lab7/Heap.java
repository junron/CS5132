package lab7;

import java.util.Arrays;

public class Heap<T extends Comparable<? super T>> {
  T[] arr = (T[]) new Comparable[10];
  int size = 0;

  public T findMax() {
    return arr[0];
  }

  public void addElement(T elem) {
    if (size == arr.length) expandCapacity();
    arr[size] = elem;
    size++;
    if (size > 1) heapify(size - 1);
  }

  private void heapify(int i) {
    T elem = arr[i];
    T parent = arr[(i - 1) / 2];
    // Child smaller than parent, ok
    if (elem.compareTo(parent) < 1) return;
    // Swap
    arr[i] = parent;
    arr[(i - 1) / 2] = elem;
    heapify((i - 1) / 2);
  }

  public T removeMax() {
    T max = arr[0];
    size--;
    arr[0] = arr[size];
    arr[size] = null;
    heapifyDown(0);
    return max;
  }


  private void heapifyDown(int i) {
    T elem = arr[i];
    T left = (2 * i + 1) >= size ? null : arr[2 * i + 1];
    T right = (2 * i + 2) >= size ? null : arr[2 * i + 2];
    if ((left == null || left.compareTo(elem) < 0) && (right == null || right.compareTo(elem) < 0)) return;
    // Swap max
    // Left greater than right, swap left
    if (left != null && (right == null || left.compareTo(right) > 0)) {
      arr[i] = left;
      arr[2 * i + 1] = elem;
      heapifyDown(2 * i + 1);
    } else {
      // Swap right
      arr[i] = right;
      arr[2 * i + 2] = elem;
      heapifyDown(2 * i + 2);
    }
  }


  private void expandCapacity() {
    arr = Arrays.copyOf(arr, arr.length * 2);
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public static void main(String[] args) {
    Heap<Integer> integerHeap = new Heap<>();
    integerHeap.addElement(1);
    integerHeap.addElement(2);
    integerHeap.addElement(3);
    integerHeap.addElement(4);
    // integerHeap.addElement(5);
    // integerHeap.addElement(6);
    // integerHeap.addElement(7);
    System.out.println(Arrays.toString(integerHeap.arr));
    while (!integerHeap.isEmpty()) System.out.println(integerHeap.removeMax());
  }
}
