package com.pallycon.sample.config;

import com.pallycon.sample.watermark.WatermarkApi;
import org.apache.commons.lang3.StringUtils;

/**
 * Enum Class for API URL and METHOD
 */
public enum ApiUrl {
    PACK_JOB_REGISTER("https://api.pallycon.com/api/v2/pack/", "POST"),
    PACK_JOB_LIST("https://api.pallycon.com/api/v2/pack/", "GET"),

    SESSION_WATERMARK_URL_GENERATE("https://watermark.pallycon.com/api/v2/session/watermarkUrl/", "GET"),
    SESSION_WATERMARK_TOKEN_GENERATE("https://watermark.pallycon.com/api/v2/session/watermarkData/", "GET"),
    SESSION_LIST("https://watermark.pallycon.com/api/v2/session/list/", "GET"),

    STORAGE_REGISTER("https://api.pallycon.com/api/v2/storage/", "POST"),
    STORAGE_UPDATE("https://api.pallycon.com/api/v2/storage/", "PUT"),
    STORAGE_LIST("https://api.pallycon.com/api/v2/storage/", "GET"),

    DETECT_REGISTER("https://api.pallycon.com/api/v2/detect/", "POST", "url"),
    DETECT_LIST("https://api.pallycon.com/api/v2/detect/", "GET", "list"),
    DETECT_DETAIL("https://api.pallycon.com/api/v2/detect/", "GET", "detail")
    ;

    private String url;
    private String method;
    private String subUrl;

    ApiUrl(String url, String method) {
        this(url, method, null);
    }

    ApiUrl(String url, String method, String subUrl) {
        this.url = url;
        this.method = method;
        this.subUrl = subUrl;
    }

    public String getMethod() {
        return method;
    }


    public String makeRequestUrl(String apiDataString, WatermarkApi watermarkApi) {
        String siteId = watermarkApi.getSiteId();

        StringBuffer url = new StringBuffer();
        url.append(generateBaseUrl(siteId));
        url.append(generateQueryString(apiDataString));

        return url.toString();
    }

    // generate base url for Request url
    private String generateBaseUrl(String siteId) {
        StringBuffer baseUrl = new StringBuffer(this.url);
        baseUrl.append(siteId);
        baseUrl.append(generateSubUrl());

        return baseUrl.toString();
    }

    private String generateSubUrl() {
        if (StringUtils.isEmpty(this.subUrl)) {
            return "";
        }

        return "/" + this.subUrl;
    }

    // generate queryString to get added on base url
    private String generateQueryString(String apiDataString) {
        return "?pallycon-apidata=" + apiDataString;
    }
}
