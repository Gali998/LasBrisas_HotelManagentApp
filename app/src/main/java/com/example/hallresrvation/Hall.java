package com.example.hallresrvation;

import com.google.firebase.database.Exclude;

public class Hall {
    private String HallName;
    private String TypeEvent;
    private  String NoOfGuest;
    private String time;
    private String GuestName;
    private String contactNo;
    private String email;
    private String address;
    private String mKey;

    public Hall() {
    }

    public Hall(String hallName, String typeEvent, String noOfGuest, String time, String guestName, String contactNo, String email, String address) {
        HallName = hallName;
        TypeEvent = typeEvent;
        NoOfGuest = noOfGuest;
        this.time = time;
        GuestName = guestName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
    }

    public Hall(String hallName, String noOfGuest, String time, String guestName, String contactNo, String email, String address) {
        HallName = hallName;
        NoOfGuest = noOfGuest;
        this.time = time;
        GuestName = guestName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
    }


    public String getGuestName() {
        return GuestName;
    }

    public void setGuestName(String guestName) {
        GuestName = guestName;
    }

    public String getHallName() {
        return HallName;
    }

    public void setHallName(String hallName) {
        HallName = hallName;
    }

    public String getTypeEvent() {
        return TypeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        TypeEvent = typeEvent;
    }

    public String getNoOfGuest() {
        return NoOfGuest;
    }

    public void setNoOfGuest(String noOfGuest) {
        NoOfGuest = noOfGuest;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Exclude
    public String getKey(){
        return mKey;
    }

    @Exclude
    public void setKey(String key){
      mKey = key;
    }

}
