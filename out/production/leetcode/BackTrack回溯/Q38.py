"""
剑指 Offer 38. 字符串的排列
输入一个字符串，打印出该字符串中字符的所有排列。
你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
示例:

输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]

限制：

1 <= s 的长度 <= 8
"""


class Solution:
    def permutation(self, s: str) -> [str]:
        ans = []
        path = ""
        isSearch = [False for x in s]
        def dfs(s, isSearch, path, k, n):
            if (k == n):
                ans.append(path)
                return

            for i in range(len(s)):
                if not isSearch[i]:
                    path += s[i]
                    isSearch[i] = True
                    dfs(s, isSearch, path, k + 1, n)
                    path = path[:-1] # 指向了常量池中的那个"ab",也就是上一层调用栈所保存的path
                    isSearch[i] = False


        dfs(s, isSearch, path, 0, len(s))
        return ans

s = Solution()
l = s.permutation("abc")
print(l)
                
