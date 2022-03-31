package com.example.craftdemo.craftdemo.service;

import com.example.craftdemo.craftdemo.entity.*;
import com.example.craftdemo.craftdemo.util.CompareUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuyerSuppilerComparisonServiceTest {

    @InjectMocks
    private BuyerSuppilerComparisonService buyerSuppilerComparisonService;

    @InjectMocks
    private CompareUtil compareUtil;

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(buyerSuppilerComparisonService,"compareUtil",compareUtil);
    }

    @Test
    public void setWeightForPartialMatching() throws ParseException {
        int weight=buyerSuppilerComparisonService.getWeightForPartialMatching(getBuyerDto().get(0),getSuppilerDto().get(0));
        Assert.assertEquals(weight,5);

    }

    @Test
    public void shouldCheckForNotExactMatch() throws ParseException {
        Assert.assertEquals(buyerSuppilerComparisonService.doExactMatch(getBuyerDto().get(0),getSuppilerDto().get(0)),false);
    }

    @Test
    public void shouldCheckForExactMatch() throws ParseException {
        Assert.assertEquals(buyerSuppilerComparisonService.doExactMatch(getBuyerDto().get(1),getSuppilerDto().get(1)),true);
    }


    public List<Buyerdto> getBuyerDto() throws ParseException {

        List<Buyerdto> buyerdtos = new ArrayList<>();
        Buyerdto buyerdto=new Buyerdto();
        buyerdto.setGstNo("29AAACB2894G1ZJ");
        BillReportdto billReportdto=new BillReportdto();
        billReportdto.setBillNo("55000444");
        billReportdto.setBillDate(new SimpleDateFormat("dd-MM-yyyy").parse("03-09-2017"));
        billReportdto.setBillValue(3000.00);
        billReportdto.setTotalValue(2476.82);
        GSTdto gstDto=new GSTdto();
        gstDto.setCgst(188.91);
        gstDto.setSgst(188.91);
        gstDto.setRate(25);
        billReportdto.setGst(gstDto);
        buyerdto.setBillReport(billReportdto);
        buyerdtos.add(buyerdto);

        Buyerdto buyerdto1=new Buyerdto();
        buyerdto1.setGstNo("29AAACB2894G1ZJ");
         billReportdto=new BillReportdto();
        billReportdto.setBillNo("474330129");
        billReportdto.setBillDate(new SimpleDateFormat("dd-MM-yyyy").parse("03-09-2017"));
        billReportdto.setBillValue(2099.00);
        billReportdto.setTotalValue(2476.82);
        gstDto=new GSTdto();
        gstDto.setCgst(188.91);
        gstDto.setSgst(188.91);
        gstDto.setRate(18);
        billReportdto.setGst(gstDto);
        buyerdto1.setBillReport(billReportdto);
        buyerdtos.add(buyerdto1);

        return buyerdtos;
    }

    public List<Suppilerdto> getSuppilerDto() throws ParseException {
        List<Suppilerdto> suppilerdtos=new ArrayList<>();
        Suppilerdto suppilerdto=new Suppilerdto();
        suppilerdto.setGstNo("29AAACB2894G1ZJ");
        BillReportdto billReportdto=new BillReportdto();
        billReportdto.setBillNo("474330129");
        billReportdto.setBillDate(new SimpleDateFormat("dd-MM-yyyy").parse("03-09-2017"));
        billReportdto.setBillValue(2099.00);
        billReportdto.setTotalValue(2476.82);
        GSTdto gstDto=new GSTdto();
        gstDto.setCgst(188.91);
        gstDto.setSgst(188.91);
        gstDto.setRate(18);
        billReportdto.setGst(gstDto);
        suppilerdto.setBillReport(billReportdto);
        suppilerdtos.add(suppilerdto);

        Suppilerdto suppilerdto1=new Suppilerdto();
        suppilerdto1.setGstNo("29AAACB2894G1ZJ");
         billReportdto=new BillReportdto();
        billReportdto.setBillNo("474330129");
        billReportdto.setBillDate(new SimpleDateFormat("dd-MM-yyyy").parse("03-09-2017"));
        billReportdto.setBillValue(2099.00);
        billReportdto.setTotalValue(2476.82);
         gstDto=new GSTdto();
        gstDto.setCgst(188.91);
        gstDto.setSgst(188.91);
        gstDto.setRate(18);
        billReportdto.setGst(gstDto);
        suppilerdto1.setBillReport(billReportdto);
        suppilerdtos.add(suppilerdto1);

        return suppilerdtos;
    }
}
