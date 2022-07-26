package com.example.qabugs.controller;

import com.example.qabugs.model.TestCase;
import com.example.qabugs.service.IServiceTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/test-case")
public class TestCaseController {

    @Autowired
    private IServiceTestCase service;

    @PostMapping
    public ResponseEntity<TestCase> save(@RequestBody TestCase testCase) {
        return new ResponseEntity<>(service.create(testCase), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TestCase>> findTestCase(@RequestParam @Nullable String lastUpdate) {
        if (lastUpdate == null) {
            List<TestCase> list = service.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        try{
            SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
            Date filter = sdt.parse(lastUpdate);
            List<TestCase> list = service.findByDate(filter);

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestCase> findById(@PathVariable long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TestCase> update(@RequestBody TestCase testCase) {
        return new ResponseEntity<>(service.update(testCase), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.remove(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
