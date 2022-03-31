package com.example.craftdemo.craftdemo.controller;

import com.example.craftdemo.craftdemo.service.BuyerSuppilerComparisonService;
import com.example.craftdemo.craftdemo.service.BuyerSuppilerReconcileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/craftDemo")
public class BuyerAndSuppilerCategorizedController {

    private static final Logger logger= LoggerFactory.getLogger(BuyerAndSuppilerCategorizedController.class);

    @Autowired
    BuyerSuppilerReconcileService buyerSuppilerReconcileService;

    @GetMapping("/api/reconcilesData")
    public ResponseEntity<String> getcategorizationBetweenBuyerandSuppiler () throws IOException, ParseException {
        try {
            logger.info("Reconciles Matches Service Called...........");
            buyerSuppilerReconcileService.categorizationBetweenBuyerandSuppiler();
            logger.info("File is created succesfully.. Response Code {}",HttpStatus.OK);
            return new ResponseEntity<String>("File is Successfully Downloaded....", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<String>("Some Issue While Downloading the Data..." + e.getMessage()+" ", HttpStatus.BAD_REQUEST);
        }

    }

}
