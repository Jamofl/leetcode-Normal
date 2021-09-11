/*
现在有三件商品，价值分别为 15 30 20，重量分别为1 4 3；
有一个大小为4的背包，求背包可以装下的最大价值
 */
public class Q01背包问题 {

    public static int[] values = new int[] {1,2,1};
    public static int[] weights = new int[] {2,1,1};
    public static int[][] cache;
    public static int item = values.length;
    public static int capacity = 2;
    public static void main(String[] args){
        cache = new int[item + 1][capacity + 1]; // cache[i][j]表示有i个物品 j个容量时的最大价值解
        int r = maxValueRecursion(item, capacity);
        int r2 = maxValueDP2(item, capacity);
        dfs(capacity,0,0);
        System.out.println(r);
        System.out.println(r2);
        //System.out.println(ans);

    }

    // https://www.cnblogs.com/mfrank/p/10533701.html
    // 递归解法 将问题拆分为子问题 需要用备忘录来优化时间 备忘录的本质就是动态规划的dp[i][j]
    // 可以拆分为以下两个子问题来求解:
    // 1.背包剩余容量不足以容纳该物品，此时背包的价值与前i - 1个物品的价值是一样的，KS(i,j) = KS(i-1,j)
    // 2.背包剩余容量可以装下该商品，此时需要进行判断，因为装了该商品不一定能使最终组合达到最大价值，如果不装该商品，
    //  则价值为：KS(i-1,j)，如果装了该商品，则价值为KS(i-1,j-wi) + vi，从两者中选择较大的那个
    public static int maxValueRecursion(int item, int capacity){
        if (cache[item][capacity] != 0)
            return cache[item - 1][capacity - 1];

        int result = 0;

        // base case
        if (item == 0 || capacity == 0)
            result =  0;

        // base case
        // 如果当前物品的重量大于背包容量，则不可以放进去
        else if (weights[item - 1] > capacity)
            result = maxValueRecursion(item - 1, capacity);

        // 不放入当前物品的价值 或 放入当前物品的价值， 取其中较大的一个
        else{
            result = Math.max(values[item - 1] + maxValueRecursion(item - 1, capacity - weights[item - 1]),  // 放入当前物品
                    maxValueRecursion(item - 1, capacity)); // 不放当前物品
        }
        cache[item][capacity] = result;
        return result;
    }


    // 方法2 dfs回溯 本质上就是枚举所有情况 当然可以解决所有问题
    public static int ans = Integer.MIN_VALUE;
    public static void dfs(int capacity, int start, int sum){
        if (capacity <= 0 || start == item){
            ans = Math.max(ans, sum);
            return ;
        }

        for (int i = start; i < item; i ++){
            if (weights[i] <= capacity){
                dfs( capacity - weights[i], i + 1, sum + values[i]);
            }
        }
    }


    // https://www.jianshu.com/p/a66d5ce49df5
    // 动态规划解法
    // 转移方程为  dp[i][j] = MAX(dp[i - 1][j], values[i] + dp[i - 1][j - weight[i]])
    // 需要分类讨论的方法
    public static int maxValueDP(int item, int capacity){
        // dp[i][j]表示有i个物品，j个存储空间时的最优解
        // i的下标从0开始，j的下标从1开始
        int[][] dp = new int[item][capacity + 1];


        for (int i = 0; i < item; i ++){
            for (int j = 1; j <= capacity; j ++){
                // 为考虑item为空的情况，即少了一行，所以需要对第0行做分类讨论
                if (i == 0){
                    dp[i][j] = (weights[i] <= j) ? values[i] : 0;
                }
                else {
                    if (weights[i] > j)
                        dp[i][j] = dp[i - 1][j];
                    else if (weights[i] == j)
                        dp[i][j] = Math.max(dp[i - 1][j], values[i]);
                    else // weigths[i] < j
                        dp[i][j] = Math.max(dp[i - 1][j], // 不放入当前物品
                                values[i] + dp[i - 1][j - weights[i]]); // 放入当前商品
                }
            }
        }
        return dp[item - 1][capacity];
    }

    // 初始化数组时， 行 列都 + 1， 避免了分类讨论。
    // item = 0时，即不放任何物品，dp[i][j] 为0； capacity = 0时，即容量为0，dp[i][j]为0
    // https://mp.weixin.qq.com/s/FwIiPPmR18_AJO5eiidT6w
    // dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
    public static int maxValueDP2(int item, int capacity){
        int[][] dp = new int [item + 1][capacity + 1]; // dp[i][j]表示有i个物品，j个容量时的最大价值

        // i的下标从1开始！！！  因为有i - 1的存在
        for (int i = 1; i <= item; i ++){
            // j的下标从0开始
            for (int j = 0; j <= capacity; j ++){
                dp[i][j] = dp[i - 1][j]; // 先把上一行的元素抄下来
                if (weights[i - 1] <= j)
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
            }
        }
        return dp[item][capacity];
    }

    // https://mp.weixin.qq.com/s/M4uHxNVKRKm5HPjkNZBnFA
    // 滚动数组， 使用一维数组代替二维数组，相当每次对矩阵中的下一行进行填充时，将上一行直接复制过来
    // 需要注意的是，这里要进行逆序遍历，以保证每个物品只被放入背包一次
    // dp[j] = max(dp[j], dp[j - weight[i]])
    public static int maxValueDP3(int item, int capacity){
        int[] dp = new int[capacity + 1]; // dp[j]表示背包容量为j时的最大价值
        // 第0列被初始化为0

        for (int i = 0; i < item; i ++){
            for (int j = capacity; j >= weights[i]; j --){
                // j >= weights[i] 保证了 j - weights[i] 这个下标永远 >= 0
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[capacity];

    }
}
