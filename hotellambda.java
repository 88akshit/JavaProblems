package com.company;

import java.util.*;
import java.util.stream.Collectors;
class HotelViewEvent{
    public int hotelId;
    public String areaCode;
    private String user;
    private Boolean isUserLoggerIn;
    HotelViewEvent(int hotelId ,String areaCode,  String user, Boolean isUserLoggerIn){
            this.hotelId = hotelId;
            this.areaCode = areaCode;
            this.user = user;
            this.isUserLoggerIn = isUserLoggerIn;
    }

    public boolean getLoggedIn() {
        return isUserLoggerIn;
    }
}
public class HotelLambda {
    private static List<OutputMessage> getSolution(List<HotelViewEvent> events){

        Map<String,List<OutputMessage> > result = events.stream()
                .filter(event -> event.getLoggedIn())
                .map(msg ->{
                    return new OutputMessage(msg.areaCode, msg.hotelId, 1);
                })
                .collect(Collectors.groupingBy(oMsg -> oMsg.hotel_area_code+"_"+oMsg.hotel_id));
        System.out.println(result);


        return events.stream()
                .filter(event -> event.getLoggedIn())
                .map(msg ->{
                    return new OutputMessage(msg.areaCode, msg.hotelId, 1);
                })
                .collect(Collectors.groupingBy(oMsg -> oMsg.hotel_area_code+"_"+oMsg.hotel_id))
                .entrySet()
                .stream()
                .map(entry -> {
                    String key = entry.getKey();
                    String[] keyData = key.split("_");
                    String hotelID = keyData[1];
                    String areaCode = keyData[0];
                    List<OutputMessage> hData = entry.getValue();
                    OutputMessage hDataMsg = hData.stream().reduce(new OutputMessage(areaCode, Long.parseLong(hotelID), 0), (a,b) -> {
                        a.views = a.views + b.views;
                        return a;
                    });
                    return hDataMsg;
                })
                .collect(Collectors.groupingBy(oMsg -> oMsg.hotel_area_code))
                .values()
                .stream()
                .map(areaData -> {
                    areaData.sort((h1,h2)->(h2.views - h1.views));
                    return areaData.get(0);
                })
                .collect(Collectors.toList());
    }

    public void solution() {
        List<HotelViewEvent> list = new ArrayList<>();

        list.add(new HotelViewEvent(23, "IT", "user1", true));
        list.add(new HotelViewEvent(23, "IT", "user2", true));
        list.add(new HotelViewEvent(23, "IT", "user1", true));
        list.add(new HotelViewEvent(24, "IT", "user1", true));
        list.add(new HotelViewEvent(24, "IT", "user2", true));
        list.add(new HotelViewEvent(24, "IT", "user1", true));
        list.add(new HotelViewEvent(24, "IT", "user1", true));
        list.add(new HotelViewEvent(35, "FR", "user1", true));

        System.out.println(getSolution(list));
    }
}
