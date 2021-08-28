package lab7;

import java.util.Arrays;

public class HeapSort {
  public static <T extends Comparable<T>> void heapSort(T[] arr) {
    for (int i = 1; i < arr.length; i++) {
      heapify(arr, i);
    }
    for (int i = 0; i < arr.length - 1; i++) {
      System.out.println(Arrays.toString(arr));
      T temp = arr[arr.length - 1 - i];
      arr[arr.length - 1 - i] = arr[0];
      arr[0] = temp;
      heapifyDown(arr, 0, arr.length - 1 - i);
    }
  }

  private static <T extends Comparable<T>> void heapify(T[] arr, int i) {
    T elem = arr[i];
    T parent = arr[(i - 1) / 2];
    // Child larger than or equal to parent, ok
    if (elem.compareTo(parent) <= 0) return;
    // Swap
    arr[i] = parent;
    arr[(i - 1) / 2] = elem;
    heapify(arr, (i - 1) / 2);
  }

  private static <T extends Comparable<T>> void heapifyDown(T[] arr, int i, int size) {
    T elem = arr[i];
    T left = (2 * i + 1) >= size ? null : arr[2 * i + 1];
    T right = (2 * i + 2) >= size ? null : arr[2 * i + 2];
    if ((left == null || left.compareTo(elem) < 0) && (right == null || right.compareTo(elem) < 0)) return;
    // Swap max
    // Left greater than right, swap left
    if (left != null && (right == null || left.compareTo(right) > 0)) {
      arr[i] = left;
      arr[2 * i + 1] = elem;
      heapifyDown(arr, 2 * i + 1, size);
    } else {
      // Swap right
      arr[i] = right;
      arr[2 * i + 2] = elem;
      heapifyDown(arr, 2 * i + 2, size);
    }
  }

  public static void main(String[] args) {
    Integer[] arr = {7, 2, 26, 25, 19, 17, 1, 90, 3, 36};
    heapSort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
