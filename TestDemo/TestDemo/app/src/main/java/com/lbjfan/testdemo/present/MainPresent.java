package com.lbjfan.testdemo.present;

import com.lbjfan.testdemo.api.IRequestClient;
import com.lbjfan.testdemo.contract.MainContract;
import com.lbjfan.testdemo.manager.AppManager;
import com.lbjfan.testdemo.model.CanadaInfo;

import java.util.List;

/**
 * Created by ${lbjfan} on 17-2-28.
 */
public class MainPresent implements MainContract.MainPresent {

    MainContract.MainView mainView;

    public MainPresent(MainContract.MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void getCanadaInfos() {
        AppManager.getInstance().getRequestClient().getTestListInfo(new IRequestClient.RequestListener() {
            @Override
            public void onRequestSucceed(Object object) {
                mainView.showCanadaInfo((List<CanadaInfo>) object);
            }

            @Override
            public void onRequestFailed(int strType, String strMsg) {

            }
        });
    }
}
