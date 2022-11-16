package com.example.vacationdestinationappna;

public class VacationDestination {
    private String placeName;
    private int imageID;
    private Boolean faveStatus;

    public VacationDestination(String n, int id, Boolean f){
        placeName = n;
        imageID = id;
        faveStatus = f;
    }

    public String getName() {
        return placeName;
    }

    /*public void setName(String placeName) {
        this.placeName = placeName;
    }*/

    public int getImageId() {
        return imageID;
    }
    public Boolean getFT() {
        return faveStatus;
    }
    public Boolean setFT() {
        if (faveStatus){
            this.faveStatus = false;
        }else{
        this.faveStatus = true;}
        return faveStatus;
    }

    public int getHeart() {
        if (getFT()){
            return (R.drawable.ic_fave);
        }
        return (R.drawable.ic_unfave);
    }

    /*public void setID(int imageID) {
        this.imageID = imageID;
    }*/
}
