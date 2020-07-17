# pallycon-wm-api-request-sample

This sample is to get specification for PallyCon Watermark APIs for java 
<br><br>

## Prerequisites

### IDE

- JAVA JDK 1.8 +

<br><br>


## Quick Start

**FOLLOW THE TODOs**

This code is from `src\test\java\com\pallycon\sample\WatermarkApiImplTest.java` Class.

```java

public class WatermarkApiImplTest {
    private static Logger logger = LoggerFactory.getLogger(WatermarkApiImplTest.class);
    WatermarkApi watermarkApi;

    @Before
    public void setUp() {
        // TODO 1st: set these variables below.
        String siteId = "TUTO";
        String accessKey = "LT2FVJDp2Xr018zf4Di6lzvNOv3DKP20";
        String siteKey = "lU5D8s3PWoLls3PWFWkClULlFWk5D8oC";

        // TODO 2nd: construct WatermarkApiImpl.
        watermarkApi = new WatermarkApiImpl(siteId, accessKey, siteKey);
    }

    @Test
    public void execute() throws Exception {
        // TODO 3rd: set jsonRequest.
        String jsonRequest = "{\n" +
                 "    \"storage_type\": \"S3\",\n" +
                 "    \"region\": \"RG011\"\n" +
                 "}";

        // TODO 4th: call method `execute`.
        String result = watermarkApi.execute(jsonRequest);

        logger.info("json Data  : " + jsonRequest);
        logger.info("data       : " + watermarkApi.getData());
        logger.info("hash       : " + watermarkApi.getHash());
        logger.info("timestamp  : " + watermarkApi.getTimestamp());
        logger.info("result     : " + result);
    }
}
```
<br>

#### Result of quick start

```
[main] INFO WatermarkApiImplTest - json Data  : {
    "storage_type": "S3",
    "region": "RG011"
}
[main] INFO WatermarkApiImplTest - data       : vvl3SpynNOShxsNa3cBOomYzaKX3+7JQ5ohZ4aqOFJFAUotKeUoV0wYN6nUu7lrdiFW3OCgGiIHDe1/BvdQhOQ==
[main] INFO WatermarkApiImplTest - hash       : 38MDPO7fkXhRxmaLISVMy0UCZ1ExScIT7b33z6E+S7E=
[main] INFO WatermarkApiImplTest - timestamp  : 2020-07-15T10:42:34Z
[main] INFO WatermarkApiImplTest - result     : eyJkYXRhIjoidnZsM1NweW5OT1NoeHNOYTNjQk9vbVl6YUtYMys3SlE1b2haNGFxT0ZKRkFVb3RLZVVvVjB3WU42blV1N2xyZGlGVzNPQ2dHaUlIRGUxXC9CdmRRaE9RPT0iLCJoYXNoIjoiMzhNRFBPN2ZrWGhSeG1hTElTVk15MFVDWjFFeFNjSVQ3YjMzejZFK1M3RT0iLCJ0aW1lc3RhbXAiOiIyMDIwLTA3LTE1VDEwOjQyOjM0WiJ9

```


<br><br>

### HOW IT WORKS ?

If want to see how it works, go to Class  `src\main\java\com\pallycon\sample\watermark\WatermarkApiImpl.java`. On the top of this Class, there is a simple note how it works.

> 1. get `site_id`, `access_key`, `site_key`, and `json_req`
> 2. encrypt `json_req` with `_make_data()`.
> 3. make `timestamp` with `_make_timestamp()`.
> 4. make `hash` with `_make_hash()`.
> 5. return PallyCon Watermark API specificated String from the values `#2 ~ #4`

<br><br><br><br><br><br>

