package com.company;

import java.util.*;

public class MinKnightMoves {
    class  Coordinates{
        int x;
        int y;
        Coordinates(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Coordinates{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public int solution(int x , int y){
        int [] xdir = { -2,-2,-1,-1, 1 ,1 ,2,2};
        int [] ydir  = {-1, 1,-2, 2 ,-2,2,-1,1};
        int moves = 0;
        Queue<Coordinates> q = new LinkedList<>();

        q.add(new Coordinates(0,0));
        while(!q.isEmpty()){

            int size = q.size();
            for(int i = 0;i<size;i++){

                Coordinates p = q.poll();
                if(p.x==x && p.y==y){
                    System.out.println("No of Moves "+moves);
                    return moves;
                }
                for (int j =0;j<xdir.length;j++){
                    int newPx = p.x+xdir[j];
                    int newPy = p.y+ydir[j];
                    q.add(new Coordinates(newPx,newPy));
                }
            }
            moves++;
        }
        return  -1;
    }
    public int solutionWithPath(int x , int y){
        int [] xdir = { -2,-2,-1,-1, 1 ,1 ,2,2};
        int [] ydir  = {-1, 1,-2, 2 ,-2,2,-1,1};
        int moves = 0;
        Queue<List<Coordinates>> q = new LinkedList<>();
        List<List<Coordinates>> result= new ArrayList<>();
        q.add(Arrays.asList(new Coordinates(0,0)));

        while(!q.isEmpty()){

            List<Coordinates> p = q.poll();
            Coordinates lastCord = p.get(p.size()-1);
            if(lastCord.x==x && lastCord.y==y){
                result.add(p);
                System.out.println(result);
                return 1;
            }
            else{
                for (int j =0;j<xdir.length;j++){
                    int newPx = lastCord.x+xdir[j];
                    int newPy = lastCord.y+ydir[j];
                    List<Coordinates> newPath = new ArrayList<>(p);
                    newPath.add(new Coordinates(newPx,newPy));
                    q.add(newPath);
                }
            }
        }
        System.out.println(result);
        return  -1;
    }
}

