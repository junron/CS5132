package sorting;

import java.util.Arrays;

public class CountingSort {
  public static void sort(int[] items) throws Exception {
    int[] counts = new int[10];
    int[] output = new int[items.length];

    for (int i : items) {
      if (i > 9) throw new Exception("Succ");
      counts[i]++;
    }
    for (int i = 1; i < counts.length; i++) {
      counts[i] += counts[i - 1];
    }

    for (int i : items) {
      // Counts number of items of lower index before it
      // Subtract 1 cos 0 index
      int newIndex = counts[i] - 1;
      output[newIndex] = i;
      // Decrement so other items of same value will be placed correctly
      counts[i]--;
    }
    System.arraycopy(output, 0, items, 0, items.length);
  }

  public static void main(String[] args) throws Exception {
    int[] items = new int[]{4, 3, 2, 1, 1, 2, 3, 4};
    sort(items);
    System.out.println(Arrays.toString(items));
  }
}
