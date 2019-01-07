def strong_connected_components(G):
    """
    Get strong connected components.
    1. Get indices of vertices. The most connected vertices will
    be earlier in list. From these elements we can go further in graph.
    2. Invert graph.
    3. Go through vertices in indices list. Every DFSR cycle gets
    one component.

    :param G: graph dictionary
    :return: list of components.
    """
    indices = dfs_loop(G)
    return dfs_loop(invert_graph(G), indices)


def dfs_loop(G, indices=None):
    """
    Loop through all vertices.

    :param G: graph dictionary.
    :param indices: list of indices.
    :return: after first iteration list of indices
    and after second iteration list of components.
    """
    # TODO split into two functions.
    investigated = []  # list of investigated vertices
    if indices is None:  # first loop
        indices = []  # time of work for every vertex
        for vertex in G:
            if vertex not in investigated:
                dfsr(G, vertex, investigated, indices)

    else:  # second loop
        components = []
        for vertex in indices:
            if vertex not in investigated:
                component = []
                dfsr(G, vertex, investigated, component)
                components.append(component)

        return components

    return indices


def dfsr(G, vertex, investigated, indices):
    """
    Recursive Depth-First Search.
    :param G: graph dictionary.
    :param vertex: initial vertex.
    """
    investigated.append(vertex)  # set initial vertex as investigated

    for u in G[vertex]:
        if u not in investigated:
            dfsr(G, u, investigated, indices)

    indices.insert(0, vertex)  # latest vertices will be earlier in list


def invert_graph(G):
    """
    Create inverted graph.
    :param G: graph dictionary.
    :return: inverted graph dictionary.
    """
    inverted_graph = {}
    vertices = [v for v in G]
    for v in vertices:
        inverted_graph[v] = []

    for v1 in G:
        for v2 in G[v1]:
            inverted_graph[v2].append(v1)

    return inverted_graph


if __name__ == '__main__':
    graph = {}
    print 'Graph initialization'
    graph['a'] = ['b']
    graph['b'] = ['c', 'e', 'f']
    graph['c'] = ['g', 'd']
    graph['d'] = ['c', 'h']
    graph['e'] = ['a', 'f']
    graph['f'] = ['g']
    graph['g'] = ['f', 'h']
    graph['h'] = []

    print 'Strong Connected Components'
    print strong_connected_components(graph)
