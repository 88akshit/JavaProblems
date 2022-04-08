package com.company;

import java.util.*;
class DPair{
    int v;
    int wt;
    int stops;

    DPair(int v, int wt, int stops){
        this.v = v;
        this.wt = wt;
        this.stops = stops;
    }

}
public class KStopFlight {


    public int findCheapestPriceWithDjks(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<DPair>> graph = new ArrayList<>();

        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge : flights){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            graph.get(u).add(new DPair(v, wt, 0));

        }

        //Min heap
        PriorityQueue<DPair> pq = new PriorityQueue<>((a, b) -> a.wt - b.wt);
        pq.add(new DPair(src, 0, -1));

        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);

        while(pq.size() > 0){
            DPair p = pq.poll();
            System.out.println("Vertex "+p.v);
            System.out.println("Dist "+p.stops);

            // If current place stops greater than minimum k  ignore or stop array have better stops point dont process it
            if(p.stops > k || stop[p.v] < p.stops)
                continue;

            stop[p.v] = p.stops;
            System.out.println(Arrays.toString(stop));
            if(p.v == dst)return p.wt;

            for(DPair nbr : graph.get(p.v)){
                pq.add(new DPair(nbr.v, p.wt + nbr.wt, p.stops + 1));

            }

        }
        return -1;
    }
        int fare = Integer.MAX_VALUE;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

            Map<Integer, List<Map<Integer,Integer>>> adj = new HashMap<>();

            for(int [] flight: flights){
                if(!adj.containsKey(flight[0])){
                    List<Map<Integer,Integer>> li = new ArrayList<>();
                    Map<Integer,Integer> m = new HashMap<>();
                    m.put(flight[1],flight[2]);
                    li.add(m);
                    adj.put(flight[0],li);
                }
                else{
                    List<Map<Integer,Integer>> li = adj.get((flight[0]));
                    Map<Integer,Integer> m = new HashMap<>();
                    m.put(flight[1],flight[2]);
                    li.add(m);
                    adj.put(flight[0],li);
                }
            }

            Boolean [] visited = new Boolean[n];
            for (int i = 0; i < n; i++)
                visited[i] = false;

            System.out.println(adj);
            dfs(adj,src,dst,k,0,visited);
            //System.out.println(fare);
            if(fare == Integer.MAX_VALUE){
                return -1;
            }
            return fare;

        }



    void dfs(Map<Integer,List<Map<Integer,Integer>>> adj,int src,int dst,int k,int totalCost, Boolean [] visited){

            if(k<-1){
                System.out.println("Returning k less "+k);
                return;
            }
            if(src==dst){
                System.out.println("TotalCost "+totalCost);
                fare = Math.min(fare,totalCost);
                //System.out.println("Fare "+fare);
                return;
            }
            visited[src] = true;
            List<Map<Integer,Integer>> li = adj.get(src);
            if(li==null) return;
            for(int i=0;i<li.size();i++){
                int value = 0;
                int key = 0;
                for (Map.Entry<Integer, Integer> e:  li.get(i).entrySet()) {
                    key = e.getKey();
                    value = e.getValue();
                }
                //System.out.println(key+" "+value);
                System.out.println(Arrays.toString(visited));
                if(!visited[key] && totalCost+value<=fare){
                    System.out.println("calling DFS for"+key);
                    dfs(adj,key,dst,k-1,totalCost+value,visited);
                }
            }
            visited[src] = false;
        }
}

