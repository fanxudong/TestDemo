package com.lbjfan.testdemo.api.impl;

import com.lbjfan.testdemo.model.CanadaInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lbjfan} on 16-8-23.
 */
public class RequestHandler {

    public Object combineObject(int urlType, Object category) {
        switch (urlType) {
            case 0:
                category = parseCanadaInfos((JSONObject) category);
                break;
            default:
                break;
        }
        return category;
    }

    private List<CanadaInfo> parseCanadaInfos(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.optJSONArray("rows");
        int length = jsonArray.length();
        if (null != jsonArray && length > 0) {
            List<CanadaInfo> canadaInfoList = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                CanadaInfo canadaInfo = new CanadaInfo(jsonObject1);
                canadaInfoList.add(canadaInfo);
            }
            return canadaInfoList;
        }
        return null;
    }
}
