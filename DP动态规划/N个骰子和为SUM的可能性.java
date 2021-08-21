package DP动态规划;

public class N个骰子和为SUM的可能性 {
    public int getSumByNCount(int n, int sum){
        if (n <= 0 || sum < n || sum > 6 * n)
            return 0;
        if (n == 1)
            return 1;

        return getSumByNCount(n - 1, sum - 1) + getSumByNCount(n - 1, sum - 2) +
                getSumByNCount(n - 1, sum - 3) + getSumByNCount(n - 1, sum - 4) +
                getSumByNCount(n - 1, sum - 5) + getSumByNCount(n - 1, sum - 6);
    }

    public static void main(String[] args){
        N个骰子和为SUM的可能性 n = new N个骰子和为SUM的可能性();
        int r = n.getSumByNCount(2,4);
        System.out.println(r);
    }
}
