package com.example.craftdemo.craftdemo.service;

import com.example.craftdemo.craftdemo.constants.Constants;
import com.example.craftdemo.craftdemo.entity.*;
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
public class SuppilerService implements BusinessService{


    @Autowired
    private CsvUtility csvUtility;

    @Autowired
    BusinessMapper businessMapper;

    @Value("${spring.application.file.suppilerpath}")
    String fileName;

    private static final Logger logger= LoggerFactory.getLogger(SuppilerService.class);


    @Override
    public List<Businessdto> generateBusinessReport() throws IOException, ParseException {
        logger.info("Reading data from {} file.................",fileName);
        List<String[]>  suppilerInformationList = csvUtility.readDataFromCSVFile(fileName);
        List<Businessdto> suppilerbillInfoList = businessMapper.setBusinessMapper(suppilerInformationList, Constants.SUPPILER);
        return suppilerbillInfoList;
    }
}
