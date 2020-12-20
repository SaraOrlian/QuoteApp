package com.example.quote_app;

import retrofit2.http.GET;
import retrofit2.Call;

public interface QuoteService {
    @GET("/random")
    Call<QuoteFeed> getRandomQuote();
}
