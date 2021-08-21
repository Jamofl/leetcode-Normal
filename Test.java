import java.util.*;


public class Test {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        LinkedList<Integer> q = new LinkedList(); // queue里面存的是元素的下标

        while (r < k){
            offer(q, nums, nums[r]);
            r ++;
        }
        ans[l] = q.getFirst();

        while(r <= n - 1){
            if (nums[l] == q.getFirst())
                q.removeFirst();
            offer(q, nums, nums[r]);
            r ++;
            l ++;
            ans[l] = q.getFirst();
        }
        return ans;
    }

    private void offer(LinkedList<Integer> lst, int[] nums, int n){
        while(lst.size() != 0 && lst.getLast() < n){
            lst.removeLast();
        }
        lst.add(n);
    }
    public static void main(String[] args){
        Test t = new Test();
        int[] r = t.maxSlidingWindow(new int[] {1,3,1,2,0,5}, 3);
        System.out.println(Arrays.toString(r));
    }
}
