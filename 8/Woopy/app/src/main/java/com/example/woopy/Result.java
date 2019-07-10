package com.example.woopy;

class Result {
    public String resultValue;
    public Exception exception;
    public Result(String resultValue) {
        this.resultValue = resultValue;
    }
    public Result(Exception exception) {
        this.exception = exception;
    }
}