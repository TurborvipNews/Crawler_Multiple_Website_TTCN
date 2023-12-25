package com.turborvip.crawler.adapter.web.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("VsResponseUtil")
@Getter
@Setter
@RequiredArgsConstructor
public class VsResponseUtil {
    public static ResponseEntity<RestData<?>> ok(Object data) {
        return ok(null, data, null);
    }

    public static ResponseEntity<RestData<?>> ok(String message, Object data) {
        return ok(message, data, null);
    }

    public static ResponseEntity<RestData<?>> ok(String message, Object data, Object option) {
        RestData<?> response = new RestData<>(message, data, option);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<RestData<?>> error(HttpStatus status, String userMessage, String devMessage) {
        RestData<?> response = RestData.error(userMessage, devMessage);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<RestData<?>> error(HttpStatus status, String userMessage) {
        RestData<?> response = RestData.error(userMessage);
        return new ResponseEntity<>(response, status);
    }
}
