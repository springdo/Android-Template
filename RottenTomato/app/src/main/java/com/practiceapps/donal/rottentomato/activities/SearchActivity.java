package com.practiceapps.donal.rottentomato.activities;

import android.content.Context;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.practiceapps.donal.rottentomato.R;
import com.practiceapps.donal.rottentomato.adapters.InTheatresAdapter;
import com.practiceapps.donal.rottentomato.bus.BusProvider;
import com.practiceapps.donal.rottentomato.events.DataLoadEvent;
import com.practiceapps.donal.rottentomato.events.DataLoadedEvent;
import com.practiceapps.donal.rottentomato.events.SearchDataEvent;
import com.practiceapps.donal.rottentomato.events.SearchDataLoadedEvent;
import com.practiceapps.donal.rottentomato.fragments.SearchFragment;
import com.practiceapps.donal.rottentomato.logging.L;
import com.practiceapps.donal.rottentomato.pojo.MyMovies.*;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import com.practiceapps.donal.rottentomato.fragments.*;

import java.util.ArrayList;

public class SearchActivity extends ActionBarActivity {

    private SearchView mSearchView;
    private Bus mBus = BusProvider.getInstance();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private InTheatresAdapter mInTheatresAdapter;
    private ArrayList<Movie> mMovieArrayList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // used to customise the toolbar and enable home features
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mRecyclerView = (RecyclerView) findViewById(R.id.search_recycler);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // init and set data on the adaper
        mInTheatresAdapter = new InTheatresAdapter(this, mMovieArrayList);
        L.mV(this, "onCreateView data list :: "+String.valueOf(mMovieArrayList));
        mInTheatresAdapter.setMovieArrayList(mMovieArrayList);
        mRecyclerView.setAdapter(mInTheatresAdapter);

//          FRAGMENT NEVER LOADED DATA?!
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frag_container, new SearchFragment())
//                    .commit();
//        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        //get ahold of the search bar object
        MenuItem searchItem =  menu.findItem(R.id.action_search_bar);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setQueryHint("Search movies");
        mSearchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

//        Doesn't detect softkeyboard go button
//        mSearchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                L.tS(getApplicationContext(), "searchView query :: "+mSearchView.getQuery());
//            }
//        });
        // add listeners and keyboards stuff to it
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                L.tS(getApplicationContext(), "searchView query :: "+s);
                // not sure about this location....
                mBus.post(new SearchDataEvent(s));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void showInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        mBus.unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.register(this);
//        mBus.post(new SearchDataEvent());
    }

//    //TODO  move to fragment
//    @Subscribe
//    public void onSearchDataLoadedEvent(SearchDataLoadedEvent event){
//        L.mV(this, "Search Movies data total data :: " + event.getmSearchMovies().getTotal());
//        L.tS(this, "First Movie :: "+event.getmSearchMovies().getMovies().get(0).getTitle());
//    }


    @Subscribe
    public void onSearchDataLoadedEvent(SearchDataLoadedEvent event){
        L.mV(this, "Search Movies data total data :: " + event.getmSearchMovies().getTotal());
        L.tS(this, "First Movie :: "+event.getmSearchMovies().getMovies().get(0).getTitle());
        L.mV(this, "Movies from search data total data :: " + event.getmSearchMovies().getTotal());
//        mInTheatresAdapter.setMovieArrayList(event.getmMoviesInTheatre().getMovies());
        mMovieArrayList = new ArrayList<>(event.getmSearchMovies().getMovies());
        mInTheatresAdapter.setMovieArrayList(mMovieArrayList);
        L.mV(this, "onSearchDataLoadedEvent movieArrayList :: "+ String.valueOf(mMovieArrayList));
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
        if (id == R.id.action_search_bar){
            L.tS(getApplicationContext(), "searchView item select");
//            showInputMethod(mSearchView);
        }

        return super.onOptionsItemSelected(item);
    }
}
