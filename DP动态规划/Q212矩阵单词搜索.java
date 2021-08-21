/*
212. 单词搜索 II
给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。



示例 1：


输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
输出：["eat","oath"]
示例 2：


输入：board = [["a","b"],["c","d"]], words = ["abcb"]
输出：[]


提示：

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] 是一个小写英文字母
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] 由小写英文字母组成
words 中的所有字符串互不相同
 */
package DP动态规划;
import java.util.*;

public class Q212矩阵单词搜索 {
    boolean[][] isMarked;
    List<String> ans;
    int[] x = new int[]{0, 1, 0, -1};
    int[] y = new int[]{1, 0, -1, 0};

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return new LinkedList<String>();
        int row = board.length;
        int col = board[0].length;
        isMarked = new boolean[row][col];
        Set<String> ans = new HashSet<>();

        for(int i = 0; i < row; i ++){
            for(int j = 0; j < col; j ++){
                for(String word : words){
                    if(search(board, word, i, j))
                        ans.add(word);
                }
            }
        }
        return new LinkedList<>(ans);
    }

    public boolean search(char[][] board, String word, int i, int j){

        if (board[i][j] != word.charAt(0))
            return false;
        if (word.length() == 1 && word.charAt(0) == board[i][j])
            return true;

        isMarked[i][j] = true;
        for(int k = 0; k <= 3; k ++){
            int newX = i + x[k];
            int newY = j + y[k];
            if(newX >= 0 && newX <= board.length - 1 && newY >= 0 && newY <= board[0].length - 1
                    && board[newX][newY] == word.charAt(1) && ! isMarked[newX][newY]){
                if (search(board, word.substring(1), newX, newY)){
                    isMarked[i][j] = false;
                    return true;
                }
            }
        }
        isMarked[i][j] = false;
        return false;
    }
    public static void main(String[] args){
        Q212矩阵单词搜索 q = new Q212矩阵单词搜索();
        char[][] board = new char[][]{{'a', 'a', 'c'},{'d','e','f'},{'g','h','c'}};
        String[] words = new String[] {"aaa"};
        List<String> r = q.findWords(board, words);
        System.out.println(r);
    }
}
