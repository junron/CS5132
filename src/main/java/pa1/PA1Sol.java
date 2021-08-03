package pa1;

import java.util.Scanner;
import java.util.Stack;

public class PA1Sol {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Read parameters
    int size = scanner.nextInt();
    double width = scanner.nextDouble();

    // Read data
    double[] arr = new double[size];
    for (int i = 0; i < size; i++) {
      arr[i] = scanner.nextDouble();
    }

    System.out.println(getLargestArea(arr, width, size));
  }

  // O(n^2) solution.
  // for each building height, calculate the area by iterating over the buildings until there's a building that's 
  // shorter
  public static double getLargestAreaN2(double[] arr, double width, int size) {
    double maxArea = 0;
    for (int i = 0; i < size; i++) {
      int currentNumberOfBuildings = 0;
      double currentHeight = arr[i];
      // Iterate over buildings from the current to the end
      for (int j = i; j < size; j++) {
        // Stop when the building height is less than the current height.
        // cannot put flag anymore
        if (arr[j] < currentHeight) {
          break;
        }
        currentNumberOfBuildings++;
      }
      // Iterate over buildings from the current to the front
      for (int j = i; j >= 0; j--) {
        // Stop when the building height is less than the current height.
        // cannot put flag anymore
        if (arr[j] < currentHeight) {
          break;
        }
        currentNumberOfBuildings++;
      }
      // -1 cos double counted current building
      double currentArea = currentHeight * (currentNumberOfBuildings - 1) * width;
      if (currentArea > maxArea) {
        maxArea = currentArea;
      }
    }
    return maxArea;
  }

  public static double getLargestArea(double[] arr, double width, int size) {
    double[] area = new double[size];
    // Contains a stack of buildings shorter than the current one
    Stack<Integer> minIndexStack = new Stack<>();

    // Stage 1 (left area)
    for (int i = 0; i < size; i++) {
      double height = arr[i];
      // Start from -1 since building 0 is a real building
      int leftIndex = -1;
      // Find building smaller than current, compacting all buildings in between
      while (!minIndexStack.isEmpty()) {
        if (arr[minIndexStack.peek()] < height) {
          leftIndex = minIndexStack.peek();
          break;
        }
        minIndexStack.pop();
      }
      area[i] = height * (i - leftIndex) * width;
      // Replace popped larger/same size buildings with current
      minIndexStack.push(i);
    }

    // Cleanup
    while (!minIndexStack.isEmpty()) minIndexStack.pop();

    // Stage 2 (right area)
    // This has a lot of similarity to left area but I decided not to abstract it out
    // because there are significant differences in the initial rightIndex and iteration direction
    for (int i = size - 1; i >= 0; i--) {
      double height = arr[i];
      int rightIndex = size;
      while (!minIndexStack.isEmpty()) {
        if (arr[minIndexStack.peek()] < height) {
          rightIndex = minIndexStack.peek();
          break;
        }
        minIndexStack.pop();
      }
      // -1 because we  double counted the current building
      area[i] += height * (rightIndex - i - 1) * width;
      minIndexStack.push(i);
    }

    return max(area);
  }

  // Computes maximum value in list (assumes elements are positive)
  private static double max(double[] arr) {
    double max = 0;
    for (double v : arr) {
      if (v > max) {
        max = v;
      }
    }
    return max;
  }

}
