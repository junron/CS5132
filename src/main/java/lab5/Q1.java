package lab5;


public class Q1 {
  public static int forwardcombi(int n, int k) {
    if (k == 0 || n==k) {
      return 1;
    }
    // either choose or don't choose
    return forwardcombi(n-1, k) + forwardcombi(n-1, k-1);
  }

  public static void main(String[] args) {
    System.out.println(forwardcombi(35, 23));
  }

}
