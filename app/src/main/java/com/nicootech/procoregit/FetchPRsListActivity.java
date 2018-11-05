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


public class FetchPRsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ApiPRs apiPRs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_prs_list);

        recyclerView = findViewById(R.id.my_recycler_view);


        apiPRs = ApiClient.getApiClient().create(ApiPRs.class);

        Call<List<Model>> call = apiPRs.getPRsList();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {

                List<Model>models = response.body();
                setRecyclerView(models);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });

    }
    private void setRecyclerView(List<Model>models)
    {
        RecyclerView.Adapter adapter;
        RecyclerView.LayoutManager layoutManager;

        adapter = new PRsAdapter(getApplicationContext(), models);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

    }
}
