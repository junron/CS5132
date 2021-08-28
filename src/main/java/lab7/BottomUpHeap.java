package lab7;

import java.util.Arrays;

public class BottomUpHeap<T extends Comparable<? super T>> {
  T[] arr;
  int size = 0;

  public BottomUpHeap(T[] elements) {
    arr = (T[]) new Comparable[elements.length];
    size = elements.length;
    mergeHeaps(elements, 0, 0, size);
  }

  public T findMax() {
    return arr[0];
  }

  private void mergeHeaps(T[] elements, int heapIndex, int kIndex, int size) {
    T k = elements[kIndex];
    // System.out.println(k + " " + size);
    arr[heapIndex] = k;
    if (size <= 3) {
      arr[2 * heapIndex + 1] = elements[kIndex + 1];
      System.out.println(heapIndex + " " + kIndex + " " + k);
      if (elements.length > kIndex + 2) arr[2 * heapIndex + 2] = elements[kIndex + 2];
    } else {
      int newSize = (size - 1) / 2;
      // left heap
      mergeHeaps(elements, 2 * heapIndex + 1, kIndex + 1, newSize);
      // right heap
      mergeHeaps(elements, 2 * heapIndex + 2, kIndex + 3 + newSize / 2, newSize);
    }
    heapifyDown(heapIndex);
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

  public boolean isEmpty() {
    return size == 0;
  }

  public static void main(String[] args) {
    // BottomUpHeap<Integer> integerHeap = new BottomUpHeap<>(new Integer[]{1, 2, 3, 4});
    BottomUpHeap<Integer> integerHeap = new BottomUpHeap<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
    System.out.println("Done");
    System.out.println(integerHeap.findMax());
    System.out.println(Arrays.toString(integerHeap.arr));
    while (!integerHeap.isEmpty()) System.out.println(integerHeap.removeMax());
  }
}
