import java.util.*;
public class ProductQ implements ItemInterface{

	class TimeComp implements Comparator<Item>{

		@Override
		public int compare(Item o1, Item o2) {
			return o2.time.compareTo(o1.time);
		}
		
	}
	Calendar cal;
	Map<Integer, Item> map = new HashMap<>();
	public void addItem(Item i){
		map.put(i.id, i);
    System.out.println("Item Added");
	}
	@Override
	public List<Item> getTopItems(Date timeGiven) {
		// TODO Auto-generated method stub
		List<Item> itemList =  new ArrayList<>();
		cal = Calendar.getInstance();
		cal.setTime(timeGiven);
		cal.add(Calendar.HOUR, -1);
		Date oneHourBack = cal.getTime();
		
    // map.entrySet().forEach(entry -> {
    //   System.out.println(entry.getKey() + " " + entry.getValue());
    // });
    
    System.out.println(map);

		PriorityQueue<Item> pq = new PriorityQueue<>(new TimeComp());
		for(Map.Entry<Integer, Item> entry: map.entrySet()){
			pq.add(entry.getValue());
		}
    while(!pq.isEmpty()){
          Item t = pq.poll();
          System.out.println(t.id);
    }
    
    
		int n = 0;
		while(!pq.isEmpty() && n < 20){
			Item t = pq.poll();
			if(t.time.compareTo(timeGiven) == -1 && (t.time.compareTo(oneHourBack) == 1 || t.time.compareTo(oneHourBack) == 0)){
				itemList.add(t);
				n++;
         System.out.println("here");
			}
		}
		return itemList;
	}

}