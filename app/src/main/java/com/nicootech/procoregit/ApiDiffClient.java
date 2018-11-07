package com.nicootech.procoregit;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiDiffClient {

    public static final String BASE_DIFF_URL = "https://github.com/";
    public static Retrofit diffRetrofit = null;

    public static Retrofit getApiDiffClient()
    {
        if(diffRetrofit==null)
        {
            diffRetrofit = new Retrofit.Builder().baseUrl(BASE_DIFF_URL).
                    addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return diffRetrofit;
    }
}
