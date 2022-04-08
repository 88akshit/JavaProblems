package com.company;

import java.util.*;

public class Basics {
    Basics(){

        calc c = (i,j)->{ return i+j;};
        System.out.println(c.operation(1,2));

        System.out.println(doSomething(1,2,(a,b)->{ return a+b;}));

        List<String>  name = new ArrayList<>();
        name.add("Roy");
        name.add("Ayush");
        name.add("Sam");
        name.forEach(i-> System.out.println(i));

        List<String> result = name.stream().filter((a)-> !a.equals("Akshit")).toList();
        System.out.println(result);

        String carBrandName = "Kia";
        char [] carArray = carBrandName.toCharArray();
        String cB = new String(carArray);
        char [] alph = new char[26];
        alph[4] = 'A';
        System.out.println(Arrays.toString(alph));

        String [] classes = new String[10];
        classes[0] = "a";
        int test = '1'-'0';
        System.out.println(test);

        int [] nums = {100,2,3};

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        Collections.sort(name);
        System.out.println(name);



        Map<String,Integer> states = new HashMap<>();
        states.put("UP",1);
        states.put("Punjab",2);

        for(Map.Entry<String,Integer> k : states.entrySet()){
            System.out.println(k.getKey());
        }
        states.remove("UP");
        System.out.println(states);
        String text = "random";
        System.out.println(text.substring(1,5));




    }
    public  static  int doSomething(int a , int b , calc c ){
        return  c.operation(a,b);
    }
}

