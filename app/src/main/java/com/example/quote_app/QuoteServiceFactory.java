package com.example.quote_app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteServiceFactory {
    public QuoteService getInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.quotable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(QuoteService.class);
    }
}
