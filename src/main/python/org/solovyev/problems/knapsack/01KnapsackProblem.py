import unittest


def findMaxValue(values, weights, weight):
    m = [[0 for x in range(0, weight + 1)] for x in range(-1, len(values))]

    for i in range(1, len(values) + 1):
        for w in range(1, weight + 1):
            if w - weights[i - 1] >= 0:
                m[i][w] = max(m[i - 1][w], m[i - 1][w - weights[i - 1]] + values[i - 1])
            else:
                m[i][w] = m[i - 1][w]

    return m[len(values)][weight]


class UnboundedKnapsackProblemTests(unittest.TestCase):
    def test(self):
        self.assertEqual(4, findMaxValue([1, 3, 5], [1, 2, 4], 3))
        self.assertEqual(5, findMaxValue([1, 3, 5], [1, 2, 4], 4))
        self.assertEqual(7, findMaxValue([1, 3, 7], [1, 2, 4], 4))
        self.assertEqual(7, findMaxValue([11, 3, 7], [25, 2, 4], 4))
        self.assertEqual(7, findMaxValue([1, 3, 7], [1, 25, 4], 4))
        self.assertEqual(5, findMaxValue([1, 5], [3, 3], 4))
        self.assertEqual(5, findMaxValue([5, 1], [3, 3], 4))


def main():
    unittest.main()


if __name__ == '__main__':
    main()