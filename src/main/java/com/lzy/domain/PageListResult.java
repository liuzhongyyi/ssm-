package com.lzy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Setter@Getter@ToString
public class PageListResult {


    private long total;
    //查询的列数
    private List<?> rows= new ArrayList<>();
}
