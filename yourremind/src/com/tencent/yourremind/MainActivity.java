package com.tencent.yourremind;



import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SherlockActivity implements ActionBar.TabListener,ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private ActionBar actionBar;
    private List<View> viewList;
    private MainPageAdapter viewPagerAdapter;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_navigation);
        mViewPager = (ViewPager)findViewById(R.id.view_pager);

        actionBar = getSupportActionBar();

        getSupportActionBar().setNavigationMode(com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_TABS);
        getSupportActionBar().setTitle(R.string.app_name);

        ActionBar.Tab tab1 = actionBar.newTab().setText(R.string.tab1).setTabListener(this);
        actionBar.addTab(tab1);

        ActionBar.Tab tab2 = actionBar.newTab().setText(R.string.tab2).setTabListener(this);
        actionBar.addTab(tab2);

        ActionBar.Tab tab3 = actionBar.newTab().setText(R.string.tab3).setTabListener(this);
        actionBar.addTab(tab1);

        viewList = new ArrayList<View>();
        View view1 = View.inflate(this,R.layout.tab1,null);
        View view2 = View.inflate(this,R.layout.tab2,null);
        View view3 = View.inflate(this,R.layout.tab3,null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        viewPagerAdapter = new MainPageAdapter();
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu sub = menu.addSubMenu("Theme");
        sub.add(0, R.style.Theme_Sherlock, 0, R.string.add_contact_people);
        sub.add(0, R.style.Theme_Sherlock_Light, 0, R.string.add_common_software);
        sub.add(0, R.style.Theme_Sherlock_Light_DarkActionBar, 0, R.string.setting);
        sub.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction transaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction transaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction transaction) {
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    public class MainPageAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = viewList.get(position);
            mViewPager.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }
}

