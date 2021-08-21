package BackTrack回溯;
/*
79. 单词搜索
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Q79单词搜索 {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visit = new boolean[row][col];

        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[0].length; j ++){
                if (board[i][j] == word.charAt(0))
                    if (dfs(board, visit, i, j, word.toCharArray(), 1))
                        return true;
            }
        }
        return false;
    }


    int[] Is = new int[] {-1,0,1,0};
    int[] Js = new int[] {0,1,0,-1};
    private boolean dfs(char[][] board, boolean[][] visit, int i, int j, char[] word, int start){
        if (start == word.length){
            return true;
        }

        visit[i][j] = true;
        for (int k = 0; k <= 3; k ++){
            int newI = i + Is[k];
            int newJ = j + Js[k];
            if (newI >= 0 && newI <= board.length - 1 && newJ >= 0 && newJ <= board[0].length - 1
                    && (!visit[newI][newJ]) && board[newI][newJ] == word[start]){
                if (dfs(board, visit, newI, newJ, word, start + 1))
                    return true;
            }
        }
        visit[i][j] = false;
        return false;
    }
}
