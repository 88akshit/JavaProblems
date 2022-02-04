import java.util.*;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    
    // List<Item> result = new ArrayList<>();
    // Date d1 = new Date(2323223232L);
    // Date d2 = new Date(2373233232L);
    // Date d3 = new Date(4323293232L);
    // Item i1 = new Item(1,d1);
    // Item i2 = new Item(2,d2);
    // Item i3 = new Item(3,d3);
    // ProductQ pq = new ProductQ();
    // pq.addItem(i3);
    // pq.addItem(i1);
    // pq.addItem(i2);
    // Date d = new Date(4323293232L);
    // result = pq.getTopItems(d);
    // System.out.println(result);

    //Find the Subarray with the given sum
    int [] nums = {10, 15 , -5 ,15, -10, 5};
    int sum = 5;
    int start = 0;
    int end = -1;
    int currentSum = 0;
    Map<Integer,Integer> track = new HashMap<>();

    for (int i =0; i< nums.length;i++){
        currentSum+= nums[i];
        if (currentSum-sum==0){
            start = 0;
            end = i;
            break;
        }
        else if (track.containsKey(currentSum-sum)){
            start =track.get(currentSum-sum)+1;
            end = i;
            break;
        }
        else{
          track.put(currentSum, i);
        }
    }
    if (end==-1){
        System.out.println("Not found");
    }else{
       System.out.println("Start "+start+" End "+end);
    }
  }
}