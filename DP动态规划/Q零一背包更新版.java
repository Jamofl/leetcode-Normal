package DP动态规划;

/*
现在有三件商品，价值分别为 15 30 20，重量分别为1 4 3；
有一个大小为4的背包，求背包可以装下的最大价值
 */
public class Q零一背包更新版 {
    public static int[] values = new int[]{15, 20, 30};
    public static int[] weights = new int[]{1, 1, 4};
    public static int capaicity = 4;
    public static int item = 3;

    // -----------------------------------------------------------------------递归 dfs解法-------------------------------------------------------------------------

    // 解法1 【dfs回溯】 最简单无脑的解法  没有采用备忘录优化
    // 对于每个物品都有取或者不取两种做法。只要当前背包的容量塞得下当前物品 那就放进去 然后更新ans
    public static int ans = Integer.MIN_VALUE;

    public static int maxValueBackTrack(int capacity) {
        dfs(capacity, 0, 0);
        return ans;

    }

    private static void dfs(int capacity, int start, int sum) {
        if (start == item) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = start; i < item; i++) {
            if (capacity >= weights[i]) {
                dfs(capacity - weights[i], i + 1, sum + values[i]);
            }
        }
    }

    /**
     * 解法2 自上而下 带备忘录的递归
     * maxValueRecursion(int item, int capacity)的返回值定义为： 从item个物品中任意拿，当前背包的容量为capacity 时的最优解（最大价值）
     * 对于每一件物品都有拿或不拿
     * 1、若不拿
     * fun(item - 1, capacity)
     * 2、如果capacity >= weight[i]时，可以拿
     * max(fun(item - 1, capacity), fun(item - 1, capacity - weights[i - 1]) + values[i - 1])
     * <p>
     * https://www.cnblogs.com/mfrank/p/10533701.html
     */
    public static Integer[][] cache = new Integer[item + 1][capaicity + 1];

    public static int maxValueRecursion(int item, int capacity) {
        if (cache[item][capacity] != null)
            return cache[item][capacity];
        int result = 0;
        if (item == 0 || capacity == 0) {
            result = 0;
        } else {
            // 放 / 不放 当前物品中的较大者
            // 1、不放当前物品的价值
            if (capacity < weights[item - 1]) {
                result = maxValueRecursion(item - 1, capacity);
            }
            // 2、放当前物品的价值
            else {
                result = Math.max(maxValueRecursion(item - 1, capacity),                                           // 不放
                        maxValueRecursion(item - 1, capacity - weights[item - 1]) + values[item - 1]);     // 放入
            }
        }
        cache[item][capacity] = result;
        return result;
    }


    // -----------------------------------------------------------------------迭代 动态规划解法------------------------------------------------------------------------

    /**
     * 【动态规划】
     * 常规动态规划解法
     * dp[i][j]的定义为 从0-i个物品中拿 放入到重量为j的背包中 的最大价值
     * 转移方程为  dp[i][j] = MAX(dp[i - 1][j], values[i] + dp[i - 1][j - weight[i]])
     * 拿第i件物品   dp[i][j] = values[i] + dp[i-1][j - weights[i]]  即 第i件物品的价值 + 前i-1件物品放入容量为j-wi的背包中的最优价值组合
     * 不拿第i件物品 dp[i][j] = dp[i-1][j]                           即 前i-1件物品放入容量为j的背包中的最优价值组合
     * <p>
     * https://www.jianshu.com/p/a66d5ce49df5
     * https://mp.weixin.qq.com/s/FwIiPPmR18_AJO5eiidT6w
     * <p>
     * <p>
     * 动态规划问题的两个必要条件：最优化原理(最优策略的子策略一定是最优的) + 无后效性(只与当前状态和决策有关，与之前如何到达当前状态无关)
     * 既然开头已经说了两个验证问题是否可以使用动态规划求解的方法，那么为何不试一试呢？
     * <p>
     * 先来看看最优化原理。同样，我们使用反证法：
     * <p>
     * 1.最优化原理: 假设(x1，x2，…，xn)是01背包问题的最优解，则有(x2，x3，…，xn)是其子问题的最优解，假设(y2，y3，…，yn)是上述问题的子问题最优解，则有(v2y2+v3y3+…+vnyn)+v1x1 > (v2x2+v3x3+…+vnxn)+v1x1。说明(X1，Y2，Y3，…，Yn)才是该01背包问题的最优解，这与最开始的假设(X1，X2，…，Xn)是01背包问题的最优解相矛盾，故01背包问题满足最优性原理。
     * 2.无后效性:   其实比较好理解。对于任意一个阶段，只要背包剩余容量和可选物品是一样的，那么我们能做出的现阶段的最优选择必定是一样的，是不受之前选择了什么物品所影响的。即满足无后效性。
     *
     * @param item
     * @param capacity
     * @return
     */
    public static int maxValueDP(int item, int capacity) {
        // dp[i][j]表示有i个物品，j个存储空间时的最优解
        // i的下标从0开始，j的下标从1开始
        int[][] dp = new int[item][capacity + 1];

        for (int i = 0; i < item; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 为考虑item为空的情况，即少了一行，所以需要对第0行做分类讨论
                if (i == 0) {
                    dp[i][j] = (weights[i] <= j) ? values[i] : 0;
                } else {
                    // 此处不需要进行倒叙遍历，是因为dp[i - 1][j - weights[i]](代表0~i-1个物品放入j-weights[i]的背包 物品i本身已经被排除了)用的是上一行的值，而不是本层的dp[i][j - weights[i]](代表0-i个物品 放入j-weights[i]的背包 可能会存在i被放2次的情况)。因为这里有i j两个维度，所以i 与 i-1是可以区分开的。但是在一维数组里只有j一个维度 只能通过倒叙来保证只放一次
                    // 如果用的是dp[i][j - weights[i]]，当j - weights[i] > 0时，由于i还可以再选 可能会存在着同一个物品被多次放入的情况
                    if (j >= weights[i]) { // 如果可以放下第i件物品时
                        dp[i][j] = Math.max(dp[i - 1][j],               // 不放入当前物品: 不放入当前物品的原背包最优解
                                values[i] + dp[i - 1][j - weights[i]]); // 放入当前商品: 当前物品的价值 + 不放入当前物品的小背包最优解
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[item - 1][capacity];
    }

    // 初始化数组时， 行 列都 + 1， 避免了分类讨论。
    // item = 0时，即不放任何物品，dp[i][j] 为0； capacity = 0时，即容量为0，dp[i][j]为0
    // https://mp.weixin.qq.com/s/FwIiPPmR18_AJO5eiidT6w
    // dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
    public static int maxValueDP2(int item, int capacity) {
        int[][] dp = new int[item + 1][capacity + 1]; // dp[i][j]表示有i个物品，j个容量时的最大价值

        // i的下标从1开始  因为有i - 1的存在
        for (int i = 1; i <= item; i++) {
            // j的下标从0开始  dp[i][0] = 0 即容量为0的背包只能放入价值为0的东西
            for (int j = 0; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[item][capacity];
    }

    /**
     * 解法5 滚动数组动态规划 节省空间  需要倒序遍历 这种方式保证转移来的是 dp[i−1][] 中的元素值。
     * 本质上还是二维数组的动态规划
     * dp[j]             代表二维数组中的dp[i-1][j]
     * dp[j - weights[i]]代表二维数组中的dp[i-1][j-weights[i]]
     *
     * @param capacity
     * @return
     */
    public static int maxValueDp滚动数组(int capacity) {
        int[] dp = new int[capacity + 1];
//        // 对第一行进行初始化
//        for (int j = 0; j <= capacity; j++) {
//            if (j >= weights[0])
//                dp[j] = values[0];
//        }

        // 包含了i = 0的初始化情况
        // 逆序遍历时 不对第一行进行初始化也可以  因为dp[j - weights[i]] 总是在 dp[j]的左边 总是为0
        for (int i = 0; i < item; i++) {
            for (int j = capacity; j >= weights[i]; j--) {
                // 需要进行倒叙遍历
                //      在访问dp[j]时 当前物品i是可以使用的
                //      但对于dp[j]之前的背包(dp[j - weight[i]]) 由于是逆序遍历所以还没有被当前物品i更新到 其结果是不包含当前物品i的，可以保证物品i只被放入了一次 即 0 1背包而不是重复背包
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        System.out.println(maxValueDp滚动数组(4));
    }


}
