"""
46. 全排列
给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

"""

class Solution(object):
    def permute(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        ans = []
        isSearched = [False for _ in range(len(nums))]

        def helper(nums, isSearched, path, k, n):
            if k == n:
                ans.append(path[:])
                return

            for i in range(0, len(nums)):
                if not isSearched[i]:
                    path.append(nums[i])
                    isSearched[i] = True
                    helper(nums, isSearched, path, k + 1, n)
                    path.pop()
                    # path = path[:-1]
                    isSearched[i] = False

        helper(nums, isSearched, [], 0, len(nums))
        return ans

s = Solution()
l = s.permute([1,2,3])
print(l)

