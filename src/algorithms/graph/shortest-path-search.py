def shortest_path_search(G, init):
    """
    Shortest path search based on BFS algorithm.
    Breadth-First Search
    :param G: graph dictionary.
    :param init: initial vertex.
    """
    k = 1  # number of vertex
    investigated = {init: k}  # set initial vertex as investigated
    queue = [init]  # create new queue
    distances = {init: 0}  # add initial vertex to distances with 0 distance

    while len(queue) > 0:
        v = queue.pop(0)  # get first vertex from queue
        for u in G[v]:  # for every edge get second vertex
            if u not in investigated:  # if vertex is not investigated
                distances[u] = distances[v] + 1
                k += 1
                investigated[u] = k
                queue.append(u)

    return distances


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
    print shortest_path_search(graph, 's')
