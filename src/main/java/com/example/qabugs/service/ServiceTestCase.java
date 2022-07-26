package com.example.qabugs.service;

import com.example.qabugs.model.TestCase;
import com.example.qabugs.repository.TestCaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Service
public class ServiceTestCase implements IServiceTestCase{

    @Autowired
    private TestCaseRepo repo;

    @Override
    public TestCase create(TestCase newTestCase) {
        return repo.save(newTestCase);
    }

    @Override
    public List<TestCase> findAll() {
        return (List<TestCase>) repo.findAll();
    }

    @Override
    public List<TestCase> findByDate(Date filter){
        List<TestCase> t = repo.findByLastUpdateAfter(filter);
        return t;
    }

    @Override
    public TestCase update(TestCase newTestCase) {
        this.findById(newTestCase.getId());
        newTestCase.setLastUpdate(new Date());
        return repo.save(newTestCase);
    }

    @Override
    public void remove(long id) {
        TestCase testCase = this.findById(id);
        repo.delete(testCase);
    }

    @Override
    public TestCase findById(long id){
        return repo.findById(id).get();
    }
}
