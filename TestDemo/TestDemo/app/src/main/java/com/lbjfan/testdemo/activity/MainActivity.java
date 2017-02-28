package com.lbjfan.testdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.lbjfan.testdemo.R;
import com.lbjfan.testdemo.adapter.CanadaAdapter;
import com.lbjfan.testdemo.contract.MainContract;
import com.lbjfan.testdemo.model.CanadaInfo;
import com.lbjfan.testdemo.present.MainPresent;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    public static final String TAG = MainActivity.class.getSimpleName();

    private ListView listView;
    private CanadaAdapter canadaAdapter;
    private MainPresent mainPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {
        listView = (ListView) findViewById(android.R.id.list);
        canadaAdapter = new CanadaAdapter(this);
        listView.setAdapter(canadaAdapter);
        mainPresent = new MainPresent(this);
    }

    @Override
    public void initData() {
        mainPresent.getCanadaInfos();
    }

    @Override
    public void showCanadaInfo(final List<CanadaInfo> canadaInfoList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                canadaAdapter.setCanadaInfoList(canadaInfoList);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mainPresent = null;
        super.onDestroy();
    }
}
