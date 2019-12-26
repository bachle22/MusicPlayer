package edu.uit.MusicPlayer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import edu.uit.MusicPlayer.Adapter.DanhsachtatcachudeAdapter;
import edu.uit.MusicPlayer.Model.ChuDe;
import edu.uit.MusicPlayer.R;
import edu.uit.MusicPlayer.Service.APIService;
import edu.uit.MusicPlayer.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcachudeActivity extends AppCompatActivity {

    RecyclerView recyclerViewtatcachude;
    Toolbar toolbartatcachude;

    DanhsachtatcachudeAdapter danhsachtatcachudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);
        init();
        
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<ChuDe>> callback=dataservice.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude= (ArrayList<ChuDe>) response.body();
                //Log.d("BBB",mangchude.get(0).getTenChuDe());
                danhsachtatcachudeAdapter=new DanhsachtatcachudeAdapter(DanhsachtatcachudeActivity.this,mangchude);
                recyclerViewtatcachude.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this,1));
                recyclerViewtatcachude.setAdapter(danhsachtatcachudeAdapter);


            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtatcachude=findViewById(R.id.recyclerviewAllChude);
        toolbartatcachude=findViewById(R.id.toolbarallchude);
        setSupportActionBar(toolbartatcachude);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbartatcachude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
