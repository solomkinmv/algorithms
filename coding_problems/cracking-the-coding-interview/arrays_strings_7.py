"""
Renull col and row
"""

def renull(matrix, row, col, m, n):
    matrix[row] = [0] * n
    for i in range(m):
        matrix[i][col] = 0

def go_through(matrix, m, n):
    cells = list()
    for i in range(m):
        for j in range(n):
            if matrix[i][j] == 0:
               cells.append((i, j))
    print(cells)
    for cell in cells:
       renull(matrix, cell[0], cell[1], m, n)


if __name__ == '__main__':
    m = 10
    n = 5
    matrix = [[i * j for j in range(n)] for i in range(m)]
