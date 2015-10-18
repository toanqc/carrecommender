/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.bigdata.car.recommender.model;

/**
 *
 * @author Hiep
 */
public class Car extends BaseModel{
    
    private double cid;
    private Make make;
    private Model model;
    private int year;
    private String mpg;
    private String tranmision;
    private byte doors;
    private String submodel;
    private int msrp;
    private String rating;
    private String consumer_rating;
    private String photo;

    public double getCid() {
        return cid;
    }

    public void setCid(double cid) {
        this.cid = cid;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMpg() {
        return mpg;
    }

    public void setMpg(String mpg) {
        this.mpg = mpg;
    }

    public String getTranmision() {
        return tranmision;
    }

    public void setTranmision(String tranmision) {
        this.tranmision = tranmision;
    }

    public byte getDoors() {
        return doors;
    }

    public void setDoors(byte doors) {
        this.doors = doors;
    }

    public String getSubmodel() {
        return submodel;
    }

    public void setSubmodel(String submodel) {
        this.submodel = submodel;
    }

    public int getMsrp() {
        return msrp;
    }

    public void setMsrp(int msrp) {
        this.msrp = msrp;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getConsumer_rating() {
        return consumer_rating;
    }

    public void setConsumer_rating(String consumer_rating) {
        this.consumer_rating = consumer_rating;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
