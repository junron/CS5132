package lab5;

public class Q2 {
	public static int tailcombihelper(int n,int k, double accumulator){
		if(k==0 || n==k){
		  return (int) accumulator;
    }
		return tailcombihelper(n-1,k-1, accumulator * n/(double)k);
	}
	
	
	public static int tailcombi(int n,int k){
		return tailcombihelper(n,k,1);
	}
	
	public static void main(String[] args) {
		System.out.println(tailcombi(35,23));//834451800
	}

}
