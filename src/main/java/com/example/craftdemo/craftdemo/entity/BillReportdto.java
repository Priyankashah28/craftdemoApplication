package com.example.craftdemo.craftdemo.entity;

import java.util.Date;
import java.util.Objects;

public class BillReportdto {
    private String billNo;
    private Date billDate;
    private double billValue;
    private double totalValue;
    private GSTdto GSTdto;

    public BillReportdto() {
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public double getBillValue() {
        return billValue;
    }

    public void setBillValue(double billValue) {
        this.billValue = billValue;
    }

    public GSTdto getGst() {
        return GSTdto;
    }

    public void setGst(GSTdto GSTdto) {
        this.GSTdto = GSTdto;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillReportdto that = (BillReportdto) o;
        return Double.compare(that.billValue, billValue) == 0 && Double.compare(that.totalValue, totalValue) == 0 && Objects.equals(billNo, that.billNo) && Objects.equals(billDate, that.billDate)  && Objects.equals(GSTdto, that.GSTdto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, billDate, billValue, totalValue, GSTdto);
    }

    @Override
    public String toString() {
        return "BillReport{" +
                "billNo='" + billNo + '\'' +
                ", billDate=" + billDate +
                ", billValue=" + billValue +
                ", totalValue=" + totalValue +
                ", gst=" + GSTdto +
                '}';
    }
}
