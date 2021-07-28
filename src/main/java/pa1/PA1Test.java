package pa1;

import java.util.Arrays;
import java.util.Random;

public class PA1Test {
  private static Random random = new Random();

  public static void main(String[] args) {
    int runs = 1_000000;
    int size = 5;
    // double[][] arr = new double[runs][size];
    // for (int i = 0; i < runs; i++) {
    //   arr[i] = randomDoubleArr(size);
    // }
    final long startTime = System.currentTimeMillis();
    for (int i = 0; i < runs; i++) {
      double[] arr = randomDoubleArr(size);
      // 3973
      double area2 = PA1Sol.getLargestArea(arr, 1.0, size);
      // 6997
      // PA1Sol.getLargestAreaN2(arr, 1.0, size);
      double area = PA1Sol.getLargestAreaN2(arr, 1.0, size);
      if (area != area2) {
        System.out.println(area);
        System.out.println(area2);
        System.out.println(Arrays.toString(arr));
        break;
      }
    }
    final long endTime = System.currentTimeMillis();

    System.out.println("Total execution time: " + (endTime - startTime));
    System.out.println(Double.MAX_VALUE / 3 * 100);
  }

  private static double[] randomDoubleArr(int size) {
    double[] arr = new double[size];
    for (int i = 0; i < size; i++) {
      arr[i] = random.nextInt(10) + 1;
    }
    return arr;
  }
}
