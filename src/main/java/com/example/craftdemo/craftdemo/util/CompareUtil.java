package com.example.craftdemo.craftdemo.util;

import com.example.craftdemo.craftdemo.entity.Businessdto;
import com.example.craftdemo.craftdemo.service.BuyerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ValueRange;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CompareUtil{

    private static final Logger logger= LoggerFactory.getLogger(CompareUtil.class);

    @Value("${spring.application.threshold}")
    private int threshold;

    public boolean handleNumberPartialMatching(Double input1,Double input2){
        long newInput1 = input1.longValue();
      if((ValueRange.of(newInput1,newInput1+threshold).isValidIntValue(input2.longValue())) ||(ValueRange.of(newInput1-threshold,newInput1).isValidIntValue(input2.longValue()))) {
          return true;
      }
      return false;
    }

    public boolean handleStringPartialMatching(String input1,String input2){
       return input1.equals(input2);
    }

    public boolean handleDateParitalMatching(Date input1, Date input2){
        Date addDaysToInput1 = addDaysToDate(input1,this.threshold);
        Date minusDaysToInput1 = minusDaysToDate(input1,this.threshold);
        if((input2.after(input1) && input2.before(addDaysToInput1))|| (input2.after(minusDaysToInput1) && input2.before(input1))){
            return true;
        }
        return false;
    }

    public boolean handleExactMatch(String input1,String input2){
        Pattern pattern=Pattern.compile(input1);
        Matcher matcher=pattern.matcher(input2);
        if(matcher.matches()){
            return true;
        }
        return false;
    }

    public Date addDaysToDate(Date dateInput,int threshold){
        LocalDate newdate = dateInput.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        newdate.plusDays(threshold);
        return Date.from(newdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date minusDaysToDate(Date dateInput,int threshold){
        LocalDate newdate = dateInput.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        newdate.minusDays(threshold);
        return Date.from(newdate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    }
}
