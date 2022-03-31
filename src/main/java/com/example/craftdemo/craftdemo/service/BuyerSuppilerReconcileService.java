package com.example.craftdemo.craftdemo.service;

import com.example.craftdemo.craftdemo.constants.Constants;
import com.example.craftdemo.craftdemo.entity.Businessdto;
import com.example.craftdemo.craftdemo.entity.Buyerdto;
import com.example.craftdemo.craftdemo.entity.Outputdto;
import com.example.craftdemo.craftdemo.entity.Suppilerdto;
import com.example.craftdemo.craftdemo.enums.Category;
import com.example.craftdemo.craftdemo.enums.Flag;
import com.example.craftdemo.craftdemo.mapper.OutputMapper;
import com.example.craftdemo.craftdemo.util.CsvUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static java.util.Objects.nonNull;

@Component
public class BuyerSuppilerReconcileService {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SuppilerService suppilerService;

    @Autowired
    CsvUtility csvUtility;

    @Autowired
    OutputMapper outputMapper;

    @Value("${spring.application.file.outputpath}")
    private String outputFilePath;

    @Autowired
    private BuyerSuppilerComparisonService buyerSuppilerComparisonService;

    private static final Logger logger= LoggerFactory.getLogger(BuyerSuppilerReconcileService.class);


    public void categorizationBetweenBuyerandSuppiler() throws IOException, ParseException {
        logger.info("Categorization for Buyer and Suppiler Started.............");
        List<Businessdto> buyerInfo = buyerService.generateBusinessReport();
        List<Businessdto> supplierInfo = suppilerService.generateBusinessReport();
        List<Outputdto> outputdetailList = new ArrayList<>();
        HashMap<Buyerdto, TreeMap<Integer, Suppilerdto>> bestPartialMap = new HashMap<>();
        categorizedAllDataForBuyerAndSuppiler(buyerInfo, supplierInfo, outputdetailList, bestPartialMap);
        setpartialMatchingList(bestPartialMap,outputdetailList);
        generateCsvForBuyerAndSuppiler(outputdetailList);
    }

    public void generateCsvForBuyerAndSuppiler(List<Outputdto> outputdtos) throws IOException {
        List<String[]> allBuyerAndSuppilerDataList =outputMapper.setOutputMapper(outputdtos);
        csvUtility.writeDataInCSVFile(outputFilePath,allBuyerAndSuppilerDataList);
    }
    private void categorizedAllDataForBuyerAndSuppiler(List<Businessdto> buyerInfo, List<Businessdto> suppilerInfo, List<Outputdto> outputdtos, HashMap<Buyerdto, TreeMap<Integer, Suppilerdto>> bestPartialMatches) {
        logger.info("Comparing Buyer and suppiler file..................");
        Outputdto outputdto;
        for(Businessdto buyerdetail: buyerInfo){
            outputdto=new Outputdto();
            for(Businessdto suppilerDetail: suppilerInfo){
                if(buyerSuppilerComparisonService.doExactMatch(buyerdetail,suppilerDetail)){
                    outputdto.setBuyerdto((Buyerdto) buyerdetail);
                    outputdto.setSuppilerdto((Suppilerdto) suppilerDetail);
                    outputdto.setCategory(Category.Exact);
                    outputdtos.add(outputdto);
                    buyerdetail.setFlag(Flag.MATCHED);
                    suppilerDetail.setFlag(Flag.MATCHED);
                    break;
                }else{
                    if(!suppilerDetail.getFlag().equals(Flag.MATCHED)) {
                        this.partialMatching(buyerdetail, suppilerDetail, bestPartialMatches);
                        this.setListOfOnlyuyerOrSuppilerCategory((Suppilerdto) suppilerDetail, outputdtos,Constants.SUPPILER);
                    }
                }
            }
            this.setListOfOnlyuyerOrSuppilerCategory((Buyerdto) buyerdetail, outputdtos,Constants.BUYER);
        }
    }

    public void setListOfOnlyuyerOrSuppilerCategory(Businessdto businessdto, List<Outputdto> outputdtos,String type){
        logger.info("sets the only Buyer or Suppiler Catgeory.................");
        Outputdto outputdto = null;
        if((!businessdto.getFlag().equals(Flag.PARTIALMATCHED)) && businessdto.getFlag().equals(Flag.NONE)){
            if(type.equalsIgnoreCase(Constants.BUYER)){
                outputdto = new Outputdto();
                outputdto.setBuyerdto((Buyerdto) businessdto);
                outputdto.setSuppilerdto(null);
                outputdto.setCategory(Category.Only_In_Buyer);
            }else if(type.equalsIgnoreCase(Constants.SUPPILER)){
                outputdto = new Outputdto();
                outputdto.setBuyerdto(null);
                outputdto.setSuppilerdto((Suppilerdto) businessdto);
                outputdto.setCategory(Category.Only_In_Suppiler);
            }
            businessdto.setFlag(Flag.NONMATCHED);
            outputdtos.add(outputdto);
        }
    }

    public void setpartialMatchingList(HashMap<Buyerdto, TreeMap<Integer,Suppilerdto>> bestPartialMatches, List<Outputdto> outputdtos){
        Outputdto outputdto=null;
        for(Map.Entry<Buyerdto, TreeMap<Integer, Suppilerdto>> partialMatch:bestPartialMatches.entrySet()){
            outputdto=new Outputdto();
            outputdto.setBuyerdto(partialMatch.getKey());
            outputdto.setSuppilerdto(partialMatch.getValue().lastEntry().getValue());
            outputdto.setCategory(Category.Partial);
            outputdtos.add(outputdto);
        }
    }

    public void partialMatching(Businessdto buyerdto,Businessdto suppilerdto,HashMap<Buyerdto,TreeMap<Integer,Suppilerdto>> bestPartialMap){
        logger.info("Partial Matching Started........................");
        int weight = buyerSuppilerComparisonService.getWeightForPartialMatching(buyerdto, suppilerdto);
        TreeMap<Integer,Suppilerdto> suppilerMap = new TreeMap<>();
        if(weight!=0) {
            if (nonNull(bestPartialMap.get(buyerdto))) {
                suppilerMap=bestPartialMap.get(buyerdto);
            }
            suppilerMap.put(weight,(Suppilerdto) suppilerdto);
            bestPartialMap.put((Buyerdto) buyerdto, suppilerMap);
            buyerdto.setFlag(Flag.PARTIALMATCHED);
            suppilerdto.setFlag(Flag.PARTIALMATCHED);
        }
    }

}
