package com.example.qabugs.service;

import com.example.qabugs.model.TestCase;

import java.util.Date;
import java.util.List;

public interface IServiceTestCase {
    TestCase create(TestCase newTestCase);
    List<TestCase> findAll();
    List<TestCase> findByDate(Date filter);
    TestCase findById(long id);
    TestCase update(TestCase newTestCase);
    void remove(long id);
}
