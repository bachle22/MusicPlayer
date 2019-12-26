package edu.uit.MusicPlayer.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.uit.MusicPlayer.Activity.PlayMvActivity;
import edu.uit.MusicPlayer.Adapter.MvAdapter;
import edu.uit.MusicPlayer.Model.Mv;
import edu.uit.MusicPlayer.R;
import edu.uit.MusicPlayer.Service.APIService;
import edu.uit.MusicPlayer.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Mv extends Fragment {
    View view;
    ListView lvmv;
    TextView txttitlemv;
    ArrayList<Mv> mangmv;
    MvAdapter mvadapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mv,container,false);
        lvmv =view.findViewById(R.id.listviewmv);
        txttitlemv=view.findViewById(R.id.textviewtitlemv);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Mv>> callback=dataservice.GetMv();
        callback.enqueue(new Callback<List<Mv>>() {
            @Override
            public void onResponse(Call<List<Mv>> call, Response<List<Mv>> response) {
                mangmv= (ArrayList<Mv>) response.body();
                //Log.d("BBB",mangplaylist.get(0).getTen());
                mvadapter =new MvAdapter(getActivity(),android.R.layout.simple_list_item_1,mangmv);
                lvmv.setAdapter(mvadapter);
                setListViewHeightBasedOnChildren(lvmv);

                lvmv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getActivity(), PlayMvActivity.class);
                        intent.putExtra("itemmv",mangmv.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Mv>> call, Throwable t) {

            }
        });
    }
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
