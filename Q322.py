class Solution:
    def coinChange(self, coins, amount: int) -> int:
        lst = []
        ans = []
        if len(coins) == 0:
            return -1
        if amount == 0:
            return 0

        def helper(self, coins, amount):
            if amount < 0:
                return -1
            if amount == 0:
                return 0

            for i in coins:
                ans.append(i)
                coin = helper(self, coins, amount - i)

                if coin == 0:
                    lst.append(ans[:])
                ans.remove(i)

        helper(self, coins, amount)

        if len(lst) == 0:
            return -1
        length = [len(x) for x in lst]
        return min(length)


s = Solution()
r = s.coinChange([2], 3)
print(r)