package com.example.craftdemo.craftdemo.service;

import com.example.craftdemo.craftdemo.entity.Businessdto;

import com.example.craftdemo.craftdemo.entity.Buyerdto;
import com.example.craftdemo.craftdemo.entity.Outputdto;
import com.example.craftdemo.craftdemo.entity.Suppilerdto;
import com.example.craftdemo.craftdemo.enums.Category;
import com.example.craftdemo.craftdemo.enums.Flag;
import com.example.craftdemo.craftdemo.mapper.OutputMapper;
import com.example.craftdemo.craftdemo.util.CompareUtil;
import com.example.craftdemo.craftdemo.util.CsvUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.*;

import static java.util.Objects.*;

@Component
public class BuyerSuppilerComparisonService {

    private static final Logger logger= LoggerFactory.getLogger(BuyerSuppilerComparisonService.class);

    @Autowired
    private CompareUtil compareUtil;

    public int getWeightForPartialMatching(Businessdto buyerdto, Businessdto suppilerdto) {
        logger.info("Get partial match weight for every buyer and suppiler object.............................");
        int weight=0;
        if(compareUtil.handleStringPartialMatching(buyerdto.getGstNo(), suppilerdto.getGstNo())) weight++;
        if(compareUtil.handleStringPartialMatching(buyerdto.getBillReport().getBillNo(), suppilerdto.getBillReport().getBillNo()))
            weight++;
        if(compareUtil.handleDateParitalMatching((buyerdto.getBillReport().getBillDate()), suppilerdto.getBillReport().getBillDate()))
            weight++;
        if(compareUtil.handleNumberPartialMatching(buyerdto.getBillReport().getBillValue(), suppilerdto.getBillReport().getBillValue()))
            weight++;
        if(compareUtil.handleNumberPartialMatching(buyerdto.getBillReport().getTotalValue(), suppilerdto.getBillReport().getTotalValue()))
            weight++;
        if(compareUtil.handleNumberPartialMatching(buyerdto.getBillReport().getGst().getRate(), suppilerdto.getBillReport().getGst().getRate()))
            weight++;
        if(compareUtil.handleNumberPartialMatching(buyerdto.getBillReport().getGst().getIgst(), suppilerdto.getBillReport().getGst().getIgst()))
            weight++;
        if(compareUtil.handleNumberPartialMatching(buyerdto.getBillReport().getGst().getCgst(), suppilerdto.getBillReport().getGst().getCgst()))
            weight++;
        if(compareUtil.handleNumberPartialMatching(buyerdto.getBillReport().getGst().getSgst(), suppilerdto.getBillReport().getGst().getSgst()))
            weight++;
        return weight;
    }

    public boolean doExactMatch(Businessdto buyer, Businessdto suppiler){
        logger.info("Comparing for Exact match for buyer and suppiler...............................");
        return compareUtil.handleExactMatch(buyer.getGstNo(), suppiler.getGstNo())
                && compareUtil.handleExactMatch(buyer.getBillReport().getBillNo(), suppiler.getBillReport().getBillNo())
                && compareUtil.handleExactMatch(String.valueOf(buyer.getBillReport().getBillDate()), String.valueOf(suppiler.getBillReport().getBillDate()))
                && compareUtil.handleExactMatch(String.valueOf(buyer.getBillReport().getBillValue()), String.valueOf(suppiler.getBillReport().getBillValue()))
                && compareUtil.handleExactMatch(String.valueOf(buyer.getBillReport().getTotalValue()), String.valueOf(suppiler.getBillReport().getTotalValue()))
                && compareUtil.handleExactMatch(String.valueOf(buyer.getBillReport().getGst().getRate()), String.valueOf(suppiler.getBillReport().getGst().getRate()))
                && compareUtil.handleExactMatch(String.valueOf(buyer.getBillReport().getGst().getCgst()), String.valueOf(suppiler.getBillReport().getGst().getCgst()))
                && compareUtil.handleExactMatch(String.valueOf(buyer.getBillReport().getGst().getIgst()), String.valueOf(suppiler.getBillReport().getGst().getIgst()))
                && compareUtil.handleExactMatch(String.valueOf(buyer.getBillReport().getGst().getSgst()), String.valueOf(suppiler.getBillReport().getGst().getSgst()));
    }


}
