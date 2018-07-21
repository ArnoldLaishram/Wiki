package com.moneytap.assignment;

import java.util.HashMap;
import java.util.Map;

public class Config {

    // Headers required to be added by interceptor
    public static final Map<String, String> API_HEADERS = new HashMap<String, String>() {{
        put("Content-Type", "application/json");
    }};

    public static final String BASE_URL = "https://en.wikipedia.org//";

    public static final String SEARCH_QUERY = "w/api.php?action=query&format=json" +
            "&prop=pageimages%7Cpageterms&generator=prefixsearch" +
            "&redirects=1&formatversion=2&piprop=thumbnail" +
            "&pithumbsize=50&pilimit=10" +
            "&wbptterms=description&gpslimit=10";
}
