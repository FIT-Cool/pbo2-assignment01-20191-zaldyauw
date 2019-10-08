package com.zaldy.entity;

import java.util.Date;

public class menu {

    private int id;
    private String name;
    private Double price;
    private String description;
    private boolean recomended;
    private String photo;
    private Date created;
    private category category;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getRecomended() {
        return recomended;
    }

    public void setRecomended(Boolean recomended) {
        this.recomended = recomended;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public com.zaldy.entity.category getCategory() {
        return category;
    }

    public void setCategory(com.zaldy.entity.category category) {
        this.category = category;
    }

    public void setCategory(menu value) {
    }
}
