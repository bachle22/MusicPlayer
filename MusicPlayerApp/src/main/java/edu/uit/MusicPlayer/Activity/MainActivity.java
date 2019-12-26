package edu.uit.MusicPlayer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import edu.uit.MusicPlayer.Adapter.MainViewPagerAdapter;
import edu.uit.MusicPlayer.Fragment.Fragment_Offline;
import edu.uit.MusicPlayer.Fragment.Fragment_Tim_Kiem;
import edu.uit.MusicPlayer.Fragment.Fragment_Trang_Chu;
import edu.uit.MusicPlayer.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }
    private void init()
    {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "Trang chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(),"Tìm kiếm");

        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);

    }
    private void anhxa()
    {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);

    }


}
