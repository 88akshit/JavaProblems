package com.company;

import java.util.*;

public class HotelChains {
    public  void solution() {
        /*
        There is list of hotels and they are given in below format:
        { hotel id, parent hotel id, no. of hotel}
        mutlilevel hotel hierarchy exits in the system.
        We want to know top k hotel chains.
        */


        List<int[]> list = new ArrayList<>();
         list.add(new int[]{ 3,0,14});
         list.add(new int[]{0, -1, 10});
         list.add(new int[]{4,0,44});
         list.add(new int[]{6, -1, 7});
         list.add(new int[]{10, 6, 13});
         list.add(new int[]{7, 6, 17});
         list.add(new int[]{2, -1, 2});
         list.add(new int[]{25, 14, 10});
         list.add(new int[]{12, 2, 10});
         list.add(new int[]{13, 0, 1});
         list.add(new int[]{14, 2, 9});

//        list.add(new int[]{0, 1, 10});
//        list.add(new int[]{2, -1, 0});
//        list.add(new int[]{1, 2, 20});
//        list.add(new int[]{3, 4, 10});
//        list.add(new int[]{4, -1, 0});
//        list.add(new int[]{7, 8, 5});
//        list.add(new int[]{8, -1, 0});

        findKMaxHotels(list, 3); // {0=69, 2=31, 3=14, 4=44, 6=37, 7=17, 25=10, 10=13, 12=10, 13=1, 14=19}

    }

    public void findKMaxHotels(List<int[]> list, int k){

        Map<Integer,List<Integer>> graph = new HashMap<>();
        Map<Integer,Integer> weightMap = new HashMap<>();

        // Create a Graph with all nodes
        // Also a weights graph
        for(int i=0;i<list.size();i++) {
            int[] current = list.get(i);
            int parent = current[1];
            int hotel = current[0];
            weightMap.put(hotel , current[2]);
            if(graph.containsKey(parent)) {
                List<Integer> temp = graph.get(parent);
                temp.add(hotel);
                graph.put(parent, temp);
            }
            else {
                List<Integer> temp = new ArrayList<>();
                temp.add(hotel);
                graph.put(parent, temp);
            }
        }
        System.out.println(graph);
        System.out.println(weightMap);

        Set<Integer> visited = new HashSet<>();
        // Do a DFS on all nodes source as items in list
        for(int i=0;i<list.size();i++) {
            int[] current = list.get(i);
            int hotelId = current[0];
            if(visited.contains(hotelId)) {
                continue;
            }

            System.out.println("DFS with "+hotelId);
            dfs(graph, visited, hotelId , weightMap);
        }

        System.out.println(weightMap);

        // Min Heap
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.getValue(),o2.getValue()));



        for(Map.Entry<Integer,Integer> e : weightMap.entrySet()) {
            pq.add(e);
            if(pq.size()>k){
                pq.poll();
            }
        }

        while(!pq.isEmpty()) {
            Map.Entry<Integer,Integer> temp = pq.poll();
            System.out.println("Brand Id : " + temp.getKey() + " with capacity -->" + temp.getValue() );
        }

    }

    private int dfs(Map<Integer,List<Integer>> graph, Set<Integer> visited, int hotel, Map<Integer,Integer> weightMap) {
        // Base condition where if the node not in graph return weight
        if(!graph.containsKey(hotel))
            return weightMap.get(hotel);

        visited.add(hotel);
        List<Integer> neighbors = graph.get(hotel);
        System.out.println("Neightbours "+neighbors);
        for(Integer n : neighbors) {
            System.out.println("taken node "+n);
            System.out.println("Visited array "+visited);
            if(visited.contains(n))
                continue;
            weightMap.put(hotel, weightMap.get(hotel) + dfs(graph , visited, n , weightMap));
            System.out.println("weight"+weightMap);
        }
        return weightMap.get(hotel);
    }
}
