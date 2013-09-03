import unittest


# http://habrahabr.ru/post/191498/


def countPaths(m, n):
    board = [[-1 for x in range(n)] for x in range(m)]
    board[m - 1][n - 1] = 1

    return count(board, 0, 0, m, n)


def count(board, i, j, m, n):
    if i < 0 or j < 0 or i >= m or j >= n:
        return 0

    if board[i][j] != -1:
        return board[i][j]

    result = count(board, i + 2, j - 1, m, n)
    result += count(board, i + 2, j + 1, m, n)
    result += count(board, i - 1, j + 2, m, n)
    result += count(board, i + 1, j + 2, m, n)

    board[i][j] = result

    return result


class CountPathsTests(unittest.TestCase):
    def test3x2(self):
        self.assertEqual(1, countPaths(3, 2))

    def test2x3(self):
        self.assertEqual(1, countPaths(2, 3))

    def test3x3(self):
        self.assertEqual(0, countPaths(3, 3))

    def test4x4(self):
        self.assertEqual(2, countPaths(4, 4))

    def test5x5(self):
        self.assertEqual(4, countPaths(5, 5))

    def test2x1000(self):
        self.assertEqual(0, countPaths(2, 1000))

    def test2x1001(self):
        self.assertEqual(0, countPaths(2, 1001))

    def test2x1002(self):
        self.assertEqual(0, countPaths(2, 1002))

    def test2x1003(self):
        self.assertEqual(1, countPaths(2, 1003))


def main():
    unittest.main()


if __name__ == '__main__':
    main()