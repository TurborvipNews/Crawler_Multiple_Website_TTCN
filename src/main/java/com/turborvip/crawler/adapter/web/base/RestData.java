package com.turborvip.crawler.adapter.web.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestData<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)//@JsonInclude : This property will include Json if it different null
    private String errorMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)//@JsonInclude : This property will include Json if it different null
    private String userMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String devMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)//@JsonInclude : This property will include Json if it different null
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T metadata;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T option;

    public RestData(String userMessage, T data, T option) {
        this.metadata = data;
        this.option = option;
        this.message = userMessage;
    }

    public RestData(T data, T option) {
        this.metadata = data;
        this.option = option;

    }

    public RestData(T data) {
        this.metadata = data;
    }


    public RestData(String data,Boolean isError) {
        if (isError){
            this.errorMessage = data;
        }else{
            this.userMessage = data;
        }
    }

    public RestData(String userMessage, String devMessage, Object o, Object o1, Object o2) {
        System.out.println("Do something!");
    }

    public static RestData<?> error(String userMessage, String devMessage) {
        return new RestData<>(userMessage, devMessage, null, null, null);
    }

    public static RestData<?> error(String userMessage) {
        return new RestData<>(userMessage,true);
    }
}