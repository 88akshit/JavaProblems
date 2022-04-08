package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class  Pair implements  Comparable<Pair>{
    int distance =0,price = 0, row = 0, column = 0;
    Pair(int distance, int price , int row , int column){
        this.distance = distance;
        this.price = price;
        this.row = row;
        this.column = column;
    }
    @Override
    public int compareTo(Pair o) {
        if(this.distance!=o.distance){
            return this.distance-o.distance;
        }
        else  if(this.price!=o.price){
            return  this.price-o.price;
        }
        else if(this.row!=o.row){
            return this.row-o.row;
        }
        else {
            return this.column-o.column;
        }
    }

}
public class KHotels {

    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
        int n = grid.length;
        int m = grid[0].length;
        int sr = start[0];
        int sc = start[1];
        int lr = pricing[0];
        int rr = pricing[1];
        boolean[][] vis = new boolean[n][m];
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        List<List<Integer>> ans = new ArrayList<>();

        pq.add(new Pair(0, grid[sr][sc], sr, sc));
        vis[sr][sc] = true;

        while (pq.size() != 0) {
            Pair rp = pq.poll();

            if (rp.price >= lr && rp.price <= rr && --k >= 0) {
                ArrayList<Integer> sa = new ArrayList<>();
                sa.add(rp.row);
                sa.add(rp.column);

                ans.add(sa);
            }

            if (k == 0)
                break;

            for (int[] d : dir) {
                int r = rp.row + d[0];
                int c = rp.column + d[1];

                if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] > 0 && !vis[r][c]) {
                    pq.add(new
                            Pair(rp.distance + 1, grid[r][c], r, c));
                    vis[r][c] = true;
                }
            }
        }
        return ans;
    }
}
