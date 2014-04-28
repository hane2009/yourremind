package com.tencent.yourremind;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.tencent.yourremind.ui.TabFirstFragment;
import com.tencent.yourremind.ui.TabSecondFragment;
import com.tencent.yourremind.ui.TabThreeFragment;

import com.tencent.yourremind.util.SettingUtil;
import com.viewpagerindicator.TabPageIndicator;
import java.util.ArrayList;


public class YourremindActivity extends SherlockFragmentActivity {

    private static final String[] CONTENT = new String[]{"tab1", "tab2", "tab3"};

    private ViewPager viewPager;

    private TabPageIndicator pageIndicator;

    //three views
    private ArrayList<Fragment> views = new ArrayList<Fragment>();

    //Fragment
    TabFirstFragment tabFirstFragment;
    TabSecondFragment tabSecondFragment;
    TabThreeFragment tabThreeFragment;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_navigation);

        getSupportActionBar().setIcon(R.drawable.ab_icon);

        pageIndicator = (TabPageIndicator)findViewById(R.id.pageIndicator);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabFirstFragment = new TabFirstFragment();
        tabSecondFragment = new TabSecondFragment();
        tabThreeFragment = new TabThreeFragment();

        views.add(tabFirstFragment);
        views.add(tabSecondFragment);
        views.add(tabThreeFragment);

        FragmentPagerAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        pageIndicator.setViewPager(viewPager);
        pageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.action_exit:{
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage(SettingUtil.getUsername(getBaseContext())+ " 您确定要退出吗？")
                        .setPositiveButton("退出",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                })
                        .setNegativeButton("注销",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        SettingUtil.setIsUserLogin(getBaseContext(),false);
                                        Intent intent = new Intent(YourremindActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                    }
                                }).create().show();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    class FragmentAdapter extends FragmentPagerAdapter{

        public FragmentAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length];
        }

        @Override
        public Fragment getItem(int i) {
            return views.get(i);
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }
}

