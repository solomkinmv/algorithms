"""
Traverse matrix by spiral
"""

def spiral(m):
    res = list()
    layers = round((min(len(m), len(m[0])) / 2 + 0.5))
    for layer in range(layers):
        print("layer " + str(layer))
        print(res)
        for j in range(layer, len(m[0]) - layer):
            res.append(m[layer][j])
        for i in range(1 + layer, len(m) - layer):
            res.append(m[i][len(m[0]) - 1 - layer])
        if layer != layers - 1:
            for j in range(1 + layer, len(m[0]) - layer):
                res.append(m[len(m) - 1 - layer][len(m[0]) - 1 - j])
            for i in range(1 + layer, len(m) - 1 - layer):
                res.append(m[len(m) - 1 - i][layer])
    return ' '.join(res)


if __name__ == '__main__':
    n = 5
    m = 3
    matrix = [[str(i) + str(j) for j in range(n)] for i in range(m)]
    print(matrix)
    print(spiral(matrix))

