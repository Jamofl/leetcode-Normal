/*
207. 课程表
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。



示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
示例 2：

输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
输出：false
解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。


提示：

1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
prerequisites[i] 中的所有课程对 互不相同
 */
package 数据结构.Graph;
import java.util.*;
public class Q207拓扑排序课程表 {

    // 建立图的数据结构  + DFS遍历
    /*
    private class Node{
        public int val;
        public int indegree;
        public Set<Node> neighbors;
        public Node(int val){
            this.val = val;
            this.neighbors = new HashSet<>();
            this.indegree = 0;
        }

        public void addNeighbor(Node nei){
            this.neighbors.add(nei);
            nei.indegree ++;
        }
    }

    public Map<Integer, Node> map;
    public int numCourses;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0)
            return true;

        this.numCourses = numCourses;
        this.map = new HashMap<Integer, Node>();
        for (int i = 0; i < prerequisites.length; i ++){
            int[] prerequisite = prerequisites[i];
            int courseNum1 = prerequisite[0];
            int courseNum2 = prerequisite[1];
            if (courseNum1 == courseNum2)
                return false;
            Node course1;
            Node course2;
            if (map.containsKey(courseNum1))
                course1 = map.get(courseNum1);
            else{
                course1 = new Node(courseNum1);
                map.put(courseNum1, course1);
            }

            if (map.containsKey(courseNum2))
                course2 = map.get(courseNum2);
            else{
                course2 = new Node(courseNum2);
                map.put(courseNum2, course2);
            }
            course2.addNeighbor(course1);
        }
        return topologicalCheck();
    }

    private boolean topologicalCheck(){
        int[] visit = new int[numCourses];
        Arrays.fill(visit, -1);
        for (Map.Entry<Integer, Node> entry : map.entrySet()){
            Node course = entry.getValue();
            if (! dfs(course, visit))
                return false;
        }
        return true;
    }

    private boolean dfs(Node course, int[] visit){
        if (visit[course.val] == 1)
            return true;
        else if (visit[course.val] == 0)
            return false;
        else{
            visit[course.val] = 0;
            for (Node nei : course.neighbors){
                if (! dfs(nei, visit))
                    return false;
            }
        }
        visit[course.val] = 1;
        return true;
    }
     */


    // 方法2 邻接表 + DFS遍历   O(M + N)
    // 注意定义好visit的状态
    //  -1 未被访问
    //  0 被当前节点开始的dfs访问过了
    //  1 被其他节点开始的dfs访问过了
    List<List<Integer>> adajcentList;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0)
            return true;
        adajcentList = new ArrayList<List<Integer>>(numCourses);
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
            if (!dfs(visit, i))
                return false;
        }
        return true;
    }

    public boolean dfs(int[] visit, int course){
        if (visit[course] == 1) // 被其他节点开始的dfs访问过了 直接返回true
            return true;
        else if (visit[course] == 0) // 被当前节点开始的dfs访问过了，说明存在环形，返回false
            return false;
        else{
            visit[course] = 0; // 置为正在访问
            for (Integer neighbor : adajcentList.get(course)){
                if (!dfs(visit, neighbor))
                    return false;
            }
        }
        visit[course] = 1; // 置为已访问
        return true;
    }



    /*
    // 方法3  邻接表 + BFS遍历
    // 维护一个队列，将入度为0的点加入队列中，每次从队列中移除一个顶点，并将它所有的邻居顶点的入度减一。
    // 若该邻居顶点的入度为0，也加入队列中。当队列为空时，若剩余课程数量不为0，说明仍存在不可达课程
    List<List<Integer>> adajcentList;
    int[] indegrees;
    int numCourses;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0)
            return true;

        this.numCourses = numCourses;
        this.adajcentList = new ArrayList<List<Integer>>(numCourses);
        this.indegrees = new int[numCourses];

        for (int i = 0; i < numCourses; i ++){
            adajcentList.add(new ArrayList<Integer>());
        }
        for (int[] prerequisite : prerequisites){
            adajcentList.get(prerequisite[1]).add(prerequisite[0]);
            indegrees[prerequisite[0]] ++;
        }
        return bfs();
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
//        Q207拓扑排序课程表 q = new Q207拓扑排序课程表();
//        boolean r = q.canFinish(5, new int[][]{{1,4}, {2,4}, {3,1}, {3,2}});
//        System.out.println(r);
        PriorityQueue<Integer> qq = new PriorityQueue();
        System.out.println(qq.poll() == null);

    }
}
