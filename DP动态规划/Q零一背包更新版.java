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


    // 解法1 【dfs回溯】 最简单无脑的解法  没有采用备忘录优化
    // 对于每个物品都有取或者不取两种做法
    public static int ans = Integer.MIN_VALUE;

    public static int maxValueBackTrack(int capacity) {
        dfs(capacity, 0, 0);
        return ans;

    }

    private static void dfs(int capacity, int item, int sum) {
        if (item == Q零一背包更新版.item) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = item; i < Q零一背包更新版.item; i++) {
            if (capacity >= weights[i]) {
                dfs(capacity - weights[i], i + 1, sum + values[i]);
            }
        }
    }

    /**
     * 解法2 【dfs递归】
     * maxValueRecursion(int item, int capacity)的返回值定义为： 从item个物品中任意拿，当前背包的容量为capacity 时的最优解（最大价值）
     * 对于每一件物品都有拿或不拿
     * 1、若不拿
     * fun(item - 1, capacity)
     * 2、如果capacity >= weight[i]时，可以拿
     * max(fun(item - 1, capacity), fun(item - 1, capacity - weights[i]) + values[i])
     * <p>
     * 备注：item = 3，代表可以放1 2 3三件物品，但对应的index却是[0] [1] [2]
     */
    public static int[][] cache = new int[item + 1][capaicity + 1];

    public static int maxValueRecursion(int item, int capacity) {
        if (cache[item][capacity] != 0)
            return cache[item][capacity];
        if (item == 0 || capacity == 0)
            return 0;

        // 放 / 不放 当前物品中的较大者

        // 1、不放当前物品的价值
        int value1 = maxValueRecursion(item - 1, capacity);

        // 2、放当前物品的价值
        if (capacity >= weights[item - 1]) {
            int value2 = maxValueRecursion(item - 1, capacity - weights[item - 1]) + values[item - 1];
            value1 = Math.max(value1, value2);
        }
        cache[item][capacity] = value1;
        return value1;
    }

    /**
     * 解法3 【动态规划】
     * dp[i][j]的状态定义为：从i个物品中任意拿，当前背包的容量为j 时的最优解（最大价值）
     * dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + values[i])
     *
     * @param capacity
     * @return
     */
    public static int maxValueDp(int capacity) {
        // 留出j == 0的一列是考虑到，后面存在j == 0的边界情况。如果在初始化时不初始j == 0的情况，后面就要分类讨论j > 0、 j == 0
        int[][] dp = new int[item][capacity + 1];

        // 对第一行初始化
        for (int j = 0; j <= capacity; j++) {
            if (j >= weights[0])
                dp[0][j] = values[0];
        }

        // 先遍历物品 再遍历背包
        for (int i = 1; i < item; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 先把上一行的元素抄下来
                dp[i][j] = dp[i - 1][j];
                if (j >= weights[i])
                    // 此处不需要进行倒叙遍历，是因为dp[i - 1][j - weights[i]]用的是上一行的值，而不是本层的dp[i][j - weights[i]]
                    // 如果用的是dp[i][j - weights[i]]，当j - weights[i] != 0时，会存在着同一个物品被多次放入的情况
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
            }
        }
        return dp[item - 1][capacity];
    }

    /**
     * 解法4 滚动数组动态规划 节省空间  需要倒序遍历
     * 本质上还是二维数组的动态规划  dp[j - 1]代表二维数组中的dp[i][j - 2]
     * dp[j]代表二维数组中的dp[i - 1][j]
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
                // 需要进行倒叙遍历 否则在访问dp[j]时， dp[j - weight[i]]不为0，很有可能把一个物品放入多次
                // 为了保证在访问dp[j]时， dp[j - weight[i]]为0，需要对数组进行逆序遍历
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        System.out.println(maxValueDp(4));
        System.out.println(maxValueDp滚动数组(4));
    }


}
