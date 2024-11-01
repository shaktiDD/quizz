package com.backend.quizz.dto;

public class BaseResponseDTO {
    private boolean success;
    private String message;

    public BaseResponseDTO() {}

    public BaseResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
