// 给定两个可能非常大的数（有可能long 也无法存储） 返回和的字符串表示

package Array数组;

public class BigNumSum {

    public String bigNumSum(String num1, String num2){
        int n1 = num1.length();
        int n2 = num2.length();
        int n = (n1 > n2) ? n1 + 1 : n2 + 1;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int[] ans = new int[n];
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < n1; i ++)
            arr1[i] = num1.charAt(n1 - 1 - i) - '0';
        for (int i = 0; i < n2; i ++)
            arr2[i] = num2.charAt(n2 - 1 - i) - '0';

        for (int i = 0; i < n; i ++){
            ans[i] = carry + arr1[i] + arr2[i];
            if (ans[i] >= 10){
                carry = ans[i] / 10;
                ans[i] = ans[i] % 10;
            }
            else
                carry = 0;
        }

        for (int i = n - 1; i >= 0; i --){
            if (i == n - 1 && ans[i] == 0)
                continue;
            sb.append(ans[i]);
        }
        return sb.toString();



//        String numShort = (num1.length() > num2.length()) ? num2 : num1;
//        String numLong = (num1.length() > num2.length()) ? num1 : num2;
//        int nShort = numShort.length();
//        int nLong = numLong.length();
//        int[] arrShort = new int[nShort];
//        int[] arrLong = new int[nLong];
//        int[] ans = new int[nLong + 1];
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < nShort; i ++)
//            arrShort[i] = numShort.charAt(nShort - i - 1) - '0';
//
//        for (int i = 0; i <nLong; i ++)
//            arrLong[i] = numLong.charAt(nLong - i - 1) - '0';
//
//        int carry = 0;
//        for (int i = 0; i < nShort; i ++){
//            ans[i] = arrShort[i] + arrLong[i] + carry;
//            if (ans[i] >= 10){
//                carry = ans[i] / 10;
//                ans[i] = ans[i] % 10;
//            }
//            else
//                carry = 0;
//        }
//
//        for (int i = nShort; i < nLong; i ++){
//            ans[i] = arrLong[i] + carry;
//            if (ans[i] > 10){
//                carry = ans[i] / 10;
//                ans[i] = ans[i] % 10;
//            }
//            else
//                carry = 0;
//        }
//        ans[nLong] = carry;
//
//        for (int i = nLong; i >= 0; i--){
//            if (i == nLong && ans[i] == 0)
//                continue;
//            sb.append(String.valueOf(ans[i]));
//        }
//        return sb.toString();
    }

    public static void main(String[] args){
        BigNumSum b = new BigNumSum();
        String s1 = "12343534535456456";
        String s2 = "3456745";
        String re = b.bigNumSum(s1, s2);
        System.out.println("actual: " + re);
        System.out.println("expect: " + (Long.parseLong(s1) + Long.parseLong(s2)));
    }
}
