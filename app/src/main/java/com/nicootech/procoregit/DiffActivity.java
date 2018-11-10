package com.nicootech.procoregit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nicootech.procoregit.Interface.ApiDiffInterface;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiffActivity extends AppCompatActivity {
    //private RecyclerView diff_recycler;
    private FrameLayout leftText;
    private FrameLayout rightText;
    private ApiDiffInterface apiDiff;
    private String intentDiff;
    private static final int TYPE_DEFAULT = 0;
    private static final int TYPE_REMOVE = 1;
    private static final int TYPE_ADD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff);
        //diff_recycler=findViewById(R.id.diff_recycler_view);
        rightText = findViewById(R.id.list_diff_right);
        leftText = findViewById(R.id.list_diff_left);

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

                    String responseString = response.body();
                    getLinesForView(true, responseString);
                    getLinesForView(false, responseString);
                }

            }

            @Override
            public void onFailure(Call<String> diffCall, Throwable t) {

            }

        });

    }
    private void getLinesForView(boolean left, String result) {
        if (result == null) return;
        String[] lines = result.split("\n");
        if (lines.length > 0) {
            for (String line : lines) {
                int type = TYPE_DEFAULT;
                if (line.trim().startsWith("+")) {
                    type = TYPE_ADD;
                } else if (line.trim().startsWith("-")) {
                    type = TYPE_REMOVE;
                }


            }
        }
    }
    private TextView addLineAsTextView(int type, String line) {

        TextView tv = new TextView(this);
        tv.setText(line);
        tv.setPadding(10, 0, 0, 10);

        switch (type) {
            case TYPE_ADD:
                tv.setBackgroundResource(R.color.colorAccent);
                break;
            case TYPE_REMOVE:
                tv.setBackgroundResource(R.color.colorPrimary);
                break;
            case TYPE_DEFAULT:
                break;
        }
        return tv;
    }

}
