// brute force
// tc: O((mn)^2) sc: O(1)

public class Solution {

  public int maximalSquare(char[][] matrix) {
    int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
    int maxsqlen = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (matrix[i][j] == '1') {
          int sqlen = 1;
          boolean flag = true;
          while (sqlen + i < rows && sqlen + j < cols && flag) {
            for (int k = j; k <= sqlen + j; k++) {
              if (matrix[i + sqlen][k] == '0') {
                flag = false;
                break;
              }
            }
            for (int k = i; k <= sqlen + i; k++) {
              if (matrix[k][j + sqlen] == '0') {
                flag = false;
                break;
              }
            }
            if (flag) sqlen++;
          }
          if (maxsqlen < sqlen) {
            maxsqlen = sqlen;
          }
        }
      }
    }
    return maxsqlen * maxsqlen;
  }
}

// DP approach
// tc: O(mn) sc: O(mn)

public class Solution {

  public int maximalSquare(char[][] matrix) {
    int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
    int[][] dp = new int[rows + 1][cols + 1];
    int maxsqlen = 0;
    // for convenience, we add an extra all zero column and row
    // outside of the actual dp table, to simpify the transition
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        if (matrix[i - 1][j - 1] == '1') {
          dp[i][j] =
            Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) +
            1;
          maxsqlen = Math.max(maxsqlen, dp[i][j]);
        }
      }
    }
    return maxsqlen * maxsqlen;
  }
}

// DP approach - better
// tc: O(mn) sc: O(n)

public class Solution {

  public int maximalSquare(char[][] matrix) {
    int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
    int[] dp = new int[cols + 1];
    int maxsqlen = 0, prev = 0;
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        int temp = dp[j];
        if (matrix[i - 1][j - 1] == '1') {
          dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
          maxsqlen = Math.max(maxsqlen, dp[j]);
        } else {
          dp[j] = 0;
        }
        prev = temp;
      }
    }
    return maxsqlen * maxsqlen;
  }
}
