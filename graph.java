package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Graph {
    private List<List<Integer>> adj = new ArrayList<>();
    private int nodesList;
    private boolean found;

    Graph(int n){
        System.out.println("Graph");
        this.nodesList = n;
        for(int i = 0;i<=nodesList;i++){
            this.adj.add(new ArrayList<Integer>());
        }
    }
    public  void addEdge(int a , int b){
        List<Integer> temp =  adj.get(a);
        temp.add(b);
    }
    public void printGraph(){
        for(int i = 0;i<=nodesList;i++){
            System.out.println(i+" ->"+adj.get(i));
        }
    }
    public  void dfs(){
        int [] visited = new int[nodesList+1];
        List<Integer> path = new ArrayList<>();
        dfsUtil(1,visited, path);
        System.out.println(path);
    }

    public void bfs(){
        int [] visited = new int[nodesList+1];
        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        while(!q.isEmpty()){
            int n = q.poll();
            List<Integer> nodeList = adj.get(n);
            for(int node : nodeList){
                if(node==18){
                    found = true;
                    System.out.println("found "+node);
                    return;
                }
                if(visited[node]==0 && !found){
                    visited[node] = 1;
                    q.add(node);
                }
            }
        }
        System.out.println("Not found ");

    }

    public void bfsWithPath(){
        Queue<List<Integer>> q = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        q.add(Arrays.asList(0));
        int goalNode = 4;
        while(!q.isEmpty()){
            List<Integer> n = q.poll();
            int lastNode = n.get(n.size()-1);
            if(lastNode==goalNode){
                result.add(n);
            }else{
                List<Integer> neighboursList = adj.get(lastNode);
                for(int node : neighboursList){
                    // Appending the list
                    List<Integer> newPath = new ArrayList<>(n);
                    newPath.add(node);
                    q.add(newPath);
                }
            }
        }
        System.out.println(result);

    }

    public void dfsUtil(int node , int[] visited, List<Integer> path){
        visited[node] = 1;
        System.out.println("Node List "+adj.get(node));
        for(int n: adj.get(node)) {
            System.out.println("Node "+n);
            if(visited[n]==0 && !found) {
                System.out.println("DFS visited "+n);
                path.add(n);
                if(n==5){
                    System.out.println("Found");
                    found = true;
                    return;
                }else{
                    dfsUtil(n, visited, path);
                }
            }
        }
    }

    public  void shortestDistance(int src){
        int distanceArray[] = new int[nodesList+1];
        for(int i = 0;i<nodesList;i++) distanceArray[i] = Integer.MAX_VALUE;
        Queue<Integer> q = new LinkedList<>();
        distanceArray[src] = 0;
        q.add(src);

        while(!q.isEmpty()){
            int n = q.poll();
            for(int node : adj.get(n)){
                if(distanceArray[n]+1<distanceArray[node]){
                    distanceArray[node] = distanceArray[n]+1;
                    q.add(node);
                }
            }
        }
        System.out.println(Arrays.toString(distanceArray));


    }
    public  void shortestDistanceWeight(int src){

    }

}
