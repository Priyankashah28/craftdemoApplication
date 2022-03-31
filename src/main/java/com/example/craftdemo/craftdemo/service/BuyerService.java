package com.example.craftdemo.craftdemo.service;

import com.example.craftdemo.craftdemo.constants.Constants;
import com.example.craftdemo.craftdemo.controller.BuyerAndSuppilerCategorizedController;
import com.example.craftdemo.craftdemo.entity.BillReportdto;
import com.example.craftdemo.craftdemo.entity.Businessdto;
import com.example.craftdemo.craftdemo.entity.GSTdto;
import com.example.craftdemo.craftdemo.mapper.BusinessMapper;
import com.example.craftdemo.craftdemo.util.CsvUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Component
public class BuyerService implements BusinessService{

    private static final Logger logger= LoggerFactory.getLogger(BuyerService.class);


    @Autowired
     private CsvUtility csvUtility;

     @Value("${spring.application.file.buyerpath}")
     private String fileName;

     @Autowired
     private BusinessMapper businessMapper;

    @Override
    public List<Businessdto> generateBusinessReport() throws IOException, ParseException {
        logger.info("Reading data from {} file.................",fileName);
       List<String[]>  buyerInformationList = csvUtility.readDataFromCSVFile(fileName);
       List<Businessdto> buyerDetails = businessMapper.setBusinessMapper(buyerInformationList, Constants.BUYER);
       return buyerDetails;
    }

}
