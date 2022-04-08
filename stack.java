package com.company;

import java.util.Stack;

public class StackProblem {
    public String stackPrb(String str){
        char [] charArray = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        String res = "";
        for(char a:charArray){

            if(a =='#'){
                stack.pop();
            }else{
                stack.push(a);
            }
        }
        for(Character c : stack){
            res+=c;
        }
        System.out.println(res);
        return res;
    }
}
