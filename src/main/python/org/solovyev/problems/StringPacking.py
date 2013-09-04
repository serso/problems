import unittest

# http://habrahabr.ru/post/191498/


def findSmallestPackedStringIJ(s, m, l, r):
    if m[l][r] != -1:
        return m[l][r]

    if l == r:
        m[l][r] = 1
    else:
        sum = 4
        c = s[l]
        for i in range(l+1, r+1):
            if c != s[i]:
                sum = 100000000
                break

        left = findSmallestPackedStringIJ(s, m, l+1, r) + 1
        right = findSmallestPackedStringIJ(s, m, l, r-1) + 1
        m[l][r] = min(left, right, sum)

    return m[l][r]


def findSmallestPackedString(s):
    m = [[-1 for x in range(len(s))] for x in range(len(s))]
    return findSmallestPackedStringIJ(s, m, 0, len(s)-1)


class CountStringsTests(unittest.TestCase):
    def testOne(self):
        self.assertEqual(len('AAA'), findSmallestPackedString('AAA'))
        self.assertEqual(len('6(A)'), findSmallestPackedString('AAAAAA'))
        self.assertEqual(len('3(2(A)2(B))C'), findSmallestPackedString('AABBAABBAABBC'))


def main():
    unittest.main()


if __name__ == '__main__':
    main()