package 数据结构.Graph;

import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class T {

    Map<String, Set<String>> adjacent;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (! wordList.contains(endWord))
            return 0;

        wordList.add(beginWord);
        int n = wordList.size();
        adjacent = new HashMap<>();
        for (int i = 0; i < n; i ++)
            adjacent.put(wordList.get(i), new HashSet<String>());

        for (int i = 0; i < n; i ++){
            for (int j = 0; j < n; j ++){
                if (i == j)
                    continue;
                else{
                    String s = wordList.get(i);
                    String t = wordList.get(j);
                    if (isOneWordVar(s, t)){
                       adjacent.get(s).add(t);
                       adjacent.get(t).add(s);
                    }
                }
            }
        }
        return bfs(beginWord, endWord, new HashSet<>());


    }

    private int bfs(String beginWord, String endWord, Set<String> visit){
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int step = 0;

        while (q.size() != 0){
            int size = q.size();
            step ++;
            while (size > 0){
                String pop = q.poll();
                visit.add(pop);
                if (pop == endWord)
                    return step;
                for (String nei : adjacent.get(pop)){
                    if (! visit.contains(nei)){
                        q.offer(nei);
                    }
                }
            }
        }
        return step;
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

}
