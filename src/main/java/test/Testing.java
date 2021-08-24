package test;

import java.util.Arrays;
import java.util.Stack;

public class Testing {
  public static int fibo(int n) {
    if (n == 0 || n == 1) return n;
    return fibo(n - 1) + fibo(n - 2);
  }

  public static void stalin(int[] arr) {
    int write = 0;
    int read = 0;
    int curr = Integer.MIN_VALUE;
    while (read < arr.length) {
      if (arr[read] < curr) {
        arr[read] = 0;
        read++;
        continue;
      } else {
        curr = arr[read];
        arr[write] = curr;
        write++;
      }
      read++;
    }
  }

  public static int binarySearch(int[] arr, int target) {
    int high = arr.length;
    int low = 0;
    int mid;
    while (high > low) {
      mid = (high + low) / 2;
      if (arr[mid] == target) return mid;
      // Guess too small, search upper
      if (arr[mid] < target) {
        low = mid + 1;
      } else {
        // Guess too large, search lower
        high = mid - 1;
      }
    }
    return -1;
  }

  public static String toPostfix(String infix) {
    StringBuilder out = new StringBuilder();
    Stack<Character> s = new Stack<>();
    for(char c : infix.toCharArray()){
      if(c == '+' || c == '-'){
        while (!s.isEmpty() && s.peek() != '('){
          out.append(s.pop());
        }
        s.push(c);
      }else if(c == '*' || c == '/'){
        while (!s.isEmpty() && s.peek() != '(' && s.peek() != '-' && s.peek()!='+'){
          out.append(s.pop());
        }
        s.push(c);
      }else if(c=='('){
        s.push(c);
      }else if(c==')'){
        while(!s.isEmpty()){
          char pop = s.pop();
          if(pop == '(') break;
          out.append(pop);
        }
      }else{
        out.append(c);
      }
    }
    while (!s.isEmpty()) out.append(s.pop());
    return out.toString();
  }

  public static int binarySearchRec(int[] arr, int target, int high, int low) {
    int mid = (high + low) / 2;
    if (low > high) return -1;
    if (arr[mid] == target) return mid;
    if (arr[mid] < target) return binarySearchRec(arr, target, high, mid + 1);
    return binarySearchRec(arr, target, mid - 1, low);
  }

  public static void main(String[] args) {
    System.out.println(fibo(8));
    int[] arr = new int[]{1, 2, 3, 1, 2, 4, 4, 5, 6};
    int[] arr2 = new int[]{1, 2, 3, 4, 5, 6};
    stalin(arr);
    System.out.println(Arrays.toString(arr));
    System.out.println(binarySearch(arr2, 8));
    System.out.println(binarySearchRec(arr2, 8, arr2.length - 1, 0));
    System.out.println(toPostfix("(a+b-c)*d-(e+f)"));
  }
}
