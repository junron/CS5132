1.

```java
public static void Q1a(int n) {
	int a = 0;
	for(int i=0; i<n; i++)
		a += Q1b(n);
	System.out.println(a);
}

public static int Q1b(int n) {
	int b = 0;
	while(n>0) {
		b += n;
		n = n / 2;
	}
	return b;
}
```

The `while` loop in `Q1b` is executed $\log_2n$ times. The time complexity of `Q1b` is thus O(n). The for loop in `Q1b` is executed n times. Thus the time complexity of `Q1a` is `O(nlogn)` .



2.
$$
\begin{align}
\text{Number of operations} &= \sum\limits_{i=0}^n \sum\limits_{k=0}^n 1\\
&= \sum\limits_{i=0}^n (n)\\
&= n^2
\end{align}
$$
3.

At the `i`th iteration of the loop, $x=2^{i-1}$, thus when `x=n` $i-1=\log_2n$. Thus the time complexity is $O(\log_n)$

4.

Loop 2 only executes once since `j=i+1` will not be greater than `i` when decremented. In loop 3, `j=i+1` thus the loop will execute `n-j=n-i-1` times.
$$
\begin{align}
\text{Number of operations} &= \sum\limits_{i=0}^n (n-i-1)\\
&= \sum\limits_{i=0}^n (n-1) - \sum\limits_{i=0}^n i\\
&= (n+1)(n-1) - \frac{n(n+1)}{2}\\
&= (n+1)(n-1-\frac{n}{2})\\
&= (n+1)(\frac{n}{2}-1)
\end{align}
$$
Thus the time complexity is `O(n^2)`



Review 1;
$$
\begin{align}
\sum\limits_{j=0}^{n-2}(n-j-1) &= \sum\limits_{j=0}^{n-2}n-\sum\limits_{j=0}^{n-2}(j+1)\\
&= n(n-1) - (n-1+\frac{(n-2)(n-1)}{2})\\
&= n(n-1) - (n-1)(1+\frac{n-2}{2})\\
&= (n-1)(n-1-\frac{n-2}{2})\\
&= \frac{1}{2}(n-1)(2n-2-(n-2))\\
&= \frac{1}{2}(n-1)(n)\\
&= \frac{n^2-n}{2}\\
\end{align}
$$
