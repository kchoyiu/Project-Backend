package com.fsse2309.project_backend.dto;

public class SuccessResponseDto {
    private String result;

    public SuccessResponseDto() {
       setResult();
    }

    public String getResult() {
        return result;
    }

    private void setResult() {
        this.result = "success";
    }
}
