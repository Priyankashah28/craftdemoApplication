package com.example.craftdemo.craftdemo.util;

import com.example.craftdemo.craftdemo.service.BuyerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ValidationUtil {

    private static final Logger logger= LoggerFactory.getLogger(ValidationUtil.class);

    public String validateString(String inputEle){
        if(StringUtils.isNoneBlank(inputEle)){
            return inputEle;
        }
        return "0";
    }

    public Date validateDate(String dateInput) throws ParseException {
        if(dateInput.contains("-")) {
            return new SimpleDateFormat("dd-MM-yyyy").parse(dateInput);
        }
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
    }

    public String removeComma(String inputEle){
        return inputEle.replaceAll(",","");
    }

}
