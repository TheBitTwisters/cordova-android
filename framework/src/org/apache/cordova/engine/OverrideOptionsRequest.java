package org.apache.cordova.engine;

import android.annotation.TargetApi;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.webkit.WebResourceResponse;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class OverrideOptionsRequest {
    static final SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy kk:mm:ss", Locale.US);

    @TargetApi(21)
    static WebResourceResponse build(String domain) {
        Date date = new Date();
        final String dateString = formatter.format(date);

        Map<String, String> headers = new HashMap<String, String>() {{
            put("Connection", "close");
            put("Content-Type", "application/json");
            put("Date", dateString + " GMT");
            put("Access-Control-Allow-Origin", domain);
            put("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
            put("Access-Control-Max-Age", "600");
            put("Access-Control-Allow-Credentials", "true");
            put("Access-Control-Allow-Headers", "accept, authorization, Content-Type");
            put("Via", "1.1 vegur");
        }};

        return new WebResourceResponse("application/json", "UTF-8", 200, "OK", headers, null);
    }
}
