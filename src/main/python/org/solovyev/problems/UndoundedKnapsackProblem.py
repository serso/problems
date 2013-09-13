import unittest


def findMaxForWeight(values, weights, m, weight):
    result = 0
    for (v, w) in zip(values, weights):
            if weight - w >= 0:
                result = max(result, m[weight - w] + v)
    return result


def findMaxValue(values, weights, weight):
    m = [-1 for x in range(0, weight + 1)]

    m[0] = 0

    for w in range(1, weight + 1):
        m[w] = max(m[w - 1], findMaxForWeight(values, weights, m, w))

    return m[weight]


class UnboundedKnapsackProblemTests(unittest.TestCase):
    def test(self):
        self.assertEqual(4, findMaxValue([1, 3, 5], [1, 2, 4], 3))
        self.assertEqual(6, findMaxValue([1, 3, 5], [1, 2, 4], 4))
        self.assertEqual(7, findMaxValue([1, 3, 7], [1, 2, 4], 4))
        self.assertEqual(7, findMaxValue([11, 3, 7], [25, 2, 4], 4))
        self.assertEqual(7, findMaxValue([1, 3, 7], [1, 25, 4], 4))


def main():
    unittest.main()


if __name__ == '__main__':
    main()