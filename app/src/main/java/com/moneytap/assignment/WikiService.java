package com.moneytap.assignment;


import com.moneytap.assignment.model.PageResponse;
import com.moneytap.assignment.model.SearchQueryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WikiService {

    @GET(Config.SEARCH_QUERY)
    Call<SearchQueryResponse> searchQuery(@Query("gpssearch") String query, @Query("srsearch") String text);

    @GET(Config.GET_PAGE)
    Call<PageResponse> getPage(@Query("page") String pageName);

}
