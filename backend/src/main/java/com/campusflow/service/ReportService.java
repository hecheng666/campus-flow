package com.campusflow.service;

import com.campusflow.pojo.ClazzCountOption;
import com.campusflow.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getGenderData();

    List<Map<String, Object>> getDegreeData();

    ClazzCountOption getStudentCountData();
}
