package lab5;

import java.util.Arrays;

public class Sudoku {
  public static void main(String[] args) {
    int[][] board = {{0, 6, 0, 1, 0, 4, 0, 5, 0}, {0, 0, 8, 3, 0, 5, 6, 0, 0}, {2, 0, 0, 0, 0, 0, 0, 0, 1}, {8, 0, 0,
            4, 0, 7, 0, 0, 6}, {0, 0, 6, 0, 0, 0, 3, 0, 0}, {7, 0, 0, 9, 0, 1, 0, 0, 4}, {5, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 7, 2, 0, 6, 9, 0, 0}, {0, 4, 0, 5, 0, 8, 0, 7, 0}};
    if (!solve(board, 0, 0)) {
      System.out.println("Not solve");
      return;
    }
    for(int[] row : board){
      System.out.println(Arrays.toString(row));
    }
  }

  public static boolean isValid(int[][] board, int x, int y, int val) {
    // Check columns and rows
    for (int i = 0; i < 9; i++) {
      if (board[i][x] == val || board[y][i] == val) {
        return false;
      }
    }
    int i = (x / 3) * 3;
    int j = (y / 3) * 3;
    for (int a = 0; a < 3; a++) {
      for (int b = 0; b < 3; b++) {
        if (board[j + a][i + b] == val) {
          return false;
        }
      }
    }
    return true;
  }

  public static boolean solve(int[][] board, int x, int y) {
    if (board[y][x] != 0) {
      if (x == 8) {
        return solve(board, 0, y + 1);
      }
      return solve(board, x + 1, y);
    }
    for (int val = 0; val < 9; val++) {
      if (isValid(board, x, y, val + 1)) {
        board[y][x] = val + 1;
        if (x == y && x == 8) {
          return true;
        }
        boolean ret;
        if (x == 8) {
          ret = solve(board, 0, y + 1);
        } else {
          ret = solve(board, x + 1, y);
        }
        if (ret) return true;
        board[y][x] = 0;
      }
      board[y][x] = 0;
    }
    return false;
  }
}
