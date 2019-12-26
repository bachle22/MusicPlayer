package edu.uit.MusicPlayer.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import edu.uit.MusicPlayer.Model.Mv;
import edu.uit.MusicPlayer.R;

import java.util.List;

public class PlayMvActivity extends AppCompatActivity {

    VideoView videoView;
    Mv mv;
    List<Mv> mangmv;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_mv);

        videoView =  findViewById(R.id.videoView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DataIntent();
        //getSupportActionBar().hide();
        playerVideo();




    }

    private void playerVideo() {
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        Uri uri = Uri.parse(mv.getLinkMv());
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        //videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
    }

    private void DataIntent() {
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("itemmv")){
                mv= (Mv) intent.getSerializableExtra("itemmv");
                //Toast.makeText(this, quangcao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
