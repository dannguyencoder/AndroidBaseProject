package com.example.vinhcrazyyyy.androidbaseproject.services.intent_service.intent_service_network;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class HeadersUtils {

    public static String[] serializeHeaders(Header[] headers) {
        if (headers == null) {
            return new String[0];
        }

        String[] rtn = new String[headers.length * 2];
        int index = -1;
        for (Header h : headers) {
            rtn[++index] = h.getName();
            rtn[++index] = h.getValue();
        }
        return rtn;
    }

    public static Header[] deserializeHeaders(String[] serialized) {
        if (serialized == null || serialized.length %2 != 0) {
            return new Header[0];
        }

        Header[] headers = new Header[serialized.length / 2];
        for (int i = 0, h = 0; h < headers.length; i++, h++) {
            headers[h] = new BasicHeader(serialized[i], serialized[++i]);
        }
        return headers;
    }
}
