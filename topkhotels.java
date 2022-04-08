package com.company;
import java.util.Arrays;
import java.util.*;

public class TopKHotels {
    /*
    The interview lasted for 1hr and contained one (medium-hard) question:
    Given a list of hotelId, parentHotelId and a score retrieve the top k root parentHotelIds with highest scores:

            [{0, 1, 10}, {1, 2, 20}, {3, 4, 10}, {7, 8, 5}] K = 2

    Result: [[2, 30], [4,10]] */

    public void findTopKHotels(){

        List<int[]> res2 = topKDFS(
                Arrays.asList(
                        new int[]{0, 1, 10},
                        new int[]{1, 2, 20},
                        new int[]{3, 4, 10},
                        new int[]{7, 8, 5}
                ),
                2
        );

//          List<int[]> res2 = topKDFS(
//                Arrays.asList(
//                        new int[]{3,0,14},
//                        new int[]{0, -1, 10},
//                        new int[]{4,0,44},
//                        new int[]{6, -1, 7},
//                        new int[]{10, 6, 13},
//                        new int[]{7, 6, 17},
//                        new int[]{2, -1, 2},
//                        new int[]{25, 14, 10},
//                        new int[]{12, 2, 10},
//                        new int[]{13, 0, 1},
//                        new int[]{14, 2, 9}
//                ),
//                3);

        for (int[] val: res2) {
            System.out.println(Arrays.toString(val));
        }
    }
    public List<int[]> topKDFS(List<int[]> hotels, int k) {
        // map of child_hotel=(parent_hotel, rating)
        // First create an array of not having parent nodes as keys
        Map<Integer, Map<Integer, Integer>> parent = new HashMap<>();
        for (int[] hotel: hotels) {
            HashMap<Integer, Integer> val = new HashMap<>();
            val.put(hotel[1],hotel[2]);
            parent.put(hotel[0], val);
        }
        System.out.println(parent);

        // Maintaing a total array which will have the accumated rating of hotels
        Map<Integer, Integer> total = new HashMap<>();

        for (int[] hotel: hotels) {
            System.out.println("calling with "+hotel[0]);
            dfs(hotel[0], 0, parent, total);
        }
        System.out.println(total);
        List<int[]> ans = new ArrayList<>();

        // Min Heap
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.getValue(),o2.getValue()));
        for (Map.Entry<Integer, Integer> e: total.entrySet()) {
            pq.add(e);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        System.out.println("Priority Queue"+pq);
        while (!pq.isEmpty()) {
            Map.Entry<Integer,Integer> hotel = pq.poll();
            System.out.println("Hotel Id : " + hotel.getKey() + " with Score -->" + hotel.getValue() );
        }
        return ans;
    }

    private void dfs(int hotel, int rating, Map<Integer, Map<Integer, Integer>> parent, Map<Integer, Integer> total) {
        if (!parent.containsKey(hotel)) {
            // using getDefault so that old values are not overwritten
            total.put(hotel, total.getOrDefault(hotel, 0) + rating);
            System.out.println(total);
            return;
        }
        Map<Integer, Integer> pair = parent.get(hotel);
//        System.out.println(pair);
        for ( Integer key : pair.keySet() ) {
//            System.out.println( key );
            dfs(key, rating + pair.get(key), parent, total);
            // For marking as visited
            HashMap<Integer, Integer> va = new HashMap<>();
            va.put(key,0);
            parent.put(hotel, va);
            System.out.println(parent);

        }
    }
}
