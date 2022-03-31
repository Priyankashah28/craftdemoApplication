package com.example.craftdemo.craftdemo.service;

import com.example.craftdemo.craftdemo.entity.Businessdto;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BusinessService {
    public List<Businessdto> generateBusinessReport() throws IOException, ParseException;
}
