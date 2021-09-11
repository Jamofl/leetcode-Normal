package 数据结构.Graph;
import java.util.*;
public class Q210拓扑排序II {

    // 方法1: dfs
    // 选取任意一个未被访问过的节点进行dfs遍历，将它的后序遍历（即return的顺序）记录下来
    // 重复这一操作直到所有节点都被访问，最后将后序遍历的结果reverse即是拓扑排序
    // 具体实现为：将return的结果记录在stack中，然后依次将stack中的值弹出
    public List<List<Integer>> adajcentList;
    public boolean hasAnswer = true;
    public Stack<Integer> stack;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        this.adajcentList = new ArrayList<List<Integer>>(numCourses);
        this.stack = new Stack<>();
        for (int i = 0; i < numCourses; i ++){
            adajcentList.add(new ArrayList<Integer>());
        }

        for (int[] prerequisite : prerequisites){
            adajcentList.get(prerequisite[1]).add(prerequisite[0]);
        }
        int[] visit = new int[numCourses];
        // -1 未被访问
        //  0 被当前节点开始的dfs访问过了
        //  1 被其他节点开始的dfs访问过了
        Arrays.fill(visit, -1);
        for (int i = 0; i < numCourses; i ++){
            if (!dfs(visit, i)){
                hasAnswer = false;
                break;
            }
        }
        if (!hasAnswer)
            return new int[]{};
        else{
            int index = 0;
            while (stack.size() != 0){
                ans[index] = stack.pop();
                index ++;
            }
            return ans;
        }
    }

    // -1 未被访问
    //  0 被当前节点开始的dfs访问过了
    //  1 被其他节点开始的dfs访问过了
    public boolean dfs(int[] visit, int course){
        if (visit[course] == 1)      // 被其他节点开始的dfs访问过了 直接返回true
            return true;
        else if (visit[course] == 0) // 被当前节点开始的dfs访问过了，说明存在环形，返回false
            return false;
        else{                        // -1 未被访问过
            visit[course] = 0; // 置为正在访问
            for (Integer neighbor : adajcentList.get(course)){
                if (!dfs(visit, neighbor))
                    return false;
            }
        }
        visit[course] = 1; // 置为已访问
        stack.push(course);
        return true;
    }



    /*
    // 方法2  邻接表 + BFS遍历
    // 维护一个队列，将入度为0的点加入队列中，每次从队列中移除一个顶点，并将它所有的邻居顶点的入度减一。
    // 若该邻居顶点的入度为0，也加入队列中。当队列为空时，若剩余课程数量不为0，说明仍存在不可达课程。
    // 每队列有元素移除时，将该元素加入到拓扑排序的结果中。
    List<List<Integer>> adajcentList;
    int[] indegrees;
    数据结构.LinkedList<Integer> lst;
    int numCourses;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        this.numCourses = numCourses;
        this.adajcentList = new ArrayList<List<Integer>>(numCourses);
        this.indegrees = new int[numCourses];
        this.lst = new 数据结构.LinkedList<>();
        int[] ans = new int[numCourses];

        for (int i = 0; i < numCourses; i ++){
            adajcentList.add(new ArrayList<Integer>());
        }
        for (int[] prerequisite : prerequisites){
            adajcentList.get(prerequisite[1]).add(prerequisite[0]);
            indegrees[prerequisite[0]] ++;
        }
        if (bfs() == true){
            int index = 0;
            for (int course : lst){
                ans[index] = course;
                index ++;
            }
            return ans;
        }
        else
            return new int[]{};
    }

    public boolean bfs(){
        Queue<Integer> q = new 数据结构.LinkedList<>();
        for (int i = 0; i < numCourses; i ++){
            if (indegrees[i] == 0)
                q.offer(i);
        }
        int remainingCourse = numCourses;

        while (q.size() != 0){
            int course = q.poll();
            lst.addLast(course);
            remainingCourse --;
            for (int nei : adajcentList.get(course)){
                indegrees[nei] --;
                if (indegrees[nei] == 0)
                    q.offer(nei);
            }
        }
        return remainingCourse == 0;
    }
     */


    public static void main(String[] args){
        Q210拓扑排序II q = new Q210拓扑排序II();
        int[] r = q.findOrder(3, new int[][] {{1,0}, {2,1}});
        System.out.println(r.toString());
    }

}
