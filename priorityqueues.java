package com.company;

import java.util.*;

class Student implements Comparator<Student> {
    public String name;
    public double cgpa;

    public Student(String name, double cgpa) {

        this.name = name;
        this.cgpa = cgpa;
    }
    public Student(){

    }

    public String getName() {
        return name;
    }

    public int compare(Student s1, Student s2) {
        // If s1>s2 Min Heap else s1<s2 Max Heap
        if (s1.cgpa < s2.cgpa)
            return 1;
        else if (s1.cgpa > s2.cgpa)
            return -1;
        return 0;
    }
}



public class PriorityQueuePrblems {

    PriorityQueue<Integer> pQueue;
    PriorityQueuePrblems(int size){
         //this.pQueue= new PriorityQueue<>(size, Collections.reverseOrder());
         this.pQueue= new PriorityQueue<>(size);
    }

    public  void addElements(int ele){
        pQueue.add(ele);
    }

    public  void printQ(){
        System.out.println(pQueue);
    }

    public void priorityQueueComp() {
        PriorityQueue<Student> pq = new PriorityQueue<Student>(5, new Student());
        Student student1 = new Student("Nandini", 3.2);
        pq.add(student1);
        Student student2 = new Student("Anmol", 3.6);
        pq.add(student2);
        Student student3 = new Student("Palak", 4.0);
        pq.add(student3);
        while (!pq.isEmpty()) {
            Student st = pq.poll();
            System.out.println(st.getName()+" "+st.cgpa);
        }
    }

    public long sum(List<Integer> data, int k) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Comparator.reverseOrder());
        pQueue.addAll(data);
        System.out.println(pQueue);
        while (k-- > 0) {
            int p = pQueue.poll();
            System.out.println("Poll "+p);
            pQueue.add((int) Math.ceil(p / 2d));
        }
        return pQueue.stream().mapToInt(a -> a).sum();
    }

    public static List<Integer> awardTopKHotels(String positiveKeywords, String negativeKeywords,
                                                List<Integer> hotelIds,
                                                List<String> reviews, int k) {
        Set<String> positiveWords = new HashSet<>();
        Set<String> negativeWords = new HashSet<>();

        for (String word: positiveKeywords.split(" ")) {
            positiveWords.add(word.toLowerCase());
        }

        for (String word: negativeKeywords.split(" ")) {
            negativeWords.add(word.toLowerCase());
        }

        Map<Integer, Integer> hotelScore = new HashMap<>();
        for (int i = 0; i < reviews.size(); i++) {
            int hotel = hotelIds.get(i);
            int score = hotelScore.getOrDefault(hotel, 0);
            List<String> review = Arrays.asList(reviews.get(i).split(" "));

            int pos=0, neg=0;
            for (String word: reviews.get(i).split(" ")) {
                if (word.charAt(word.length()-1) == '.' || word.charAt(word.length()-1) == ',') {
                    word = word.substring(0, word.length() - 1);
                }
                if (positiveWords.contains(word.toLowerCase())) {
                    pos++;
                }
                if (negativeWords.contains(word.toLowerCase())) {
                    neg++;
                }
            }
            hotelScore.put(hotel, score + 3*pos - neg);
        }

        // Sorting based on value first , if value equal then key smallest key first
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );

        for(Map.Entry<Integer, Integer> entry: hotelScore.entrySet())
        {
            pq.add(entry);

            if(pq.size()>k)
                pq.poll();
        }

        List<Integer> result = new ArrayList<>();
        System.out.println(pq);
        while(!pq.isEmpty())
            result.add(0, pq.poll().getKey());

        return result;
    }




}

