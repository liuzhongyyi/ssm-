package com.lzy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageNum {
    private int page;
    private int rows;
    private String keyword;
}
