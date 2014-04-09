package com.tencent.yourremind.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.tencent.yourremind.R;

/**
 * Created by sidyang on 14-4-9.
 */
public class TabThreeFragment extends SherlockFragment{

    private View contentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView =  super.onCreateView(inflater, container, savedInstanceState);
        if(contentView == null ){
            contentView = inflater.inflate(R.layout.tab3,container,false);
        }
        return contentView;
    }
}
