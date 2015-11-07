"""
Rotate matrix right to 90 degrees
"""

def rotate(m):
    n = len(m)
    for i in range(n // 2):
        for j in range(n // 2):
            tmp = m[i][j]
            m[i][j] = m[n - 1 - j][i]
            m[n - 1 - j][i] = m[n - 1 - i][n - 1 - j]
            m[n - 1 - i][n - 1 - j] = m[j][n - 1 - i]
            m[j][n - 1 - i] = tmp

    return m


if __name__ == '__main__':
    n = 10
    matrix = [[str(i) + str(j) for j in range(n)] for i in range(n)]
    print(matrix)
    rotate(matrix)
    print(matrix)
