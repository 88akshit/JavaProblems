package com.company;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class RottenOranges {

    public int solution(int [][] grid){
        HashSet<String> fresh = new HashSet<>();
        HashSet<String> rotten = new HashSet<>();

        for (int i =0;i<grid.length;i++){
            for (int j =0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    fresh.add(""+i+j);
                }else if (grid[i][j]==2){
                    rotten.add(""+i+j);
                }
            }
        }
        int min = 0;
        int [][] directions =  {{0,1},{1,0},{-1,0},{0,-1}};

        //System.out.println(fresh);
        System.out.println(rotten);

        while(fresh.size()>0){

            HashSet<String> infected = new HashSet<>();
            for (String a : rotten){

                int i = a.charAt(0)-'0';
                int j = a.charAt(1)-'0';

                for(int [] direct : directions){
                        int nexti = i+direct[0];
                        int nextj  = j+direct[1];
                        if(fresh.contains(""+nexti+nextj)){
                            fresh.remove(""+nexti+nextj);
                            infected.add(""+nexti+nextj);
                        }
                }
            }
            if(infected.size()==0){
                System.out.println("-1");
                return -1;
            }
            rotten = infected;
            System.out.println(rotten);
            min++;
        }
        System.out.println(min);
        return min;




    }
}
