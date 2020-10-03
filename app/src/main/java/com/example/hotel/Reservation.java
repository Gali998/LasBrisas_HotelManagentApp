package com.example.hotel;

public class Reservation {

   private String checkin;
    private String checkout;
    private String rname;
    private String rooms;
    private String adults;
    private String kids;
    private String name;
    private String email;
    private String contactNo;

  public Reservation(){

   }
    public Reservation(String checkin, String checkout,String rname, String rooms, String adults, String kids, String name, String email, String contactNo) {
        this.checkin =checkin;
        this.checkin = checkout;
        this.rname=rname;
        this.rooms = rooms;
        this.adults = adults;
        this.kids = kids;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
    }
    public Reservation(String rname,String rooms, String adults, String kids, String name, String email, String contactNo) {
        this.rname=rname;
        this.rooms = rooms;
        this.adults = adults;
        this.kids = kids;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
    }
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname =rname;
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