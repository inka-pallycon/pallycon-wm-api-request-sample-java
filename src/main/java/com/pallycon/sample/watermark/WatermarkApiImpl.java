package com.pallycon.sample.watermark;

import com.pallycon.sample.util.StringEncrypter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

/**
 * A simple note how this module works.
 * 0. get `site_id`, `access_key`, `site_key`, and `json_req`
 * 1. encrypt `json_req` with `_make_data()`.
 * 2. make `timestamp` with `_make_timestamp()`.
 * 3. make `hash` with `_make_hash()`.
 * 4. return PallyCon HTTP API specification String
 */
public class WatermarkApiImpl implements WatermarkApi {

    private final String siteId;
    private final String accessKey;
    private final String siteKey;

    private String data;
    private String timestamp;
    private String hash;

    private final String AES_IV = "0123456789abcdef";

    public WatermarkApiImpl(@NotNull String siteId, @NotNull String accessKey, @NotNull String siteKey) {
        this.siteId = siteId;
        this.accessKey = accessKey;
        this.siteKey = siteKey;
    }


    /**
     * The One And Only function to call.
     *     Generate an encrypted String for PallyCon Api (a.k.a PallyCon HTTP API specification).
     *
     * @param jsonRequest
     * @return
     * @throws Exception
     */
    @Override
    public String execute(String jsonRequest) throws Exception {
        makeData(jsonRequest);
        makeTimestamp();
        makeHash();
        return generatePallyConHttpApi();
    }

    private String generatePallyConHttpApi() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", this.data);
        jsonObject.put("timestamp", this.timestamp);
        jsonObject.put("hash", this.hash);
        return Base64.getEncoder().encodeToString(jsonObject.toJSONString().getBytes());
    }

    private void makeData(String jsonRequest) throws Exception {
        StringEncrypter encrypter = new StringEncrypter(this.siteKey, AES_IV);
        this.data = encrypter.encrypt(jsonRequest);
    }

    private void makeTimestamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.timestamp = format.format(new Date());;
    }

    private void makeHash() throws Exception {
        StringBuffer bf = new StringBuffer();
        bf.append(this.accessKey);
        bf.append(this.siteId);
        bf.append(this.data);
        bf.append(this.timestamp);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(bf.toString().getBytes(StandardCharsets.UTF_8));
        this.hash = Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String getHash() {
        return hash;
    }

    @Override
    public String getSiteId() {
        return siteId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSiteKey() {
        return siteKey;
    }
}
