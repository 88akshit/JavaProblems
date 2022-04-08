package com.company;
import java.util.*;

class GNode implements Comparator<GNode>
{
    private int v;
    private int weight;

    GNode(int _v, int _w) { v = _v; weight = _w; }

    GNode() {}

    int getV() { return v; }
    int getWeight() { return weight; }

    @Override
    public int compare(GNode node1, GNode node2)
    {
        if (node1.weight < node2.weight)
            return -1;
        if (node1.weight > node2.weight)
            return 1;
        return 0;
    }
}


public class ShortestDistance
{
    void shortestPath(int s, ArrayList<ArrayList<GNode>> adj, int N)
    {
        int dist[] = new int[N];

        for(int i = 0;i<N;i++) dist[i] = Integer.MAX_VALUE;
        dist[s] = 0;

        //Min Heap
        PriorityQueue<GNode> pq = new PriorityQueue<>(N, new GNode());
        pq.add(new GNode(s, 0));

        while(pq.size() > 0) {
            GNode node = pq.poll();

            for(GNode it: adj.get(node.getV())) {
                if(node.getWeight()+ it.getWeight() < dist[it.getV()]) {
                    dist[it.getV()] = dist[node.getV()] + it.getWeight();
                    pq.add(new GNode(it.getV(), dist[it.getV()]));
                }
            }
        }

        for (int i = 0; i < N; i++)
        {
            System.out.print( dist[i] + " ");
        }
    }
    public void solution()
    {
        int n = 6;
        ArrayList<ArrayList<GNode> > adj = new ArrayList<>();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<GNode>());

//        adj.get(0).add(new GNode(1, 2));
//        adj.get(1).add(new GNode(0, 2));
//
//        adj.get(1).add(new GNode(2, 4));
//        adj.get(2).add(new GNode(1, 4));
//
//        adj.get(0).add(new GNode(3, 1));
//        adj.get(3).add(new GNode(0, 1));
//
//        adj.get(3).add(new GNode(2, 3));
//        adj.get(2).add(new GNode(3, 3));
//
//        adj.get(1).add(new GNode(4, 5));
//        adj.get(4).add(new GNode(1, 5));
//
//        adj.get(2).add(new GNode(4, 1));
//        adj.get(4).add(new GNode(2, 1));


        // Directed graph
        adj.get(0).add(new GNode(1, 2));
        adj.get(0).add(new GNode(4, 1));
        adj.get(1).add(new GNode(2, 3));
        adj.get(2).add(new GNode(3, 6));
        adj.get(4).add(new GNode(2, 2));
        adj.get(4).add(new GNode(5, 4));
        adj.get(5).add(new GNode(3, 1));


        shortestPath(0, adj, n);
        shortestPathDAG(0,adj,n);

    }

    void shortestPathDAG(int s, ArrayList<ArrayList<GNode>> adj, int N)
    {
        Stack stack = new Stack();
        int dist[] = new int[N];

        Boolean visited[] = new Boolean[N];
        for (int i = 0; i < N; i++)
            visited[i] = false;

        for (int i = 0; i < N; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack, adj);

        for (int i = 0; i < N; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[s] = 0;

        while (stack.empty() == false)
        {
            int node = (int)stack.pop();

            if (dist[node] != Integer.MAX_VALUE)
            {
                for(GNode it: adj.get(node)) {
                    if(dist[node] + it.getWeight() < dist[it.getV()]) {
                        dist[it.getV()] = dist[node] + it.getWeight();
                    }
                }
            }
        }

        for (int i = 0; i < N; i++)
        {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.print( "INF ");
            else
                System.out.print( dist[i] + " ");
        }
    }
    void topologicalSortUtil(int node, Boolean visited[], Stack stack, ArrayList<ArrayList<GNode>> adj)
    {

        visited[node] = true;
        for(GNode it: adj.get(node)) {
            if(visited[it.getV()] == false) {
                topologicalSortUtil(it.getV(), visited, stack, adj);
            }
        }
        stack.add(node);
    }

}




