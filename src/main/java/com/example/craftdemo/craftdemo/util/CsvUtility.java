package com.example.craftdemo.craftdemo.util;

import com.example.craftdemo.craftdemo.service.BuyerService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class CsvUtility {

    private static final Logger logger= LoggerFactory.getLogger(CsvUtility.class);


    public List<String[]> readDataFromCSVFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader filereader = new FileReader(file);
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(1)
                .build();
        List<String[]> allDataList = csvReader.readAll();
        return allDataList;
    }

    public void writeDataInCSVFile(String fileName, List<String[]> outputData) throws IOException {
        File file = new File(fileName);
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);
        writer.writeAll(outputData);
        writer.close();
    }
}
