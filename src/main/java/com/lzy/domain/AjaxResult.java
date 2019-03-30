package com.lzy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class AjaxResult {

    private boolean isSuccessful;

    private String msg;
}
