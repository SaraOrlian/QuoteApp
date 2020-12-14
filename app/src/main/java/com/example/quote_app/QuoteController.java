package com.example.quote_app;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteController {
    QuoteService service = new QuoteServiceFactory().getInstance();
    String quote;

    public void regenerateQuote() {
        service.getRandomQuote().enqueue(new Callback<QuoteFeed>() {
            @Override
            public void onResponse(Call<QuoteFeed> call, Response<QuoteFeed> response) {
                if (response.body() != null) {
                    quote = response.body().getContent();
                }
            }

            @Override
            public void onFailure(Call<QuoteFeed> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public String getQuote() {
        return quote;
    }
}
