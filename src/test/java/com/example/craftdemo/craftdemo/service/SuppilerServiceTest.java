package com.example.craftdemo.craftdemo.service;

import com.example.craftdemo.craftdemo.entity.*;
import com.example.craftdemo.craftdemo.mapper.BusinessMapper;
import com.example.craftdemo.craftdemo.util.CsvUtility;
import com.example.craftdemo.craftdemo.util.ValidationUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SuppilerServiceTest {

    @InjectMocks
    CsvUtility csvUtility;

    @InjectMocks
    SuppilerService suppilerService;

    @InjectMocks
    BusinessMapper businessMapper;

    @InjectMocks
    ValidationUtil validationUtil;

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(suppilerService,"fileName","D:/craftdemo/craftdemo/src/main/resources/Supplier.csv");
        ReflectionTestUtils.setField(suppilerService,"csvUtility",csvUtility);
        ReflectionTestUtils.setField(suppilerService,"businessMapper",businessMapper);
        ReflectionTestUtils.setField(businessMapper,"validationUtil",validationUtil);
    }

    @Test
    public void generateReportForBuyer() throws IOException, ParseException {
        List<Businessdto> actualBussinessDto=suppilerService.generateBusinessReport();
        Assert.assertEquals(actualBussinessDto.get(0).getGstNo(),getExpectedBussinessDto().getGstNo());
        Assert.assertEquals(actualBussinessDto.get(0).getBillReport().getBillNo(),getExpectedBussinessDto().getBillReport().getBillNo());
        Assert.assertEquals(actualBussinessDto.get(0).getBillReport().getBillDate(),getExpectedBussinessDto().getBillReport().getBillDate());
        Assert.assertEquals(actualBussinessDto.get(0).getBillReport().getBillValue(),getExpectedBussinessDto().getBillReport().getBillValue(),0.02);
        Assert.assertEquals(actualBussinessDto.get(0).getBillReport().getTotalValue(),getExpectedBussinessDto().getBillReport().getTotalValue(),0.02);
        Assert.assertEquals(actualBussinessDto.get(0).getBillReport().getGst().getRate(),getExpectedBussinessDto().getBillReport().getGst().getRate(),0.02);
        Assert.assertEquals(actualBussinessDto.get(0).getBillReport().getGst().getIgst(),getExpectedBussinessDto().getBillReport().getGst().getIgst(),0.02);
        Assert.assertEquals(actualBussinessDto.get(0).getBillReport().getGst().getSgst(),getExpectedBussinessDto().getBillReport().getGst().getSgst(),0.02);
        Assert.assertEquals(actualBussinessDto.get(0).getBillReport().getGst().getCgst(),getExpectedBussinessDto().getBillReport().getGst().getCgst(),0.02);

    }

    public Businessdto getExpectedBussinessDto() throws ParseException {
        Businessdto businessdto=new Suppilerdto();
        businessdto.setGstNo("29AAACB2894G1ZJ");
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
        businessdto.setBillReport(billReportdto);
        return businessdto;
    }
}
