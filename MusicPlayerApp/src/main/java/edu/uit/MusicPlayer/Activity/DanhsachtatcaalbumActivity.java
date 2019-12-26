package edu.uit.MusicPlayer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.uit.MusicPlayer.Adapter.AllAlbumAdapter;
import edu.uit.MusicPlayer.Model.Album;
import edu.uit.MusicPlayer.R;
import edu.uit.MusicPlayer.Service.APIService;
import edu.uit.MusicPlayer.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaalbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllalbum;
    Toolbar toolbaralbum;

    AllAlbumAdapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcaalbum);
        init();

        //Get Data tra ve tu Server
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Album>> callback=dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum= (ArrayList<Album>) response.body();
               // Log.d("BBB",mangalbum.get(0).getTenAlbum());
                allAlbumAdapter=new AllAlbumAdapter(DanhsachtatcaalbumActivity.this,mangalbum);
                recyclerViewAllalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaalbumActivity.this,2));
                recyclerViewAllalbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllalbum=findViewById(R.id.recyclerviewAllalbum);
        toolbaralbum=findViewById(R.id.toolbarAllalbum);

        setSupportActionBar(toolbaralbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbaralbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
