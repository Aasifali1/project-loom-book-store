package com.bestdeal.bookservice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomErrorController {
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleNotFound(HttpServletRequest request, NoHandlerFoundException ex) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("status", HttpStatus.NOT_FOUND.value());
        errorAttributes.put("error", "Not Found");
        errorAttributes.put("message", ex.getMessage());
        errorAttributes.put("path", request.getRequestURI());
        return new ResponseEntity<>(errorAttributes, HttpStatus.NOT_FOUND);
    }
}
