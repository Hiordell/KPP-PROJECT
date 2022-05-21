package ru.specialist.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultDto {

    @JsonProperty
    List<TriangleInfo> resultList;

    public ResultDto(List<TriangleInfo> resultList) {
        this.resultList = resultList;
    }
}
