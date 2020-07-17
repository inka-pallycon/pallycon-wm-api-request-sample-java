package com.pallycon.sample.config;

/**
 * Enum Class for API URL and METHOD
 */
public enum ApiUrl {
    PACK_JOB_REGISTER("https://api.pallycon.com/api/v2/pack/", "POST"),
    PACK_JOB_LIST("https://api.pallycon.com/api/v2/pack/", "GET"),
    SESSION_WATERMARK_URL("https://watermark.pallycon.com/api/v2/session/watermarkUrl/", "GET"),
    SESSION_WATERMARK_URL_AKAMAI("https://watermark.pallycon.com/api/v2/session/watermarkUrl/akamai/", "GET"),
    STORAGE_REGISTER("https://api.pallycon.com/api/v2/storage/", "POST"),
    STORAGE_UPDATE("https://api.pallycon.com/api/v2/storage/", "PUT"),
    STORAGE_LIST("https://api.pallycon.com/api/v2/storage/", "GET")
    ;

    private String url;
    private String method;

    ApiUrl(String url, String method) {
        this.url = url;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
