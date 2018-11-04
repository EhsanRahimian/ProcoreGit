package com.nicootech.procoregit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.nicootech.procoregit.Interface.ApiDiff;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiffActivity extends AppCompatActivity {
    private RecyclerView diff_recycler_view;
    private String intentDiff;
    private ApiDiffClient apiDiffClient;
    private ApiDiff apiDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff);
        diff_recycler_view = findViewById(R.id.diff_recycler_view);

        Intent intent = getIntent();
        if(intent.getExtras()!=null)
            intentDiff = intent.getExtras().getString("diffUrlFromIntent");

        apiDiffClient = (ApiDiffClient) ApiDiffClient.getApiDiffClient().create(ApiDiff.class);
        apiDiff = (ApiDiff) ApiDiffClient.getApiDiffClient().create(ApiDiffClient.class);

        Call<String> callDiff =apiDiff.getStringResponse(intentDiff);
        callDiff.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String responseString =  response.body();
                    if(responseString !=null)
                        setRecyclerView(stringProcess(responseString));
            }


        }

            @Override
            public void onFailure(Call<String> call, Throwable t) {}

            });

    }
        private List<String> stringProcess(String responseString) {
            String[] parts = responseString.trim().split("diff---git");
            return Arrays.asList(parts);
        }
        private void setRecyclerView(List<String> diffList) {
            RecyclerView.Adapter diffAdapter;
            RecyclerView.LayoutManager layoutManager;

            diffAdapter = new DiffAdapter(getApplicationContext(),diffList);
            layoutManager = new LinearLayoutManager(this);

            diff_recycler_view.setLayoutManager(layoutManager);
            diff_recycler_view.setHasFixedSize(true);

            diff_recycler_view.setAdapter(diffAdapter);



        }

}
