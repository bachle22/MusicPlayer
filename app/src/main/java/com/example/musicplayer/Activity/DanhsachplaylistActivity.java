package com.example.musicplayer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.musicplayer.Adapter.DanhsachplaylistAdapter;
import com.example.musicplayer.Model.Playlist;
import com.example.musicplayer.R;
import com.example.musicplayer.Service.APIService;
import com.example.musicplayer.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachplaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachplaylist;
    DanhsachplaylistAdapter danhsachplaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachplaylist);
        anhxa();
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetDanhsachplaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                danhsachplaylistAdapter = new DanhsachplaylistAdapter(DanhsachplaylistActivity.this,mangplaylist);
                recyclerViewdanhsachplaylist.setLayoutManager(new GridLayoutManager(DanhsachplaylistActivity.this,2));
                recyclerViewdanhsachplaylist.setAdapter(danhsachplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbardanhsachplaylist);
        recyclerViewdanhsachplaylist = findViewById(R.id.recyclerviewdanhsachplaylist);
    }
    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Play Lists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
