package com.pallycon.sample.watermark;

/**
 * A simple note how this module works.
 * 0. get `site_id`, `access_key`, `site_key`, and `json_req`
 * 1. encrypt `json_req` with `_make_data()`.
 * 2. make `timestamp` with `_make_timestamp()`.
 * 3. make `hash` with `_make_hash()`.
 * 4. return PallyCon HTTP API specification String
 */
public interface WatermarkApi {
    String execute(String jsonRequest) throws Exception;

    String getData();

    String getTimestamp();

    String getHash();

    String getSiteId();
}
