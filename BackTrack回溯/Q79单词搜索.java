package BackTrack回溯;
/*
79. 单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Q79单词搜索 {
    /**
     * 对于矩阵中的每一个字符 循环遍历该字符上下左右的元素 校验和当前word的首位字符是否匹配
     * 结束条件: word长度校验完毕 返回成功 其余均返回失败
     */


    // 初始化一个数组用于遍历
    public int[][] arounds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 避免了初始化的情况 直接进入dfs方法中判断 (首次访问 边界条件和是否访问直接满足 无需校验)
                if (dfs(board, word, visit, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 整体逻辑:
     *      对本次dfs 直接判断字符是否匹配
     *      对下次dfs 提现判断数组边界和是否访问
     *
     * 整理一个本次dfs和下次dfs流程化的模板 然后把初始化的情况套进来 不要特判初始化的情况
     *      兼容i == 0 j == 0的初始化讨论 让第一次也可以套用dfs循环的模板
     *      对于第一次，相当于直接满足边界和是否访问的校验
     *
     * ==>
     * @param board
     * @param word
     * @param visit
     * @param index
     * @param curI
     * @param curJ
     * @return
     */
    public boolean dfs(char[][] board, String word, boolean[][] visit, int index, int curI, int curJ) {
        if (board[curI][curJ] != word.charAt(index)){
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }

        boolean re = false;
        visit[curI][curJ] = true;
        for (int[] around : arounds) {
            int newI = curI + around[0];
            int newJ = curJ + around[1];
            // 校验边界 校验访问
            if (newI >= 0 && newI <= board.length - 1 && newJ >= 0 && newJ <= board[0].length - 1
                    && !visit[newI][newJ]) {
                if (dfs(board, word, visit, index + 1, newI, newJ)) {
                    re = true; // 实际上 这一次访问完毕后也是需要还原visit的 不过由于命中true后会直接返回结果 不会再有后续的dfs了 所以这里不还原现场也是可以的
                    break;
                }
            }
        }
        visit[curI][curJ] = false;
        return re;
    }
}
