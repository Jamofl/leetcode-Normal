package BackTrack回溯;
import java.util.*;
public class Q51 { // 无法判断对角线元素
    public int n;
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        List<List<Integer>> ans = new LinkedList<>();
        List<List<String>> convertedAns = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();

        dfs(ans, path, 0);
        convertedAns = convertAns(ans);
        return convertedAns;

    }

    private List<List<String>> convertAns(List<List<Integer>> ans){
        List<List<String>> convertedAns = new LinkedList<>();
        for (int i = 0; i < ans.size(); i ++){
            List<Integer> temp = ans.get(i);
            LinkedList<String> toAdd = new LinkedList<>();
            for (int j = 0; j < n; j ++) {
                String convert = getString(temp.get(j));
                toAdd.add(convert);
            }
            convertedAns.add(new LinkedList<>(toAdd));
        }
        return convertedAns;
    }

    private String getString(int index){
        StringBuilder re = new StringBuilder();
        for (int i = 0; i <index; i ++)
            re.append(".");
        re.append("Q");
        for (int i = index + 1; i < n; i ++)
            re.append(".");
        return re.toString();

    }

    private boolean isSameSlash(int a, int b, int c, int d){
        if ((d - b == c - a) || (d - b == a - c))
            return true;
        return false;
    }

    private void dfs(List<List<Integer>> ans, LinkedList<Integer> path, int row){
        if (row == n){
            ans.add(new LinkedList(path));
            return;
        }

        for (int j = 0 ; j < n; j ++){
            if (path.contains(j))
                continue;
            if (path.size() != 0){
                boolean flag = false;
                for (int i = 0; i < path.size(); i ++){
                    if (isSameSlash(i, path.get(i), row, j)){
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    continue;
            }

            path.addLast(j);
            dfs(ans, path, row + 1);
            path.removeLast();
        }
    }

    public static void main(String[] args){
        Q51 q = new Q51();
        List l = q.solveNQueens(4);
        System.out.println(l);
        System.out.println(q.getString(1));
    }
}
