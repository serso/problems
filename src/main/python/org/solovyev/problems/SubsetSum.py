import unittest


def countSums(integers):
    negativeSum = 0
    positiveSum = 0
    for i in integers:
        if i > 0:
            positiveSum += i
        else:
            negativeSum += i

    return positiveSum, negativeSum


def subsetSum(a, sum):
    (positiveSum, negativeSum) = countSums(a)

    if positiveSum < sum or negativeSum > sum:
        return False

    m = [[-1 for x in range(negativeSum, positiveSum+1)] for x in range(len(a))]

    for i in range(0, len(a)):
        m[i][0] = True

    for s in range(negativeSum, positiveSum+1):
        m[0][s] = a[0] == s

    for i in range(1, len(a)):
        for s in range(negativeSum, positiveSum+1):
            m[i][s] = a[i] == s or m[i-1][s] or m[i-1][s - a[i]]


    return m[len(a) - 1][sum]


class SubsetSumTests(unittest.TestCase):
    def test(self):
        self.assertTrue(subsetSum([-3, 1, 2, 3], 3))
        self.assertTrue(subsetSum([-3, 1, 2, 22], 3))
        self.assertFalse(subsetSum([-3, 1, 2, 22], 3222))
        self.assertTrue(subsetSum([-1, 10, -3, 14], 24))
        self.assertTrue(subsetSum([-1, 10, -3, 14], -4))
        self.assertTrue(subsetSum([-4, 10, -3, 14], -4))


def main():
    unittest.main()


if __name__ == '__main__':
    main()