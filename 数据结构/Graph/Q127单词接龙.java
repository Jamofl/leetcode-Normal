package 数据结构.Graph;
import java.util.*;
public class Q127单词接龙 {


    // 方法1 邻接表 + bfs
    Map<String, Set<String>> adjMap = new HashMap<>();; // 邻接表
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;

        wordSet.add(beginWord);
        for (String word : wordSet){
            adjMap.put(word, new HashSet<String>());
        }
        // 构建图
        constructGraphPromotion(wordSet);
        // 广度优先寻找最短路径
        return bfs(beginWord, endWord, new HashSet<String>());
    }

    private int bfs(String beginWord, String target, Set<String> visit){
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int path = 0;
        while (queue.size() != 0){
            int m = queue.size();
            path ++;
            while (m > 0){
                String pop = queue.poll();
                visit.add(pop);
                if (pop.equals(target)){ // 这里要用equals 不要用== 判断
                    return path;
                }
                for (String nei : adjMap.get(pop)){
                    if (! visit.contains(nei))
                        queue.add(nei);
                }
                m --;
            }
        }
        return 0;
    }


    /**
     * 内外循环遍历枚举所有组合 判断是否相差一个字符 时间复杂度N * N * wordLength
     * @param wordList
     */
    private void constructGraph(List<String> wordList){
        for (int i = 0; i < wordList.size(); i ++){
            for (int j = i + 1; j < wordList.size(); j ++){
                String s = wordList.get(i);
                String t = wordList.get(j);
                if (isOneWordVar(s, t)){
                    adjMap.get(s).add(t);
                    adjMap.get(t).add(s);
                }
            }
        }
    }

    private void constructGraphPromotion(Set<String> wordSet){
        for (String curWord : wordSet){
            for (char k  = 'a'; k < 'z' ; k ++){
                for (int i = 0; i < curWord.length(); i ++){
                    if (curWord.charAt(i) == k){
                        continue;
                    }
                    char[] chars = curWord.toCharArray();
                    chars[i] = k;
                    String tempWord = String.valueOf(chars);
                    if (wordSet.contains(tempWord)){
                        adjMap.get(curWord).add(tempWord);
                        adjMap.get(tempWord).add(curWord);
                    }
                }
            }
        }
    }

    private boolean isOneWordVar(String s, String t){
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i ++){
            if (s.charAt(i) != t.charAt(i))
                count ++;
        }
        return count == 1;
    }




    List<String> wordList;
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (! wordList.contains(endWord))
            return 0;

        int n = wordList.size();
        this.wordList = wordList;
        wordList.add(beginWord);
        return bfs2(beginWord, endWord, new HashSet<String>());
    }

    private int bfs2(String beginWord, String target, Set<String> visit){
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        visit.add(beginWord);
        int path = 0;

        while (q.size() != 0){
            int m = q.size();
            path ++;
            while (m > 0){
                String pop = q.poll();
                if (oneWordVar(pop, target, q, visit))
                    return path + 1;
                m --;
            }
        }
        return 0;
    }

    private boolean oneWordVar(String curWord, String target, Queue<String> q, Set<String> visit){
        char[] charArr = curWord.toCharArray();
        for (int i = 0; i < charArr.length; i ++){
            char c = charArr[i];
            for (char j = 'a'; j <= 'z'; j ++){
                if (j == c)
                    continue;
                charArr[i] = j;
                String nextWord = String.valueOf(charArr);
                if (wordList.contains(nextWord)){
                    if (target.equals(nextWord))
                        return true;
                    if (! visit.contains(nextWord)){
                        q.offer(nextWord);
                        visit.add(nextWord);
                    }
                }
            }
            charArr[i] = c;
        }
        return false;
    }

    public static void main(String[] args){
        List<String> wordList = new LinkedList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        Q127单词接龙 q = new Q127单词接龙();
        int r = q.ladderLength("hit", "cog", wordList);
        System.out.println("ans : " + r);
    }
}
