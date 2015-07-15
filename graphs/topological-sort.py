def topological_sort(G):
    investigated = []  # list of investigated vertices
    topology = []  # graph topology

    for vertex in G:
        if vertex not in investigated:
            dfsr(G, vertex, investigated, topology)

    return topology


def dfsr(G, vertex, investigated, topology):
    """
    Recursive Depth-First Search.
    :param G: graph dictionary.
    :param vertex: initial vertex.
    """

    investigated.append(vertex)  # set initial vertex as investigated

    for u in G[vertex]:
        if u not in investigated:
            dfsr(G, u, investigated, topology)

    topology.insert(0, vertex)


if __name__ == '__main__':
    graph = {}
    print 'Graph initialization'
    graph['underwear'] = ['socks', 'trousers', 'shirt']
    graph['socks'] = ['boots']
    graph['trousers'] = ['boots']
    graph['shirt'] = ['jacket']
    graph['boots'] = []
    graph['jacket'] = []

    print 'Topology'
    print topological_sort(graph)
