package com.company;


import java.util.*;

class FNode{
    public int ticketPrice;
    public int flightDate;
    public String city;
    public Boolean visited;
    public int currentExp;

    FNode(int ticketPrice , int flightDate , String city){
        this.ticketPrice = ticketPrice;
        this.flightDate = flightDate;
        this.city = city;
        this.visited = false;
        this.currentExp = 0;
    }

}
public class FlightDistance {

    void solution(){

        int n = 3;

        Map<String,Integer> hotelCharges = new HashMap<>();
        hotelCharges.put("Amsterdam",400);
        hotelCharges.put("Paris",500);
        hotelCharges.put("London",300);

        Map<String,ArrayList<FNode>> adj = new HashMap<>();

        ArrayList<FNode> route = new ArrayList<>();
        route.add(new FNode(300, 10,"Paris"));
        adj.put("Amsterdam",route);

        ArrayList<FNode> route2 = new ArrayList<>();
        route2.add(new FNode(300, 13,"London"));
        route2.add(new FNode(400, 21,"Amsterdam"));
        adj.put("Paris",route2);

        ArrayList<FNode> route3 = new ArrayList<>();
        route3.add(new FNode(400, 17,"Amsterdam"));
        route3.add(new FNode(410, 15,"Paris"));
        adj.put("London",route3);

        System.out.println(adj);

        String source = "Amsterdam";

        FNode destination;
        ArrayList<FNode> routes = new ArrayList<>();
        routes = adj.get("Amsterdam");
        destination = routes.get(0);
        int budget = 5000;
        int presentExp = 0;
        presentExp = destination.ticketPrice+ presentExp;
        destination.currentExp = presentExp;
        Stack<FNode> pq = new Stack<>();
        System.out.println(destination);
        pq.add(destination);
        while(pq.size()>0){
            System.out.println("Stack "+pq);
            FNode ds = pq.pop();

            int currentDate = ds.flightDate;
            presentExp = ds.currentExp;
            System.out.println("CurrentDate "+currentDate);
            System.out.println("CurrentCity Popped "+ds.city);
            System.out.println("Current exp until now"+presentExp);
            if(ds.city.equals("Amsterdam") && ds.currentExp<5000){
                    System.out.println("Reached Here"+ds.currentExp +" "+ds.city);
                    break;
            }
            if(ds.currentExp<5000){
                ArrayList<FNode> rout = adj.get(ds.city);
                for (FNode r : rout) {
                    if (currentDate < r.flightDate) {
                        int noOfDaysStay = r.flightDate-currentDate;

                        int chargesOfStay = noOfDaysStay*hotelCharges.get(ds.city);
                        System.out.println("Charges of stay "+chargesOfStay);
                        System.out.println("Charges of flight "+r.ticketPrice);
                        System.out.println("Charges until now "+presentExp);
                        int currExp = r.ticketPrice+presentExp+chargesOfStay;
                        System.out.println(currExp+" "+r.city);
                        r.visited = true;
                        r.currentExp = currExp;
                        pq.add(r);
                    }
                }
            }else{
                System.out.println("Not taking this path");
            }

        }
        System.out.println(pq);
        System.out.println(presentExp);


    }
}
