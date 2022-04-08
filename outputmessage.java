package com.company;

public class OutputMessage {
    public String hotel_area_code;
    public long hotel_id;
    public int views;
    OutputMessage(String hotel_area_code,long hotel_id, int views ){
        this.hotel_area_code = hotel_area_code;
        this.hotel_id = hotel_id;
        this.views = views;

    }

    @Override
    public String toString() {
        return "OutputMessage{" +
                "hotel_area_code='" + hotel_area_code + '\'' +
                ", hotel_id=" + hotel_id +
                ", views=" + views +
                '}';
    }
}
