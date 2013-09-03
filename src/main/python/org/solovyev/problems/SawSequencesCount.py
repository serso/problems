import unittest


# http://habrahabr.ru/post/191498/


def countSawSequences(n, digits):
    m = [[[0 for x in range(2)] for x in range(digits)] for x in range(n+1)]
    # m[n][last][less] - number of saw sequences of length n ending at last
    # where 'last' is less than number previous to 'last' if 'less' == 0 and more if 'less' == 1
    for last in range(digits):
        if last == 0:
            m[2][0][1] = 0
        else:
            m[2][last][1] = m[2][last - 1][1] + (digits - last)

        m[2][digits - last - 1][0] = m[2][last][1]

    for length in range(3, n+1):
        for prev in range(digits):
            for last in range(digits):
                if prev > last:
                    m[length][last][0] += m[length - 1][prev][1]
                elif prev < last:
                    m[length][last][1] += m[length - 1][prev][0]

    return sum(m[n][j][less] for less in range(0, 2) for j in range(0, digits))


class CountSawSequencesTests(unittest.TestCase):
    def test(self):
        self.assertEqual(570, countSawSequences(2, 10))
        self.assertEqual(60, countSawSequences(2, 5))
        self.assertEqual(28, countSawSequences(2, 4))
        self.assertEqual(2, countSawSequences(3, 2))
        self.assertEqual(2, countSawSequences(4, 2))
        self.assertEqual(2, countSawSequences(5, 2))


def main():
    unittest.main()


if __name__ == '__main__':
    main()