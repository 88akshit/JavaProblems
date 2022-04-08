import java.util.*;


class Main {

  public static void subArraySum(){
    //Find the Subarray with the given sum
    int [] nums = {10, 15 , -5 ,15, -10, 5};
    int sum = 5;
    int start = 0;
    int end = -1;
    int currentSum = 0;
    Map<Integer,Integer> track = new HashMap<>();
    // CurrentSum = sum+(currentsum-sum)
    for (int i =0; i< nums.length;i++){
        currentSum+= nums[i];
        if (currentSum==sum){
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
  
  public static void palindrome(){
    String word = "racecar";

    int start = 0;
    int end = word.length()-1;

    while(start<end){
      if(word.charAt(start)==word.charAt(end)){
          start+=1;
          end-=1;
      }
      else{
        System.out.println("Not palindrome");
        break;
      }

    }
    System.out.println("Palindrome");
  }

  public static void removeDuplicates(){
        int [] nums  = {1,1,1,2,2,3};
        final int k = 2;

    		//check if it is an empty array
    		if(nums.length == 0) System.out.println(0);

    		//start pointer of new array
    		int m = 1;

    		// count the time of duplicate numbers occurence
    		int count = 1;

    		for(int i = 1; i < nums.length; ++i) {
                // compare current and previous element if the equal  and count of them is less than k shift and                
                // increment m otherwise we dont increment m
    			if(nums[i] == nums[i - 1]) {
    				if(count < k) {
              System.out.println("shifting "+m+" and "+i);
    					nums[m++] = nums[i];
    				}
    				count++;
    			} else {
    				count = 1;
            System.out.println("shifting not equal "+m+" and "+i);
    				nums[m++] = nums[i];
    			}
          System.out.println(Arrays.toString(nums));
    		}
    		System.out.println(m);
  }
  
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

    Main.subArraySum();

    // Main.palindrome();
    //Main.removeDuplicates();

    // Tree t2 = new Tree(7);

    // t2.insertElement(4);
    // t2.insertElement(9);
    // t2.insertElement(1);
    // t2.insertElement(6);
    // t2.insertElement(8);
    // t2.insertElement(10);
    // t2.print();
    // System.out.println(t2.find(10));

    // List<Integer> random = new ArrayList<>();
    // HashSet<Integer> random = new HashSet<>();
    // random.add(3);
    // random.add(2);
    // if(random.contains(3)){
    //   System.out.println("Found");
    // }else{
    //    System.out.println("not Found");
    // }

    Hotel h = new Hotel();
  }
}