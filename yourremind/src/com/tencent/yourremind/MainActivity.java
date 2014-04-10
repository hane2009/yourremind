package com.tencent.yourremind;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.tencent.yourremind.ui.TabFirstFragment;
import com.tencent.yourremind.ui.TabSecondFragment;
import com.tencent.yourremind.ui.TabThreeFragment;

import com.viewpagerindicator.TabPageIndicator;
import java.util.ArrayList;


public class MainActivity extends SherlockFragmentActivity {

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

