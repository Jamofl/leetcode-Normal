/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */

public class Q922 {
    public static int[] sortArrayByParityII(int[] A) {
        int[] toReturn = new int[A.length];
        int indexEven = 0;
        int indexOdd = 1;
        for(int i : A){
            if(i % 2 == 0){
                toReturn[indexEven] = i;
                indexEven += 2;
            }
            else{
                toReturn[indexOdd] = i;
                indexOdd += 2;
            }
        }
        return toReturn;
    }
    public static void main(String[] args){
        int[] lst = new int[]{2,4,1,7,9,10};
        int[] re = sortArrayByParityII(lst);
    }
}
