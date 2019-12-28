package com.nambry.android.mycustomlist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // 以下、データ作成
        List<Map<String, Object>> data = new ArrayList<>();
        String[] names = {"Android", "山田太郎", "にこるん"};
        String[] descriptions = {"アンドロメダ星から地球侵略にやってきたAI型ロボット。", "日本一ありふれた名前を持つ少年。", "出身はフロリダです。"};
        int[] icons = {R.drawable.ic_android_black_24dp, R.drawable.ic_face_black_24dp, R.drawable.ic_tag_faces_black_24dp};
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", names[i]);
            item.put("description", descriptions[i]);
            item.put("icon", icons[i]);
            data.add(item);
        }

        ListView lv = view.findViewById(R.id.lvRow);
        final String[] FROM = {"name", "description", "icon"}; // FROMとTOの組み合わせでMap内のどのデータをListView各行のどの部品に割り当てるかを指定できる
        final int[] TO = {R.id.name, R.id.description, R.id.icon};
        SimpleAdapter adapter = new SimpleAdapter(this.getContext(), data, R.layout.row, FROM, TO);// 自作したrow.xmlを指定
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
                makeToast(item.get("name").toString());
            }
        });
        lv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }


    public void makeToast(String name) {
        Toast toast = Toast.makeText(this.getContext(), name, Toast.LENGTH_SHORT);
        toast.show();
    }
}
