package edu.uit.MusicPlayer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.uit.MusicPlayer.Activity.PlayNhacActivity;
import edu.uit.MusicPlayer.Model.Baihat;
import edu.uit.MusicPlayer.R;
import edu.uit.MusicPlayer.Service.APIService;
import edu.uit.MusicPlayer.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;

    public SearchBaiHatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.dong_search_bai_hat,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat=mangbaihat.get(position);
        holder.txtTenbaihat.setText(baihat.getTenbaihat());
        holder.txtCasi.setText(baihat.getCasi());
        Picasso.with(context).load(baihat.getHinhbaihat()).into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenbaihat,txtCasi;
        ImageView imgbaihat,imgluotthich;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtTenbaihat=itemView.findViewById(R.id.textviewsearchtenbaihat);
            txtCasi=itemView.findViewById(R.id.textviewsearchtencasi);
            imgbaihat=itemView.findViewById(R.id.imageSearchbaihat);
            imgluotthich=itemView.findViewById(R.id.imageviewSearchluotthich);

            // bat su kien khi click o man hinh tim kiem
            //bai hat se dc phat
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, PlayNhacActivity.class); // chuyen man hinh sang PlayNahc
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice= APIService.getService();
                    Call<String> callback=dataservice.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua =response.body();
                            if(ketqua.equals("OK")){
                                Toast.makeText(context, "Da thich", Toast.LENGTH_SHORT).show();
                            }else
                            {
                                Toast.makeText(context, "Loi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}
