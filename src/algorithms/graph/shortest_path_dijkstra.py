def dijkstra(G, start, end):
    distances = {}
    precusors = {}
    V = set(G.keys())
    for v in G:
        distances[v] = float('inf')
        precusors[v] = None

    distances[start] = 0
    x = {start}
    v = start  # starting point
    while x != V:
        for u in G[v]:
            if distances[u] > distances[v] + 1:
                distances[u] = distances[v] + 1
                precusors[u] = v

        v = min(V - x, key=lambda arg: distances[arg])
        x.add(v)
        if v == end:
            break

    return distances, precusors


if __name__ == '__main__':
    graph = {}
    print 'Graph initialization'
    graph['s'] = ['a', 'b']
    graph['a'] = ['c', 'd', 's']
    graph['b'] = ['d', 's']
    graph['c'] = ['a', 'd', 'e']
    graph['d'] = ['a', 'b', 'c', 'e']
    graph['e'] = ['c', 'd']

    print 'Distances'
    print dijkstra(graph, 's', 'a')
