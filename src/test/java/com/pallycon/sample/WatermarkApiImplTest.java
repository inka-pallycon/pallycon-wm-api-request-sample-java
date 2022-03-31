package com.pallycon.sample;


import com.pallycon.sample.config.ApiUrl;
import com.pallycon.sample.watermark.WatermarkApi;
import com.pallycon.sample.watermark.WatermarkApiImpl;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * THIS IS AN SAMPLE CODE FOR GENERATE PallyCon HTTP API specification.
 */
public class WatermarkApiImplTest {
    private static Logger logger = LoggerFactory.getLogger(WatermarkApiImplTest.class);
    WatermarkApi watermarkApi;

    @Before
    public void setUp() {
        // TODO 1st: set these variables below.
        String siteId = "<Enter your siteId>";
        String accessKey = "<Enter your accessKey>";
        String siteKey = "<Enter your siteKey>";

        // TODO 2nd: construct WatermarkApiImpl.
        watermarkApi = new WatermarkApiImpl(siteId, accessKey, siteKey);
    }

    @Test
    public void execute() throws Exception {
        // TODO 3rd: set jsonRequest.
        // i.e. "{\"region\" : \"RG004\"}"
        String jsonRequest = "<Enter the request>";

        // TODO 4th: call method `execute`.
        String result = watermarkApi.execute(jsonRequest);

        logger.info("json Data  : " + jsonRequest);
        logger.info("data       : " + watermarkApi.getData());
        logger.info("hash       : " + watermarkApi.getHash());
        logger.info("timestamp  : " + watermarkApi.getTimestamp());
        logger.info("result     : " + result);

        /**
         * TODO 5th: ( Optional ) If want to see the whole url and method, call the makeUrl(String result, ApiUrl apiUrl) below.
         * i.e. If want to call packaging job list API, use ApiUrl.PACK_JOB_LIST in the parameter.
         * makeUrl(result, ApiUrl.PACK_JOB_LIST) then returns
         *     {
         *     "method": "GET",
         *     "url": "https://api.pallycon.com/api/v2/pack/{siteId}?pallycon-apidata={result String you executed}"
         *     }
         */
//        makeUrl(result, ApiUrl.PACK_JOB_LIST);
//        makeUrl(result, ApiUrl.PACK_JOB_REGISTER);
//        makeUrl(result, ApiUrl.SESSION_WATERMARK_URL_GENERATE);
//        makeUrl(result, ApiUrl.SESSION_WATERMARK_TOKEN_GENERATE);
//        makeUrl(result, ApiUrl.SESSION_LIST);
//        makeUrl(result, ApiUrl.STORAGE_LIST);
//        makeUrl(result, ApiUrl.STORAGE_REGISTER);
//        makeUrl(result, ApiUrl.STORAGE_UPDATE);
//        makeUrl(result, ApiUrl.DETECT_REGISTER);
//        makeUrl(result, ApiUrl.DETECT_LIST);
//        makeUrl(result, ApiUrl.DETECT_DETAIL);
    }

    // can get the result as a type of JSON
    private void makeUrl(String result, ApiUrl apiUrl) {
        String url = apiUrl.makeRequestUrl(result, watermarkApi);
        String method = apiUrl.getMethod();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        jsonObject.put("method", method);
        logger.info("url        : " + jsonObject.toJSONString());
    }
}
