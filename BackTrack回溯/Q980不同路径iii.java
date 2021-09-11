package BackTrack回溯;

public class Q980不同路径iii {
    public int ans = 0;
    public int[] X = new int[] {-1, 0, 1, 0};
    public int[] Y = new int[] {0, 1, 0, -1};
    public int row;
    public int col;
    public int uniquePathsIII(int[][] grid) {
        this.row = grid.length;
        this.col = grid[0].length;
        int remain = 0;
        int srcI = 0;
        int srcJ = 0;
        int targetI = 0;
        int targetJ = 0;
        for (int i = 0; i < row; i ++){
            for (int j = 0; j < col; j ++){
                if (grid[i][j] == 0)
                    remain ++;
                if (grid[i][j] == 1){
                    srcI = i;
                    srcJ = j;
                }
                if (grid[i][j] == 2){
                    targetI = i;
                    targetJ = j;
                }
            }
        }
        dfs(remain, grid, srcI, srcJ, targetI, targetJ);
        return ans;
    }

    public void dfsright(int remain, int[][] grid, int i, int j, int targetI, int targetJ){
        if (i == targetI && j== targetJ && remain ==  -1){
            ans += 1;
            return;
        }

        // 上下左右 四邻域
        for (int k = 0; k <= 3; k ++){
            int newI = i + Y[k];
            int newJ = j + X[k];
            if (newI >= 0 && newI < row && newJ >= 0 && newJ < col && (
                    grid[newI][newJ] == 0 || grid[newI][newJ] == 2)){
                grid[newI][newJ] = 3; // 3表示正在访问
                dfs(remain - 1, grid, newI, newJ, targetI, targetJ);
                grid[newI][newJ] = 0;
            }
        }
    }

    public void dfs(int remain, int[][] grid, int i, int j, int targetI, int targetJ){
        if (remain == -1 && i == targetI && j == targetJ){
            ans ++;
            return;
        }
        for (int k = 0; k <= 3; k ++){
            int newI = i + X[k];
            int newJ = j + Y[k];
            if (newI >= 0 && newI <= grid.length - 1 && newJ >= 0 && newJ <= grid[0].length - 1 && (grid[newI][newJ] == 0 || grid[newI][newJ] == 2)){
                grid[newI][newJ] = 1;
                dfs(remain - 1, grid, newI, newJ, targetI, targetJ);
                grid[newI][newJ] = 0;
            }
        }
    }

    public static void main(String[] args){
        Q980不同路径iii q = new Q980不同路径iii();
        int[][] gird = new int[][] {{1, 0 ,0 ,0}, {0,0,0,0}, {0,0,0,2}};
        int[][] gird2 = new int[][] {{1, 0 }, {2,0}};

        System.out.println(q.uniquePathsIII(gird));
    }
}
