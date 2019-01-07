def dfs(G, init):
    """
    Recursive Depth-First Search.
    :param G: graph dictionary.
    :param init: initial vertex.
    """
    k = 1  # number of vertex
    stack = [init]  # stack with initial vertex
    investigated = {init: k}  # set initial vertex as investigated

    while len(stack) > 0:
        v = stack[len(stack) - 1]  # get top vertex from stack
        # go to the next not investigated vertex
        for u in G[v]:  # for every edge get second vertex
            # if vertex is not investigated add it to stack and go further
            if u not in investigated:
                k += 1
                investigated[u] = k
                stack.append(u)
                break
        else:
            stack.pop()

    return investigated


if __name__ == '__main__':
    graph = {}
    print 'Graph initialization'
    graph['s'] = ['a', 'b']
    graph['a'] = ['c', 'd', 's']
    graph['b'] = ['d', 's']
    graph['c'] = ['a', 'd', 'e']
    graph['d'] = ['a', 'b', 'c', 'e']
    graph['e'] = ['c', 'd']

    print 'DFS'
    print dfs(graph, 's')
