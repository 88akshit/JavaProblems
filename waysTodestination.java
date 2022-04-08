package com.company;

import java.util.*;

public class WaysToDestination {

    // https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
    // Return the number of ways you can arrive at your destination (n-1) in the shortest amount of time
    // We have implemented using BFS

    class Node{
        int v;
        int cost;
        Node(int v , int cost){
            this.v = v;
            this.cost = cost;
        }

        public int getV() {
            return v;
        }
        public  int getCost(){
            return cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", cost=" + cost +
                    '}';
        }
    }

    public int countPaths(int n, int[][] roads) {

        Map<Integer, List<Node>> adjList = new HashMap<>();
        for (int[] edge: roads) {
            if(adjList.containsKey(edge[0])){
                List<Node> list = adjList.get(edge[0]);
                list.add(new Node(edge[1],edge[2]));
            }else{
                List<Node> list = new ArrayList<>();
                list.add(new Node(edge[1],edge[2]));
                adjList.put(edge[0],list);

            }

            if(adjList.containsKey(edge[1])){
                List<Node> list = adjList.get(edge[1]);
                list.add(new Node(edge[0],edge[2]));
            }else{
                List<Node> list = new ArrayList<>();
                list.add(new Node(edge[0],edge[2]));
                adjList.put(edge[1],list);
            }

        }
        System.out.println(adjList);

        long[] cost = new long[n];
        long [] paths = new long[n];  //store how many paths are there to reach the destination
        int [] parent = new int[n];

        parent[0] =-1;
        Arrays.fill(cost, -1); // -1 -> not visited
        // Min heap
        Queue<long[]> q = new PriorityQueue<>((l, r) -> Long.compare(l[1], r[1])); // [node, cost]  and TC O(logn)

        q.add(new long[]{0,0});
        paths[0] = 1; // to make path accumulation work
        cost[0] = 0;
        int destination = n-1;
        while (!q.isEmpty()) {

            long[] f = q.poll();
            System.out.println("Popped item "+Arrays.toString(f));
            int fnode = (int) f[0];

            if (fnode == destination){
                System.out.println(Arrays.toString(parent));

                printPathUtil(parent, destination);
                System.out.println("");
                return (int)(paths[fnode]);
            }

            for (Node ch : adjList.get(fnode)) {
                long newCost = f[1] + ch.getCost();
                if (newCost == cost[ch.getV()]) // if same cost to reach n-1 then increment path
                    paths[ch.getV()] += paths[fnode];

                if (cost[ch.getV()] == -1 || newCost < cost[ch.getV()]) { // if better cost update
                    cost[ch.getV()] = newCost;
                    paths[ch.getV()] = paths[fnode]; // we have found a new path which has minimum distance
                    q.add(new long[]{ch.getV(), newCost});
                    parent[ch.getV()] = fnode;
                }
            }
            for(long [] a :q){
                System.out.print(Arrays.toString(a)+" ");
            }
            System.out.println("");
            System.out.println("Path "+Arrays.toString(paths));
            System.out.println("Cost "+Arrays.toString(cost));
        }
        return -1; // shouldn't get here
    }
    public void printPathUtil(int parent[], int destination){
        //if vertex is source then stop recursion
        if(parent[destination] == -1) {
            System.out.print("0 ");
            return;
        }
        printPathUtil(parent, parent[destination]);
        System.out.print(destination + " ");
    }

}
