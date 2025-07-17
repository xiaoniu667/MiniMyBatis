package com.xiaoniu.bean;

import com.xiaoniu.annotation.XiaoNiuParam;

import java.util.List;

public interface SchoolMapper {

    School querySchoolName(@XiaoNiuParam("school") School school, @XiaoNiuParam("school2") School school2);

    int updateSchoolName(School school);

    int insertSchoolName(School school);

    int deleteSchoolName(School school);

    List<School> queryList(@XiaoNiuParam("school") School school);

}
