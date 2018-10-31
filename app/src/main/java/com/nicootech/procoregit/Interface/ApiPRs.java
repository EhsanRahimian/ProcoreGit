package com.nicootech.procoregit.Interface;

import java.util.List;
import com.nicootech.procoregit.Model;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiPRs {

    @GET("pulls")
    Call<List<Model>> getPRsList();
}
