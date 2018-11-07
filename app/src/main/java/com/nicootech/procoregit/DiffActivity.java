package com.nicootech.procoregit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.nicootech.procoregit.Interface.ApiDiffInterface;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiffActivity extends AppCompatActivity {
    private RecyclerView diff_recycler;
    private ApiDiffInterface apiDiff;
    private String intentDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff);
        diff_recycler=findViewById(R.id.diff_recycler_view);

        //making the request


        Intent intent = getIntent();

        if(intent.getExtras()!=null)
            intentDiff = intent.getExtras().getString("diffUrlFromIntent");

        apiDiff = ApiDiffClient.getApiDiffClient().create(ApiDiffInterface.class);

        Call<String> diffCall = apiDiff.getStringResponse(intentDiff);
        diffCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> diffCall, Response<String> response) {

                if(response.isSuccessful()){
                    String responseString= response.body();
                    if(responseString !=null)
                        setRecyclerView(stringProcess(responseString));
                }

            }

            @Override
            public void onFailure(Call<String> diffCall, Throwable t) {

            }

        });

    }
    private List<String> stringProcess(String responseString) {
        String[] parts = responseString.trim().split("diff --git");
        return Arrays.asList(parts);
    }
    private void setRecyclerView(List<String>list){

        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        adapter = new DiffAdapter(list);
        layoutManager = new LinearLayoutManager(this);

        diff_recycler.setLayoutManager(layoutManager);
        diff_recycler.setHasFixedSize(true);

        diff_recycler.setAdapter(adapter);
    }

}
