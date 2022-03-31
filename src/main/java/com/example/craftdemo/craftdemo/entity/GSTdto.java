package com.example.craftdemo.craftdemo.entity;

import java.util.Objects;

public class GSTdto {
    private double rate;
    private double igst;
    private double cgst;
    private double sgst;

    public GSTdto() {
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getIgst() {
        return igst;
    }

    public void setIgst(double igst) {
        this.igst = igst;
    }

    public double getCgst() {
        return cgst;
    }

    public void setCgst(double cgst) {
        this.cgst = cgst;
    }

    public double getSgst() {
        return sgst;
    }

    public void setSgst(double sgst) {
        this.sgst = sgst;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GSTdto GSTdto = (GSTdto) o;
        return rate == GSTdto.rate && Double.compare(GSTdto.igst, igst) == 0 && Double.compare(GSTdto.cgst, cgst) == 0 && Double.compare(GSTdto.sgst, sgst) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate, igst, cgst, sgst);
    }

    @Override
    public String toString() {
        return "GST{" +
                "rate=" + rate +
                ", igst=" + igst +
                ", cgst=" + cgst +
                ", sgst=" + sgst +
                '}';
    }
}
