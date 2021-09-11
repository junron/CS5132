package sorting;

import java.util.Arrays;

public class MergeSort {
  public static void main(String[] args) {
    Integer[] numArr = {91, 7, 2, 38, 31, 76, 12, 15, 12, 3};
    System.gc();

    long startTime = System.nanoTime();

    Integer[] resultArr = genericSort(numArr);

    long elapsedTime = System.nanoTime() - startTime;
    System.out.println("Sorted integer array: " + Arrays.toString(resultArr));
    System.out.println("Time elapsed: " + elapsedTime + " nano seconds\n");
  }

  // State what sorting algorithm the below method implements as a comment
  // Mergesort
  // DO NOT CHANGE THE FOLLOWING METHOD NAME 
  public static <T extends Comparable<? super T>> T[] genericSort(T[] data) {
    // Implement your sorting algorithm below
    mergeSort(data, 0, data.length);
    return data;
  }

  public static <T extends Comparable<? super T>> void mergeSort(T[] data, int left, int right) {
    int size = right - left;
    // If array is a single element or is empty, it is already sorted
    if (size <= 1) {
      return;
    }
    int mid = size / 2 + left;
    mergeSort(data, left, mid);
    mergeSort(data, mid, right);
    merge(data, left, mid - left, right - mid);
  }

  // Merges 2 sorted arrays of length size, with the first starting at start and the second starting at start + size
  private static <T extends Comparable<? super T>> void merge(T[] data, int start, int size1, int size2) {
    int i = 0;
    int j = 0;

    int writeIndex = 0;
    T[] temp = (T[]) new Comparable[size1 + size2];
    while (i < size1 || j < size2) {
      // Ensure i doesn't exceed array bounds
      int iIndex = Math.min(i, size1 - 1);
      // Ensure j doesn't exceed array bounds
      int jIndex = Math.min(j, size2 - 1);
      // If we're done with array 1, copy elements from array 2
      if (iIndex != i) {
        temp[writeIndex] = data[start + jIndex + size1];
        j++;
        writeIndex++;
        continue;
      }
      // If we're done with array 2, copy elements from array 1
      if (jIndex != j) {
        temp[writeIndex] = data[start + iIndex];
        i++;
        writeIndex++;
        continue;
      }
      // Copy the smaller of the fronts of the two arrays to the back of the temp array
      if (data[start + iIndex].compareTo(data[start + jIndex + size1]) <= 0) {
        // First array front is smaller than second array
        temp[writeIndex] = data[start + iIndex];
        i++;
      } else {
        // Second array front is smaller 
        temp[writeIndex] = data[start + jIndex + size1];
        j++;
      }
      writeIndex++;
    }

    // Copy temp elements back to actual array
    System.arraycopy(temp, 0, data, start, temp.length);
  }
}
