# http://habrahabr.ru/post/191498/

m = int(input("Enter number of rows: \n"))
n = int(input("Enter number of columns: \n"))

board = [[-1 for x in range(n)] for x in range(m)]
board[m-1][n-1] = 1

def count(board, i, j):
    if i < 0 or j < 0 or i >= m or j >= n:
        return 0

    if board[i][j] != -1:
        return board[i][j]

    if board[j][i] != -1:
        return board[j][i]

    result = count(board, i+2, j-1)
    result +=count(board, i+2, j+1)
    result +=count(board, i-1, j+2)
    result +=count(board, i+1, j+2)

    board[i][j] = result

    return result


print("Count:" + repr(count(board, 0, 0)))