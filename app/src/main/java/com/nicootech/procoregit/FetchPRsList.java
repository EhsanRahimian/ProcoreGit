package com.nicootech.procoregit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.nicootech.procoregit.Interface.ApiPRs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchPRsList extends AppCompatActivity {
    private TextView textView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PRsAdapter adapter;
    private List<Model> models;
    private ApiPRs apiPRs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_prs_list);

        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        apiPRs = ApiClient.getApiClient().create(ApiPRs.class);

        Call<List<Model>> call = apiPRs.getPRsList();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                models = response.body();
                adapter = new PRsAdapter(models);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });


    }
}
