package com.practiceapps.donal.rottentomato;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.practiceapps.donal.rottentomato.adapters.ViewPagerAdapter;
import com.practiceapps.donal.rottentomato.tabs.SlidingTabLayout;


public class MainActivity extends ActionBarActivity {

    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.sliding_tabs_pager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext()));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        // setCustomTabColorizer provides interface so override it inline
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
//                return 0;
                return getResources().getColor(R.color.accent);
            }
        });
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.primary));
//        mSlidingTabLayout.setCustomTabView(R.layout.tab_text, R.id.tab_textview);
        // once init / customisatino is done call setViewPager
        mSlidingTabLayout.setViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
