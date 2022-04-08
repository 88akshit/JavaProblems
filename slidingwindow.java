package com.company;

import java.util.*;

public class SlidingWindow {

    public  void longestSubStringWithoutRepeatingCharacters(String str){

        //"abbacbcdbad"
        System.out.println(str.charAt(5) - 'a');
        int ans = 0;
        int i = -1;
        int j = -1;

        HashMap<Character,Integer> charMapping = new HashMap<>();
        while (true) {

            boolean f1 = false;
            boolean f2 = false;
            //acquire
            while(i<str.length()-1){
                f1 = true;
                i++;
                char ch = str.charAt(i);
                charMapping.put(ch, charMapping.getOrDefault(ch,0)+1);
                if(charMapping.get(ch)==2){
                    break;
                }else{
                    int len = i-j;
                    if(len>ans){
                        ans = len;

                    }
                }
            }

            // release

            while(j<i){
                f2 = true;
                j++;
                char ch = str.charAt(j);
                charMapping.put(ch,charMapping.get(ch)-1);
                if(charMapping.get(ch)==1){
                    break;
                }
            }

            if(f1==false && f2==false){
                    break;
            }
        }
        System.out.println("Answer "+ans);
    }

    public  void anotherMethod(String str){
        // "PWWKEW"
        //HashSet<Character> charSet = new HashSet<>();
        Queue<Character> charSet = new LinkedList<>();

        int l =0;
        int res = 0;
        for (int r = 0 ;r<str.length();r++){

            while(charSet.contains(str.charAt(r))){
                charSet.remove();
                l++;
            }
            charSet.add(str.charAt(r));
            res = Math.max(res,r-l+1);
            System.out.println(charSet);
        }

        System.out.println(res);
    }
}
