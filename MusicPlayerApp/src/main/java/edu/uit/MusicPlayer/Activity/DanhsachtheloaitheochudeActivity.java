package edu.uit.MusicPlayer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import edu.uit.MusicPlayer.Adapter.DanhsachtheloaitheochudeAdapter;
import edu.uit.MusicPlayer.Model.ChuDe;
import edu.uit.MusicPlayer.Model.TheLoai;
import edu.uit.MusicPlayer.R;
import edu.uit.MusicPlayer.Service.APIService;
import edu.uit.MusicPlayer.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {
    ChuDe chuDe;

    RecyclerView recyclerViewtheloaithoechude;
    Toolbar toolbartheloaitheochude;

    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);

        GetIntent();
        init();
        
        GetData();

    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<TheLoai>> callback=dataservice.GetTheloaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai= (ArrayList<TheLoai>) response.body();
               // Log.d("BBB",mangtheloai.get(0).getTenTheLoai());
               danhsachtheloaitheochudeAdapter=new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this,mangtheloai);
               recyclerViewtheloaithoechude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this,2));
               recyclerViewtheloaithoechude.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtheloaithoechude=findViewById(R.id.recyclerviewtheloaitheochude);
        toolbartheloaitheochude=findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("chude")){
            chuDe= (ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this,chuDe.getTenChuDe(), Toast.LENGTH_SHORT).show();
        }
    }
}
