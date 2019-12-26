package edu.uit.MusicPlayer.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import edu.uit.MusicPlayer.Activity.DanhsachbaihatActivity;
import edu.uit.MusicPlayer.Activity.DanhsachtatcachudeActivity;
import edu.uit.MusicPlayer.Activity.DanhsachtheloaitheochudeActivity;
import edu.uit.MusicPlayer.Model.ChuDe;
import edu.uit.MusicPlayer.Model.TheLoai;
import edu.uit.MusicPlayer.Model.Theloaitrongngay;
import edu.uit.MusicPlayer.R;
import edu.uit.MusicPlayer.Service.APIService;
import edu.uit.MusicPlayer.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_Today extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);
         horizontalScrollView =view.findViewById(R.id.horizontalscrollview);
         txtxemthemchudetheloai=view.findViewById(R.id.textviewxemthem);


         txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                 startActivity(intent);
             }
         });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<Theloaitrongngay> callback =dataservice.GetChuDeTheLoai();
        callback.enqueue(new Callback<Theloaitrongngay>() {
            @Override
            public void onResponse(Call<Theloaitrongngay> call, Response<Theloaitrongngay> response) {
                Theloaitrongngay theloaitrongngay=response.body();

                final ArrayList<ChuDe> chuDeArrayList=new ArrayList<>();
                chuDeArrayList.addAll(theloaitrongngay.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList=new ArrayList<>();
                theLoaiArrayList.addAll(theloaitrongngay.getTheLoai());

                LinearLayout linearLayout=new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layout=new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);
                for(int i=0;i<(chuDeArrayList.size());i++){
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView=new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chuDeArrayList.get(i).getHinhChuDe() !=null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).centerCrop().fit().into(imageView);

                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);


                    //bat su kien cho Ramdom ChuDe
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for(int j=0;j<(chuDeArrayList.size());j++){
                    CardView cardView=new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView=new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theLoaiArrayList.get(j).getHinhTheLoai() !=null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).centerCrop().fit().into(imageView);

                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    //bat su kien
                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {

            }
        });
    }
}
