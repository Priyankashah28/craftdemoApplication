package com.example.craftdemo.craftdemo.mapper;

import com.example.craftdemo.craftdemo.constants.Constants;
import com.example.craftdemo.craftdemo.entity.*;
import com.example.craftdemo.craftdemo.service.BuyerService;
import com.example.craftdemo.craftdemo.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessMapper {

    private static final Logger logger= LoggerFactory.getLogger(BusinessMapper.class);

    @Autowired
    private ValidationUtil validationUtil;

    public List<Businessdto> setBusinessMapper(List<String[]> businessdetails,String type) throws ParseException {
        logger.info("Set the {} object to the business Mapper.......",type);
        List<Businessdto> businessInfoList = new ArrayList<>();
        for(String[] businessdetail: businessdetails){
            String gstNo=validationUtil.validateString(businessdetail[0]);
            String date= validationUtil.validateString(businessdetail[1]);
            String billNo=validationUtil.validateString(businessdetail[2]);
            String gstRate=validationUtil.validateString(businessdetail[3]);
            String billValue=validationUtil.validateString(businessdetail[4]);
            String igst=validationUtil.validateString(businessdetail[5]);
            String cgst=validationUtil.validateString(businessdetail[6]);
            String sgst=validationUtil.validateString(businessdetail[7]);
            String totalValue=validationUtil.validateString(businessdetail[8]);
            GSTdto GSTdto = new GSTdto();
            GSTdto.setRate(Integer.parseInt(gstRate));
            GSTdto.setIgst(Double.parseDouble(validationUtil.removeComma(igst)));
            GSTdto.setSgst(Double.parseDouble(validationUtil.removeComma(sgst)));
            GSTdto.setCgst(Double.parseDouble(validationUtil.removeComma(cgst)));
            BillReportdto billReportdto = new BillReportdto();
            billReportdto.setBillNo(billNo);
            billReportdto.setBillDate(validationUtil.validateDate(date));
            billReportdto.setGst(GSTdto);
            billReportdto.setBillValue(Double.parseDouble(validationUtil.removeComma(billValue)));
            billReportdto.setTotalValue(Double.parseDouble(validationUtil.removeComma(totalValue)));
            Businessdto businessdto = type.equalsIgnoreCase(Constants.BUYER) ? new Buyerdto() : new Suppilerdto();
            businessdto.setGstNo(gstNo);
            businessdto.setBillReport(billReportdto);
            businessInfoList.add(businessdto);

        }
        return businessInfoList;
    }
}
