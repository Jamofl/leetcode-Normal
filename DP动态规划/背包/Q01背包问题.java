package DP动态规划.背包;

/*
https://mp.weixin.qq.com/s/FwIiPPmR18_AJO5eiidT6w
现在有三件商品，价值分别为 15 30 20，重量分别为1 4 3；
有一个大小为4的背包，求背包可以装下的最大价值
 */
public class Q01背包问题 {

    public static int[] values = new int[]{15, 30, 20};
    public static int[] weights = new int[]{1, 4, 3};
    public static int[][] cache;
    public static int item = values.length;
    public static int capacity = 4;

    public static void main(String[] args) {
        cache = new int[item + 1][capacity + 1]; // cache[i][j]表示有i个物品 j个容量时的最大价值解
        int r = maxValueRecursion(item, capacity);
        int r2 = maxValueDP2(item, capacity);
        System.out.println(r);
        System.out.println(r2);
        //System.out.println(ans);

    }

    // -----------------------------------------------------------------------递归 dfs解法-------------------------------------------------------------------------

    /**
     * 递归法回溯  本质还是用备忘录的迭代 转移方程的本质没变
     *
     * @param item
     * @param capacity
     * @return
     */
    // https://www.cnblogs.com/mfrank/p/10533701.html
    // 递归解法 将问题拆分为子问题 需要用备忘录来优化时间 备忘录的本质就是动态规划的dp[i][j]
    // 可以拆分为以下两个子问题来求解:
    // 1.背包剩余容量不足以容纳该物品，此时背包的价值与前i - 1个物品的价值是一样的，KS(i,j) = KS(i-1,j)
    // 2.背包剩余容量可以装下该商品，此时需要进行判断，因为装了该商品不一定能使最终组合达到最大价值，如果不装该商品，
    //  则价值为：KS(i-1,j)，如果装了该商品，则价值为KS(i-1,j-wi) + vi，从两者中选择较大的那个
    public static int maxValueRecursion(int item, int capacity) {
        if (cache[item][capacity] != 0)
            return cache[item][capacity];

        int result = 0;
        // base case
        if (item == 0 || capacity == 0) {
            result = 0;
        } else {
            // base case
            // 如果当前物品的重量大于背包容量，则不可以放进去
            if (weights[item - 1] > capacity) {
                result = maxValueRecursion(item - 1, capacity);
            }
            // 不放入当前物品的价值 或 放入当前物品的价值， 取其中较大的一个
            else {
                result = Math.max(values[item - 1] + maxValueRecursion(item - 1, capacity - weights[item - 1]),  // 放入当前物品
                        maxValueRecursion(item - 1, capacity)); // 不放当前物品
            }
        }
        cache[item][capacity] = result;
        return result;
    }


    /**
     * 方法2 迭代法回溯 本质上就是枚举所有情况 当然可以解决所有问题
     * 注：
     *  方法1和方法2的本质都是回溯，但是解法上是有区别的
     *  方法1：dfs递归 + 备忘录，每一次递归都会讨论针对当前物品时放or不放，每次就只有当前物品这一个选择，是通过dfs(index + 1)完成对物品的遍历，最终得到结果
     *    写法上需要注意，由于没有写for循环，所以需要通过分类讨论列举多次dfs，从而走到不同的分支
     *  方法2：dfs迭代(穷举)，每次无脑穷举从当前index到结尾处的所有物品，看看放进背包里是什么结果，是通过for循环的i++完成对所有物品的遍历
     *    写法上需要注意，for循环本身就是在对所有物品进行扫描了，就是进行了多次dfs，所以不再需要分类讨论放与不放的情况
     *  for循环 和 分类讨论dfs(即出现了多个dfs) 二选一！！！ 不会出现既for循环,又分类讨论写了多个dfs的情况！！！
     */
    public static int ans = Integer.MIN_VALUE;

