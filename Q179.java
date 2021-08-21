import java.text.CollationElementIterator;
import java.util.*;

/**
 * 给定一组非负整数 nums，重新排列它们每个数字的顺序（每个数字不可拆分）使之组成一个最大的整数。
 *
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 示例 1：
 *
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 *
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 *
 */

public class Q179 {


    private int getWeight(String s){
        if (s.length() == 1)
            return Integer.parseInt(s);
        else
            return Integer.parseInt(String.valueOf(s.charAt(0)));
    }

    private int compareHelper(String s, String t){

        int maxLen = (s.length() >= t.length()) ? s.length() : t.length();

        List<Character> sList = new LinkedList<>();
        List<Character> tList = new LinkedList<>();
        for(char c : s.toCharArray())
            sList.add(c);
        for(char c : t.toCharArray())
            tList.add(c);
        for(int i = s.length(); i < maxLen; i ++)
            sList.add(s.charAt(0));
        for(int i = t.length(); i < maxLen; i ++)
            tList.add(t.charAt(0));

        for(int i = 0; i < maxLen; i ++){
            if (sList.get(i) > tList.get(i))
                return -1;
            else if (sList.get(i) > tList.get(i))
                return 1;
        }
        return 0;
    }


    private int combinationCompare(String s, String t){
        String comb1 = s + t;
        String comb2 = t + s;
        List<Character> comb1List = new LinkedList<>();
        List<Character> comb2List = new LinkedList<>();
        for(char c : comb1.toCharArray())
            comb1List.add(c);
        for(char c : comb2.toCharArray())
            comb2List.add(c);
        for(int i = 0 ; i < comb1List.size(); i ++){
            if (comb1List.get(i) > comb2List.get(i))
                return 1;
            else if (comb1List.get(i) < comb2List.get(i))
                return -1;
        }
        return 0;
    }

    public String largestNumber(int[] nums) {
        if (nums.length == 1)
            return String.valueOf(nums[0]);

        List<String> lst = new LinkedList<>();
        String ans = "";
        for(int i : nums)
            lst.add(String.valueOf(i));

        Collections.sort(lst, (String a, String b) -> (b + a).compareTo(a + b));
        Collections.sort(lst, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return 0;
            }
            }
        );
        /*
        Collections.sort(lst, new Comparator<String>() {
            @Override
//            public int compare(String s, String t) {
////                if(combinationCompare(s, t) > 0)
////                    return -1;
////                else if(combinationCompare(s, t) < 0)
////                    return 1;
////                else
////                    return 0;
//                String comb1 = s + t;
//                String comb2 = t + s;
//                return comb2.compareTo(comb1);
//            }
            public int compare = (String a, String b) -> (b + a).compareTo(a + b);


        });
        */
        for(String s : lst)
            ans += s;

        if (ans.charAt(0) == '0')
            ans = "0";
        return ans;
    }

    public static void main(String[] args){
//        int[] nums = new int[]{432,43243};
//        Q179 q = new Q179();
//        String re = q.largestNumber(nums);
//        System.out.println(re);
        System.out.println("23".compareTo("29"));

    }


}
