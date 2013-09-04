import unittest

# http://habrahabr.ru/post/191498/


def findSmallestPackedStringIJ(s, m, l, r):
    if l >= len(s) or r >= len(s):
        return 0

    if m[l][r] != -1:
        return m[l][r]

    if l == r:
        m[l][r] = 1
    else:
        sum = 4
        c = s[l]

        k = l
        for i in range(l + 1, r + 1):
            if c != s[i]:
                break
            else:
                k = i

        if k == l:
            sum = 1000000000
        else:
            if c == s[k]:
                sum += findSmallestPackedStringIJ(s, m, k+1, r)
            else:
                sum += findSmallestPackedStringIJ(s, m, k, r)

        left = findSmallestPackedStringIJ(s, m, l + 1, r) + 1
        right = findSmallestPackedStringIJ(s, m, l, r - 1) + 1

        m[l][r] = min(left, right, sum)

    return m[l][r]


def findSmallestPackedString(s):
    m = [[-1 for x in range(len(s))] for x in range(len(s))]
    return findSmallestPackedStringIJ(s, m, 0, len(s) - 1)


class CountStringsTests(unittest.TestCase):
    def testOne(self):
        self.assertEqual(len('AAA'), findSmallestPackedString('AAA'))
        self.assertEqual(len('6(A)'), findSmallestPackedString('AAAAAA'))
        self.assertEqual(len('6(A)B'), findSmallestPackedString('AAAAAAB'))
        self.assertEqual(len('C6(A)B'), findSmallestPackedString('CAAAAAAB'))
        self.assertEqual(len('8(A)8(B)'), findSmallestPackedString('AAAAAAAABBBBBBBB'))
        # todo serso: in case of nested brackets we should not add 1 twice
        self.assertEqual(len('2(AABB)') + 1, findSmallestPackedString('AABBAABB'))
        self.assertEqual(len('3(2(A)2(B))C') + 1, findSmallestPackedString('AABBAABBAABBC'))


def main():
    unittest.main()


if __name__ == '__main__':
    main()