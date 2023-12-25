package com.turborvip.crawler.application.utils;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;

import java.io.IOException;
import java.util.Arrays;

public class RequestUtil {
    private static CloseableHttpClient httpClient;
    private static String baseUrl = "http://localhost:1337/";

    private static final Header[] headers = {
            new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"),
    };

    public RequestUtil(String baseUrl) {
        httpClient = HttpClientBuilder.create().setDefaultHeaders(Arrays.asList(headers)).build();
        RequestUtil.baseUrl = baseUrl;
    }

    public String get(String path, String... basicToken) throws IOException, ParseException {
        HttpEntity entity = null;
        HttpGet request = new HttpGet(baseUrl + path);

        try {
            request.setHeader(HttpHeaders.AUTHORIZATION, basicToken);
            CloseableHttpResponse response = httpClient.execute(request);
            entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException err) {
            throw new IOException(err);
        } finally {
            EntityUtils.consumeQuietly(entity);
        }
    }

    public String post(String path, String payload, String... basicToken) throws IOException, ParseException {
        HttpPost request = new HttpPost(baseUrl + path);
        request.setHeader(HttpHeaders.AUTHORIZATION, basicToken);
        request.setEntity(new StringEntity(payload));
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }
}
