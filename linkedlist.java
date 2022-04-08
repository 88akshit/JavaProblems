package com.company;

class LinkListNode{
    Integer value;
    LinkListNode next;
    LinkListNode(Integer value , LinkListNode next){
        this.value = value;
        this.next = next;
    }

}
public class LinkListProb {
    void solution(){
        LinkListNode node = new LinkListNode(23,null);
        LinkListNode node2 = new LinkListNode(43,null);
        node.next = node2;
        LinkListNode traversedNode = node;
        while(traversedNode!=null){
            System.out.println(traversedNode.value);
            traversedNode = traversedNode.next;
        }

    }

}
