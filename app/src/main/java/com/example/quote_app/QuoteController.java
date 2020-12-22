package com.example.quote_app;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteController {
    private final QuoteService service = new QuoteServiceFactory().getInstance();

    private String quote;
    private String author;

    public QuoteController() {
        regenerateQuote();
    }

    public void regenerateQuote() {
        service.getRandomQuote().enqueue(new Callback<QuoteFeed>() {
            @Override
            public void onResponse(Call<QuoteFeed> call, Response<QuoteFeed> response) {
                if (response.body() != null) {
                    quote = response.body().getContent();
                    author = response.body().getAuthor();
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

    public String getAuthor() {
        return author;
    }
}