package com.example.service;

import com.example.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportService {
    JobOption getJobReport();

    List<Map<String, Object>> getGenderReport();
}
