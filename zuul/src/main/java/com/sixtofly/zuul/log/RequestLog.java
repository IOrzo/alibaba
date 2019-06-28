package com.sixtofly.zuul.log;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author xie yuan bing
 * @date 2019-06-16 16:18
 * @description
 */
@Data
@Document(indexName = "alibaba-log", type = "alibaba-log")
public class RequestLog {

    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private String application;
    @Field(type = FieldType.Date)
    private Date occurrenceTime;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String message;
    @Field(type = FieldType.Ip)
    private String ip;
    @Field(type = FieldType.Keyword)
    private String level;
    @Field(type = FieldType.Keyword)
    private String throwable;
    @Field(type = FieldType.Keyword)
    private String traceId;
    @Field(type = FieldType.Keyword)
    private String browser;
    @Field(type = FieldType.Keyword)
    private String browserVersion;
    @Field(type = FieldType.Keyword)
    private String os;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String params;
    @Field(type = FieldType.Keyword)
    private String url;
    @Field(type = FieldType.Keyword)
    private String userId;
    @Field(type = FieldType.Keyword)
    private String responseCode;
    @Field(type = FieldType.Keyword)
    private String responseText;
    @Field(type = FieldType.Long)
    private long responseTime;
    @Field(type = FieldType.Keyword)
    private String version;
    @Field(type = FieldType.Keyword)
    private String client;
    @Field(type = FieldType.Keyword)
    private String username;
    @Field(type = FieldType.Keyword)
    private String userType;
    @Field(type = FieldType.Keyword)
    private String userAgent;
    @Field(type = FieldType.Keyword)
    private String host;
    @Field(type = FieldType.Keyword)
    private String origin;
    @Field(type = FieldType.Keyword)
    private String referer;
    @Field(type = FieldType.Integer)
    private int httpStatus;
//    @Field(type = FieldType.Object)
//    private IpInfo ipInfo;
    @Field(type = FieldType.Keyword)
    private String contentType;
    @Field(type = FieldType.Integer)
    private int contentLength;
    @Field(type = FieldType.Long)
    private long responseContentLength;

    /**
     * 响应 最长记录字符串
     */
    private static final Integer MAX_RECORD_RESPONSE_LENGTH = 10000;

    public String getResponseText() {
        if (responseText != null && responseText.length() > MAX_RECORD_RESPONSE_LENGTH) {
            responseText = responseText.substring(0, 9980) + "...";
        }
        return responseText;
    }

    public void initRequestInfo(HttpServletRequest request, String application) {
        this.application = application;
        if (request == null) {
            return;
        }
        String userAgentStr = request.getHeader("user-agent");

        this.ip = ip;
        this.contentLength = request.getContentLength();
        this.contentType = request.getContentType();
        this.params = params;
        this.url = request.getRequestURI();
        StringBuffer url = request.getRequestURL();
        this.host = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString().replaceFirst("https://", "").replaceFirst("http://", "");

        //冒号
        String colon = ":";

        if (this.host.contains(colon)) {
            this.host = this.host.split(colon)[0];
        }
        this.origin = request.getHeader("Origin");
        this.referer = request.getHeader("Referer");
        this.client = request.getHeader("client");
        this.version = request.getHeader("version");
        this.userAgent = userAgentStr;
    }
}