    public static void dfs(int capacity, int start, int sum) {
        if (capacity <= 0 || start == item) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = start; i < item; i++) {
            if (weights[i] <= capacity) { // 剪枝 只探索那些当前重量可以放进来的分支
                dfs(capacity - weights[i], i + 1, sum + values[i]);
            }
        }
    }


    // -----------------------------------------------------------------------迭代 动态规划解法------------------------------------------------------------------------

    /**
     * 【动态规划】
     * 常规动态规划解法
     * dp[i][j]的定义为 从0-i个物品中拿 放入到重量为j的背包中 的最大价值
     * 转移方程为  dp[i][j] = MAX(dp[i - 1][j], values[i] + dp[i - 1][j - weight[i]])
     * 拿第i件物品   dp[i][j] = values[i] + dp[i-1][j - weights[i]]  即 第i件物品的价值 + 从[0,i-1]个物品里拿放入到j-weights[i]的背包里的最大价值
     * 不拿第i件物品 dp[i][j] = dp[i-1][j]                           即 从[0,i-1]个物品里拿放入到j背包里的最大价值
     * <p>
     * https://www.jianshu.com/p/a66d5ce49df5
     * https://mp.weixin.qq.com/s/FwIiPPmR18_AJO5eiidT6w
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
                    if (j >= weights[i]) { // 如果可以放下第i件物品时
                        dp[i][j] = Math.max(dp[i - 1][j],               // 不放入当前物品
                                values[i] + dp[i - 1][j - weights[i]]); // 放入当前商品
                    } else {               // 如果放不下第i件物品时
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[item - 1][capacity];
    }

    // 初始化数组时， 行 列都 + 1， 避免了分类讨论。
    // item = 0时，即不放任何物品，dp[0][j] 为0； capacity = 0时，即容量为0，dp[i][0]为0
    // https://mp.weixin.qq.com/s/FwIiPPmR18_AJO5eiidT6w
    // dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
    public static int maxValueDP2(int item, int capacity) {
        int[][] dp = new int[item + 1][capacity + 1]; // dp[i][j]表示有i个物品，j个容量时的最大价值

        // i的下标从1开始  因为有i - 1的存在
        for (int i = 1; i <= item; i++) {
            // j的下标从0开始  dp[i][0] = 0 即容量为0的背包只能放入价值为0的东西
            // 20241119更新: 这里感觉j的下标也可以从1开始  dp[i][0] = 0 即容量为0的背包只能放入价值为0的东西 上面做初始化的目的就是为了这里避免分类讨论
            for (int j = 1; j <= capacity; j++) {
                // 如果j-weight[i-1] >= 0 就进行动规 根据动态转移方程从上一次的结果推导本次结果
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
//                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i][j - weights[i - 1]]); // 和01背包的区别就是拿了当前物品后，还可以再拿，所以下标i不用减一
                // 否则就直接取上一行的数据即可 ==> 个人觉得后面的一维数组是从结果反推的为了节省空间的做法 并不是很好理解 因为直接复制上一行 无法对应真实的理解 是为了省空间而省空间
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[item][capacity];
    }

    // https://mp.weixin.qq.com/s/M4uHxNVKRKm5HPjkNZBnFA
    // 滚动数组， 使用一维数组代替二维数组，相当每次对矩阵中的下一行进行填充时，将上一行直接复制过来
    // 需要注意的是，这里要进行逆序遍历，以保证每个物品只被放入背包一次
    // dp[j] = max(dp[j], dp[j - weight[i]])
    public static int maxValueDP3(int item, int capacity) {
        int[] dp = new int[capacity + 1]; // dp[j]表示背包容量为j时的最大价值
        // 第0列被初始化为0

        for (int i = 0; i < item; i++) {
            for (int j = capacity; j >= weights[i]; j--) {
                // j >= weights[i] 保证了 j - weights[i] 这个下标永远 >= 0
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[capacity];
    }
}
