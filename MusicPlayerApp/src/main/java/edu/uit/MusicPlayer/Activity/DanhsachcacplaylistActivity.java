package edu.uit.MusicPlayer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.uit.MusicPlayer.Adapter.DanhsachcacplaylistAdapter;
import edu.uit.MusicPlayer.Model.Playlist;
import edu.uit.MusicPlayer.R;
import edu.uit.MusicPlayer.Service.APIService;
import edu.uit.MusicPlayer.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacplaylistActivity extends AppCompatActivity {
    
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachcacplaylist;

    DanhsachcacplaylistAdapter danhsachcacplaylistAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);

        anhxa();
        init();
        
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Playlist>> callback=dataservice.GetDanhsachcacPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
               // Log.d("BBB",mangplaylist.get(0).getTen());
                danhsachcacplaylistAdapter =new DanhsachcacplaylistAdapter(DanhsachcacplaylistActivity.this,mangplaylist);
                recyclerViewdanhsachcacplaylist.setLayoutManager(new GridLayoutManager(DanhsachcacplaylistActivity.this,2));
                recyclerViewdanhsachcacplaylist.setAdapter(danhsachcacplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbar=findViewById(R.id.toolbardanhsachcacplaylist);
        recyclerViewdanhsachcacplaylist=findViewById(R.id.recyclerviewdanhsachcacplaylist);
    }
}
