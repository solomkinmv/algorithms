def find_connected_components(G):
    """
    Find connected components.
    :param G: graph dictionary.
    """
    investigated = []  # list of investigated vertexes
    components = []  # list of components
    n = 0  # count of components

    for v in G:
        if v not in investigated:
            component = bfs(G, v)
            components.append(component)
            n += 1
            for vertex in component:
                investigated.append(vertex)

    return components


def bfs(G, init):
    """
    Breadth-First Search
    :param G: graph dictionary.
    :param init: initial vertex.
    """
    k = 1  # number of vertex
    investigated = {init: k}  # set initial vertex as investigated
    queue = [init]  # create new queue

    while len(queue) > 0:
        v = queue.pop(0)  # get first vertex from queue
        for u in G[v]:  # for every edge get second vertex
            if u not in investigated:  # if vertex is not investigated
                k += 1
                investigated[u] = k
                queue.append(u)

    return investigated


if __name__ == '__main__':
    graph = {}
    print 'Graph initialization'
    graph['a'] = ['b', 'c']
    graph['b'] = ['a', 'c']
    graph['c'] = ['a', 'b']

    graph['d'] = ['e']
    graph['e'] = ['d']

    graph['f'] = ['g', 'h', 'i']
    graph['g'] = ['f', 'h', 'i']
    graph['h'] = ['f', 'g', 'i']
    graph['i'] = ['f', 'g', 'h']

    print 'Components'
    print find_connected_components(graph)
