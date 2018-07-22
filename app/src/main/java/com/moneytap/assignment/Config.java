package com.moneytap.assignment;

import java.util.HashMap;
import java.util.Map;

public class Config {

    // Headers required to be added by interceptor
    public static final Map<String, String> API_HEADERS = new HashMap<String, String>() {{
        put("Content-Type", "application/json");
    }};

    public static final String BASE_URL = "https://en.wikipedia.org/";

    public static final String SEARCH_QUERY = "w/api.php?action=query&format=json&formatversion=2" +
            "&redirects=&converttitles=&prop=description|pageimages&piprop=thumbnail" +
            "&pilicense=any&generator=prefixsearch&gpsnamespace=0" +
            "&list=search&srnamespace=0&srwhat=text&srinfo=suggestion" +
            "&srprop=&sroffset=0&srlimit=1&gpslimit=20&pithumbsize=320";

    public static final String GET_PAGE = "w/api.php?action=parse&format=json&prop=text";
}
