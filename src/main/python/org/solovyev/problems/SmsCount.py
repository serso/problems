# http://habrahabr.ru/post/191498/

k = int(input("Enter k: \n"))

m = [[0 for x in range(k)] for x in range(k)]

def getCount(m, i, j):
    if i == -1 and j == -1:
        return 1

    if i < 0 or j < 0:
        return 0

    return m[i][j]

# i - length of the string
# j - number of presses
for i in range(0, k):
    for j in range(0, k):
        oneSymbolCount = getCount(m, i - 1, j - 1)
        twoSymbolsCount = getCount(m, i - 1, j - 2)
        threeSymbolsCount = getCount(m, i - 1, j - 3)
        fourSymbolsCount = getCount(m, i - 1, j - 4)
        m[i][j] = (oneSymbolCount + twoSymbolsCount + threeSymbolsCount) * 8 + fourSymbolsCount * 2

print("Total: " + repr(sum(m[i][j] for i in range(0, k) for j in range(0, k))))