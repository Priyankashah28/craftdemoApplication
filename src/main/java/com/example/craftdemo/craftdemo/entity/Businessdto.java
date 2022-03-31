package com.example.craftdemo.craftdemo.entity;

import com.example.craftdemo.craftdemo.enums.Flag;

import java.util.Objects;

public class Businessdto {
     private String gstNo;
     private BillReportdto billReportdto;
     private Flag flag=Flag.NONE;
    public BillReportdto getBillReport() {
        return billReportdto;
    }

    public void setBillReport(BillReportdto billReportdto) {
        this.billReportdto = billReportdto;
    }


    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public BillReportdto getBillReportdto() {
        return billReportdto;
    }

    public void setBillReportdto(BillReportdto billReportdto) {
        this.billReportdto = billReportdto;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Businessdto businessdto = (Businessdto) o;
        return Objects.equals(gstNo, businessdto.gstNo) && Objects.equals(billReportdto, businessdto.billReportdto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gstNo, billReportdto);
    }

    @Override
    public String toString() {
        return "Business{" +
                "gstNo='" + gstNo + '\'' +
                ", billReport=" + billReportdto +
                '}';
    }
}
