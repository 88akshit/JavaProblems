package com.company;

import java.util.*;

public class Matrix {
    int [][] mat;
    int x;
    int y;
    Matrix(int x, int y ){
        this.mat = new int[x][y];
        this.x = x;
        this.y = y;

    }
    private  int [][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
    private  char[][] gridCopy;

    public void buildMat(){
        int k = 1;
        for(int i=0;i<mat.length;i++){
            for(int j = 0;j<mat[i].length;j++){
                mat[i][j] = k;
                k++;
                System.out.print(mat[i][j]);
            }
            System.out.println("");
        }
    }
    public void printMat(){
        int k = 1;
//        int [][] mx = {{0,1},{2,3}};
//        System.out.println(mx[0][0]+" "+mx[0][1]);
        for(int i=0;i<mat.length;i++){
            for(int j = 0;j<mat[i].length;j++){
                System.out.print(mat[i][j]);
            }
            System.out.println("");
        }
    }
    public void reverse(){
        for(int i=0;i<mat.length;i++){
            for(int j = i;j<mat[0].length;j++){
                int temp = 0;
                temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
                System.out.print(i+".."+j+" ");
            }
            System.out.println("");
        }

        for(int i=0;i<mat.length;i++){
            for(int j = 0;j<mat.length/2;j++){
                int temp = 0;
                temp = mat[i][j];
                mat[i][j] = mat[i][mat.length-1-j];
                mat[i][mat.length-1-j] = temp;
                System.out.print(i+".."+j+" ");
            }
            System.out.println("");
        }
    }

    public  int[][] roadsBuildingSolution(int cities, int[][] roads) {

        int [][] adjmatrix = new int [cities][cities];
        List<List<Integer>> res = new ArrayList<>();
        int [][] li = new int [cities][cities];
        for (int[] eachRow : roads) {

            System.out.println(eachRow[0]+" "+eachRow[1]);
            adjmatrix[eachRow[0]][eachRow[1]] = 1;
            adjmatrix[eachRow[1]][eachRow[0]] = 1;
        }

        for(int i=0; i< adjmatrix.length; i++) {
            for(int j=0; j< adjmatrix[i].length; j++) {
                System.out.print(adjmatrix[i][j] + "\t");
                if(adjmatrix[i][j]==0 && i!=j){
                    if(!res.contains((List.of(i,j))) && !res.contains(List.of(j,i)) ){
                        res.add(new ArrayList<>(List.of(i,j)));
                    }
                }
            }
            System.out.println("");
        }
        System.out.println(res);
        int[][] intArray = res.stream().map(  u  ->  u.stream().mapToInt(i->i).toArray()  ).toArray(int[][]::new);

        //String[][] stringArray = mainList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        return intArray;
    }

    public  int numberOfIslandsWithBFS(char [][] grid){
        int islands =0;
        if (grid == null || grid.length==0){
            return  0;
        }
        int rows = grid.length;
        int column = grid[0].length;
        for(int i = 0 ;i<rows;i++){
            for(int j = 0;j<column;j++){
                if(grid[i][j]=='1'){
                    islands++;
                    fillWithWater(grid , rows, column,i,j);
                }
            }
        }
        return islands;
    }
    public void fillWithWater(char grid[][], int rows , int cols, int i , int j ){
        Queue<Integer> q = new LinkedList<>();
        // 2D -> 1 D = R* #cols + C
        // 1D -> 2D = R (r/#cols)  and C (c%#cols)
        q.add(i = i*cols+j);
        grid[i][j] = '0';
        while(!q.isEmpty()){
            int cord  = q.poll();
            int row = cord/cols;
            int col = cord%cols;

            for (int [] d : directions){
                int x = row+d[0];
                int y = col+d[1];
                if(x> -1 && y>-1 && x< rows && y< cols && grid [x][y]=='1'){
                    q.add(i = x*cols+y);
                    grid[x][y] = '0';
                }

            }
        }


    }

    public int numIslandsWithDFS(char[][] grid) {
        //set grid copy to the current grid
        gridCopy = grid;

        //initialize number of islands to zero
        int numberOfIslands = 0;

        //iterate through every index of the grid
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                //attempt to "sink" the current index of the grid
                numberOfIslands += sink(gridCopy, i, j);
            }
        }

        //return the total number of islands
        return numberOfIslands;
    }

    int sink(char[][] grid, int i, int j) {
        //check the bounds of i and j and if the current index is an island or not (1 or 0)
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return 0;
        }

        //set current index to 0
        grid[i][j] = '0';

        // sink all neighbors of current index
        sink(grid, i + 1, j);
        sink(grid, i - 1, j);
        sink(grid, i, j + 1);
        sink(grid, i, j - 1);

        //increment number of islands
        return 1;
    }
}
