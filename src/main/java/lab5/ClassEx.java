package lab5;

import java.util.Arrays;

public class ClassEx {
  public static void main(String[] args) {
    displayInBase(31, 2);
    System.out.println();
    System.out.println(sumSquares(5, 10));
    int[] arr = new int[]{1, 2, 3, 4};
    rev(arr, 0, arr.length-1);
    System.out.println(Arrays.toString(arr));
  }

  public static void displayInBase(int n, int base) {
    if (n == 0) {
      System.out.print("0");
      return;
    }
    if (n / base > 0) displayInBase(n / base, base);
    System.out.print(n % base);
  }

  public static int sumSquares(int m, int n) {
    if (n == m) {
      return m * m;
    }
    return m * m + sumSquares(m + 1, n);
  }

  public static void rev(int[] array, int start, int finish) {
    if (start >= finish || array.length < 2) {
      return;
    }
    int temp = array[finish];
    array[finish] = array[start];
    array[start] = temp;
    rev(array, start + 1, finish - 1);
  }

  // public static void reverse(int[] array, int tart, int finih) {
  //   int[] front = new int[tart];
  //   for (int i = 0; i < tart; i++) {
  //     front[i] = (array[i]);
  //   }
  //   int[] bck = new int[finih - tart];
  //   for (int i = 0; i < finih - tart; i++) {
  //     bck[i] = (array[i + finih - tart]);
  //   }
  //   int[] mid = new int[];
  //
  // }
}
