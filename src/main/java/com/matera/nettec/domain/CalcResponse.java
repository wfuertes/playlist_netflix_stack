/*
 * Copyright 2015, Charter Communications, All rights reserved.
 */
package com.matera.nettec.domain;

/**
 *
 *
 * @author wbatista
 */
public class CalcResponse {

    private double result;
    private String msg;

    public CalcResponse(double result, String msg) {

        this.result = result;
        this.msg = msg;
    }

    public double getResult() {

        return result;
    }

    public void setResult(double result) {

        this.result = result;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {

        this.msg = msg;
    }

}
