def dfsr(G, vertex, k, investigated=None):
    """
    Recursive Depth-First Search.
    :param G: graph dictionary.
    :param vertex: initial vertex.
    :param k: number of vertex.
    """
    if investigated is None:
        investigated = {}

    investigated[vertex] = k  # set initial vertex as investigated

    for u in G[vertex]:
        if u not in investigated:
            k = dfsr(G, u, k+1, investigated)

    return k


if __name__ == '__main__':
    graph = {}
    print 'Graph initialization'
    graph['s'] = ['a', 'b']
    graph['a'] = ['c', 'd', 's']
    graph['b'] = ['d', 's']
    graph['c'] = ['a', 'd', 'e']
    graph['d'] = ['a', 'b', 'c', 'e']
    graph['e'] = ['c', 'd']

    print 'DFSR'
    vertexes = {}
    dfsr(graph, 's', 1, vertexes)
    print vertexes
