package com.example.qabugs.repository;

import com.example.qabugs.model.TestCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TestCaseRepo extends CrudRepository<TestCase, Long> {
    List<TestCase> findByLastUpdateAfter(Date lastUpdate);
}
