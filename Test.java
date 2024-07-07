import java.util.*;


public class Test {


    // 状态转移方程 f(n) = min(f(i) + 1)   i = (1, 2 ... sqrt(n))
//    for (int j = 1; j <= (int) Math.sqrt(n); j ++){
//        f[n] = Math.min(f[n], f(n - j * j) + 1);


    /**
     * 8
     * 1 2
     * 8 - 4 = 4
     *
     * 10
     * 1 2 3
     * 10 - 1 = 9
     * 10 - 4 = 5
     * 10 - 9 = 1
     * @param n
     * @return
     */
    Set<Integer> squares = new HashSet<>();
    public int numSquares(int n) {
        for (int j = 1; j <= (int) Math.sqrt(n); j ++){
            squares.add(j * j);
        }
        for (int k = 1; k <= n; k ++){
            if (numSquareHelper(n , k)){
                return k;
            }
        }
        return 0;

    }

    public boolean numSquareHelper(int n, int k){
        if (k == 1){
           if (squares.contains(n)){
               return true;
           }
           else{
               return false;
           }
        }
       else {
           for (int square : squares){
               if (numSquareHelper(n - square, k - 1)){
                   return true;
               }
           }
           return false;
       }
    }

    public boolean isSquare(int n) {
        if ((int)Math.sqrt(n) * (int)Math.sqrt(n) == n) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.numSquares(12));
    }

}
