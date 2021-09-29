package sorting;

import java.util.Arrays;

public class LSDRadix {
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
  // LSD radix sort
  // DO NOT CHANGE THE FOLLOWING METHOD NAME 
  public static <T extends Comparable<? super T>> T[] genericSort(T[] data) {
    // Implement your sorting algorithm below
    Integer[] dataArr = (Integer[]) data;
    int maxVal = max(dataArr);
    int numDigits = (int) Math.floor(Math.log10(maxVal)) + 1;
    for (int i = 0; i < numDigits; i++) {
      LSDSort(dataArr, i);
    }
    return (T[]) dataArr;
  }

  public static int max(Integer[] items) {
    int max = Integer.MIN_VALUE;
    for (int i : items) {
      max = Math.max(i, max);
    }
    return max;
  }

  public static void LSDSort(Integer[] items, int digit) {
    int div = (int) Math.pow(10, digit);
    int[] counts = new int[10];
    Integer[] output = new Integer[items.length];

    for (int i : items) {
      counts[(i / div) % 10]++;
    }
    for (int i = 1; i < counts.length; i++) {
      counts[i] += counts[i - 1];
    }


    for (int i = items.length - 1; i >= 0; i--) {
      // Counts number of items of lower index before it
      // Subtract 1 cos 0 index
      int item = items[i];
      int newIndex = counts[(item / div) % 10] - 1;
      output[newIndex] = item;
      // Decrement so other items of same value will be placed correctly
      counts[(item / div) % 10]--;
    }
    System.arraycopy(output, 0, items, 0, items.length);
  }
}

