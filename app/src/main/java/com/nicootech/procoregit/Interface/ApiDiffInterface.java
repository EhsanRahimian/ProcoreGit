package com.nicootech.procoregit.Interface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiDiffInterface {
    @GET("mgp25/Instagram-API/pull/{repoStr}")
    Call<String> getStringResponse
            (
            @Path("repoStr") String repoPath
    );
}
