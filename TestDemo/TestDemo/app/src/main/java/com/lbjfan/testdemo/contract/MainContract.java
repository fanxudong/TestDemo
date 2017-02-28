package com.lbjfan.testdemo.contract;

import com.lbjfan.testdemo.model.CanadaInfo;

import java.util.List;

/**
 * Created by ${lbjfan} on 17-2-28.
 */
public class MainContract {

    public interface MainView {

        void initWidget();

        void initData();

        void showCanadaInfo(List<CanadaInfo> canadaInfoList);
    }

    public interface MainPresent {
        void getCanadaInfos();
    }
}
