package com.company;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        /* Basic Graph Problems */
        //graphProblem();


        //priority();
        //matrix();
          //kHotels();
        //stackProblem();
        //treeProblem();

//          HotelLambda h = new HotelLambda();
//          h.solution();

//          TopKHotels tk = new TopKHotels();
//          tk.findTopKHotels();

//        HotelChains hc = new HotelChains();
//        hc.solution();

//        ShortestDistance shortestDistance = new ShortestDistance();
//        shortestDistance.solution();

//        FlightDistance f = new FlightDistance();
//        f.solution();

        //kStopFLights();

        //binarySearch();


        //MinKnightMoves mk = new MinKnightMoves();
        //System.out.println(mk.solution(2,1));
        //System.out.println(mk.solutionWithPath(2,1));
//
//        WaysToDestination ways = new WaysToDestination();
//        int [][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
//        System.out.println(ways.countPaths(7, roads));

//        RottenOranges r = new RottenOranges();
//        int [][] grid = {{2,1,1},{1,1,0},{0,1,1}};
//        System.out.println(r.solution(grid));

        //Basics b = new Basics();
//        LinkListProb l = new LinkListProb();
//        l.solution();

        SlidingWindow sw = new SlidingWindow();
        String s = "PWWKEW";
        sw.longestSubStringWithoutRepeatingCharacters(s);
        sw.anotherMethod(s);



    }


    public static void graphProblem() {
        Graph gr = new Graph(8);
        gr.addEdge(0, 1);
        gr.addEdge(1, 2);
        gr.addEdge(2, 3);
        gr.addEdge(2, 4);
        gr.addEdge(3, 4);
        gr.addEdge(4, 5);
        gr.addEdge(5, 3);
        gr.addEdge(1, 3);
        gr.addEdge(1, 5);
        gr.addEdge(4, 6);
        gr.addEdge(7, 8);
        gr.addEdge(4, 7);
        gr.printGraph();
        //gr.dfs();
        //gr.bfs();
        gr.bfsWithPath();
        //gr.shortestDistance(0);
        //gr.shortestDistanceWeight(0);
    }

    public static void priority() {
        PriorityQueuePrblems prblems = new PriorityQueuePrblems(5);
//        prblems.addElements(234);
//        prblems.addElements(34);
//        prblems.addElements(4);
//        prblems.addElements(434);
//
//        prblems.printQ();
        //prblems.priorityQueueComp();

//        List<Integer> nums = new ArrayList<>(List.of(10,20,7));
//        System.out.println(prblems.sum(nums,4));

        String positiveKeywords = "breakfast beach city center location metro view staff price";
        String negativeKeywords = "not";
        int numberOfHotels = 5;
        List<Integer> hotelIds = new ArrayList<>(List.of(1, 2, 1, 1, 2));
        int k = 2;
        List<String> reviews = new ArrayList<>(List.of("This hotel has a nice view of the city center. The location is perfect.",
                "The breakfast is ok. Regarding location, it is quite far from city center but the price is cheap so it is worth.",
                "Location is excellent, 5 minutes from the city center. There is also a metro station very close to the hotel.",
                "They said I couldn’t take my dog and there were other guests with dogs! That is not fair.",
                "Very friendly staff and a good cost-benefit ratio. Its location is a bit far from the city center."));

        System.out.println(prblems.awardTopKHotels(positiveKeywords, negativeKeywords, hotelIds, reviews, k));
    }

    public static void matrix() {
        Matrix mx = new Matrix(3, 3);
//        mx.buildMat();
//        System.out.println("**********");
//        mx.reverse();
//        System.out.println("**********");
//        mx.printMat();
//        int[][] input = {{0, 1}, {1, 2}, {2, 0}};
//        mx.roadsBuildingSolution(4, input);

        char [] [] grid = {
                {'1', '0', '1', '1', '0'},
                {'0', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        //System.out.println(mx.numberOfIslandsWithBFS(grid));
        System.out.println(mx.numIslandsWithDFS(grid));

    }

    public static void kHotels() {
        KHotels hotel = new KHotels();
        int[][] grid = {{1, 2, 0, 1}, {1, 3, 0, 1}, {0, 2, 5, 1}};
        int[] pricing = {2, 5};
        int[] start = {0, 0};
        int k = 3;
        System.out.println(hotel.highestRankedKItems(grid, pricing, start, k));
    }

    public static void stackProblem() {
        StackProblem sp = new StackProblem();
        sp.stackPrb("abc#def##");

    }

    public static void treeProblem() {
        TreeProblem t2 = new TreeProblem(7);

        t2.insertElement(4);
        t2.insertElement(9);
        t2.insertElement(1);
        t2.insertElement(6);
        t2.insertElement(8);
        t2.insertElement(10);
        t2.print();
        System.out.println(t2.find(10));
        t2.findPath(10);

    }

    public static void kStopFLights() {
        int n = 3;
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        KStopFlight k = new KStopFlight();
        //System.out.println(k.findCheapestPrice(n,flights,0,2,0));
        System.out.println(k.findCheapestPriceWithDjks(n, flights, 0, 2, 1));
    }

    public static void binarySearch() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        int left = 0;
        int right = nums.length - 1;
        Boolean found = false;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                System.out.println("found " + mid);
                found = true;
                break;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (!found) {
            System.out.println("Not found ");
        }

    }

}
