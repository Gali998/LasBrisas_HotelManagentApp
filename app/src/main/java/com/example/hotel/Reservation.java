package com.example.hotel;

public class Reservation {

   private String date1;
    private String date2;
    private String rooms;
    private String adults;
    private String kids;
    private String name;
    private String email;
    private String contactNo;

  public Reservation(){

   }
    public Reservation(String date1, String date2, String rooms, String adults, String kids, String name, String email, String contactNo) {
        this.date1 = date1;
        this.date2 = date2;
        this.rooms = rooms;
        this.adults = adults;
        this.kids = kids;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
    }
    public Reservation(String rooms, String adults, String kids, String name, String email, String contactNo) {
        this.rooms = rooms;
        this.adults = adults;
        this.kids = kids;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
    }
    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getAdults() {
        return adults;
    }

    public void setAdults(String adults) {
        this.adults = adults;
    }

    public String getKids() {
        return kids;
    }

    public void setKids(String kids) {
        this.kids = kids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}