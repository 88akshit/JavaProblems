package com.company;

import java.util.ArrayList;
import java.util.List;

class Node{
    public int value;
    public Node leftChild;
    public Node rightChild;

    public Node(int value){
        this.value = value;
    }
}

class TreeProblem{

    public Node root;

    public TreeProblem(int val){
        this.root = new Node(val);
    }

    public void insert(Node root,int val){
        if(val>root.value){
            if(root.rightChild == null){
                root.rightChild = new Node(val);
                System.out.println("Inserted Right "+val);
                return;
            }
            else{
                insert(root.rightChild,val);
            }
        }else{
            if(root.leftChild == null){
                root.leftChild = new Node(val);
                System.out.println("Inserted Left "+val);
                return;
            }
            else{
                insert(root.leftChild,val);
            }
        }
    }


    public void insertElement(int val){
        insert(root,val);
    }

    public void printTree(Node root){

        if (root == null){
            return;
        }
        // DFS Inorder Traversal
        printTree(root.leftChild);
        System.out.println(root.value);
        printTree(root.rightChild);

    }

    public void print(){
        printTree(root);
    }

    public Boolean findElement(Node root , int val){
        if (root.value ==val){
            return true;
        }
        else if (val<root.value){
            if(root.leftChild!=null){
                System.out.println("inside left");
                return findElement(root.leftChild,val);
            }else{
                return false;
            }
        }
        else if (val>root.value){
            if(root.rightChild!=null){
                System.out.println("inside right");
                return findElement(root.rightChild,val);
            }else{
                return false;
            }
        }
        return false;
    }
    public Boolean find(int val){
        return findElement(root,val);
    }

    public  Boolean getPath(Node root , List<Integer> path , int val){
        if(root==null){
            return false;
        }
        path.add(root.value);
        if(root.value == val){
            return true;
        }
        if(getPath(root.leftChild,path,val) || getPath(root.rightChild,path,val)){
            return true;
        }
        path.remove(path.size() -1);
        return false;
    }
    public void findPath(int val){
        List<Integer> path = new ArrayList<>();
        getPath(root,path, val);
        System.out.println(path);
    }
}