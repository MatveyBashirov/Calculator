package ru.neoflex.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.practice.database.resultTable;
import ru.neoflex.practice.repository.resultRep;

import java.util.List;

@RestController
public class CalcController {

    @Autowired
    private resultRep resultRepository;

    @GetMapping("/plus/{a}/{b}")
    public Integer Sum (@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        int result = a+b;
        resultRepository.save(new resultTable(a,"+",b,result));
        return result;
    }

    @GetMapping("/minus/{a}/{b}")
    public Integer Diff(@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        int result = a-b;
        resultRepository.save(new resultTable(a,"-",b,result));
        return result;
    }

    @GetMapping("/allResults")
    public List<resultTable> getAllResults() {
        return resultRepository.findAll();
    }

    @GetMapping("/allResultsOnly")
    public List<Integer> getAllResultsOnly() {
        return resultRepository.findAllResultsOnly();
    }
}
