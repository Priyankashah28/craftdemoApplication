package com.example.craftdemo.craftdemo.entity;

import com.example.craftdemo.craftdemo.enums.Category;

import javax.sql.rowset.CachedRowSet;


public class Outputdto {
    private Buyerdto buyerdto;
    private Suppilerdto suppilerdto;
    private Category category;


    public Buyerdto getBuyerdto() {
        return buyerdto;
    }

    public void setBuyerdto(Buyerdto buyerdto) {
        this.buyerdto = buyerdto;
    }

    public Suppilerdto getSuppilerdto() {
        return suppilerdto;
    }

    public void setSuppilerdto(Suppilerdto suppilerdto) {
        this.suppilerdto = suppilerdto;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Outputdto{" +
                "buyerdto=" + buyerdto +
                ", suppilerdto=" + suppilerdto +
                ", category=" + category +
                '}';
    }
}
