class Solution:
    # 时间复杂度太高
    def kthLargestValue(self, matrix: list[list[int]], k: int) -> int:
        def xor(x, y):
            return x ^ y

        row = len(matrix)
        col = len(matrix[0])
        newMatrix = [[-1 for j in range(col)] for i in range(row)]
        for a in range(row):
            for b in range(col):
                lst = []
                if (a - 1 >= 0 and b - 1 >= 0):
                    newMatrix[a][b] = newMatrix[a - 1][b - 1] ^ newMatrix[a][b - 1] ^ newMatrix[a - 1][b] ^ matrix[a][b]
                else:
                    for i in range(0, a + 1):
                        for j in range(0, b + 1):
                            lst.append(matrix[i][j])
                    if len(lst) == 1:
                        temp = lst[0]
                    else:
                        temp = reduce(xor, lst)
                    newMatrix[a][b] = temp

        lstMatrix = [newMatrix[i][j] for i in range(row) for j in range(col)]
        lstMatrix.sort()
        return lstMatrix[-k]




