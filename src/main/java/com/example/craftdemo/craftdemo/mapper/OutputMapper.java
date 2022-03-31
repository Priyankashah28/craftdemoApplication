package com.example.craftdemo.craftdemo.mapper;

import com.example.craftdemo.craftdemo.entity.Outputdto;
import com.example.craftdemo.craftdemo.service.BuyerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OutputMapper {

    private static final Logger logger= LoggerFactory.getLogger(OutputMapper.class);

    public List<String[]> setOutputMapper(List<Outputdto> outputDtos){
        logger.info("Set the  outputdtos object to the output Mapper.............");
        List<String[]> allBuyerAndSuppliersDataList = new ArrayList<>();
        String [] headerElement= new String []{"BuyerGSTIN","BuyerDate","BuyerBill no","BuyerGST rate(%)","BuyerTaxable value","BuyerIGST","BuyerCGST","BuyerSGST","BuyerTotal","Category","SuppilerGSTIN","SuppilerDate","SuppilerBill no","SuppilerGST rate(%)","SuppilerTaxable value","SuppilerIGST","SuppilerCGST","SuppilerSGST","SuppilerTotal"};
        allBuyerAndSuppliersDataList.add(headerElement);
        for(Outputdto outputdto:outputDtos){
            List<String> resultList=new ArrayList<>();
            resultList.add(outputdto.getBuyerdto()!=null?outputdto.getBuyerdto().getGstNo():"NA");
            resultList.add(outputdto.getBuyerdto()!=null?outputdto.getBuyerdto().getBillReport().getBillNo():"NA");
            resultList.add(outputdto.getBuyerdto()!=null?String.valueOf(outputdto.getBuyerdto().getBillReport().getBillDate()):"NA");
            resultList.add(outputdto.getBuyerdto()!=null?String.valueOf(outputdto.getBuyerdto().getBillReport().getBillValue()):"NA");
            resultList.add(outputdto.getBuyerdto()!=null?String.valueOf(outputdto.getBuyerdto().getBillReport().getGst().getRate()):"NA");
            resultList.add(outputdto.getBuyerdto()!=null?String.valueOf(outputdto.getBuyerdto().getBillReport().getGst().getIgst()):"NA");
            resultList.add(outputdto.getBuyerdto()!=null?String.valueOf(outputdto.getBuyerdto().getBillReport().getGst().getCgst()):"NA");
            resultList.add(outputdto.getBuyerdto()!=null?String.valueOf(outputdto.getBuyerdto().getBillReport().getGst().getSgst()):"NA");
            resultList.add(outputdto.getBuyerdto()!=null?String.valueOf(outputdto.getBuyerdto().getBillReport().getTotalValue()):"NA");
            resultList.add(outputdto.getCategory().toString());
            resultList.add(outputdto.getSuppilerdto()!=null?outputdto.getSuppilerdto().getGstNo():"NA");
            resultList.add(outputdto.getSuppilerdto()!=null?outputdto.getSuppilerdto().getBillReport().getBillNo():"NA");
            resultList.add(outputdto.getSuppilerdto()!=null?String.valueOf(outputdto.getSuppilerdto().getBillReport().getBillDate()):"NA");
            resultList.add(outputdto.getSuppilerdto()!=null?String.valueOf(outputdto.getSuppilerdto().getBillReport().getBillValue()):"NA");
            resultList.add(outputdto.getSuppilerdto()!=null?String.valueOf(outputdto.getSuppilerdto().getBillReport().getGst().getRate()):"NA");
            resultList.add(outputdto.getSuppilerdto()!=null?String.valueOf(outputdto.getSuppilerdto().getBillReport().getGst().getIgst()):"NA");
            resultList.add(outputdto.getSuppilerdto()!=null?String.valueOf(outputdto.getSuppilerdto().getBillReport().getGst().getCgst()):"NA");
            resultList.add(outputdto.getSuppilerdto()!=null?String.valueOf(outputdto.getSuppilerdto().getBillReport().getGst().getSgst()):"NA");
            resultList.add(outputdto.getSuppilerdto()!=null?String.valueOf(outputdto.getSuppilerdto().getBillReport().getTotalValue()):"NA");
            String[] resultArray = new String[resultList.size()];
            resultList.toArray(resultArray);
            allBuyerAndSuppliersDataList.add(resultArray);
        }
        return allBuyerAndSuppliersDataList;
    }
}
