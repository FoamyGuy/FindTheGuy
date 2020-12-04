/**
 * Copyright 2014 Magnus Woxblom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.foamyguy.findtheguy;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    TextView scoreTxt;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setItemAnimator(null);




        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this, 5);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        ArrayList<String> guys = new ArrayList<String>();
        Random rnd = new Random();
        int winner = rnd.nextInt(50);

        for (int i = 0; i < 50; i++){
            if (i != winner){
                guys.add(Types.TYPE_DUDD);
            }else{
                guys.add(Types.TYPE_GOOD);
            }

        }
        scoreTxt = new TextView(this);

        mAdapter = new GridItemAdapter(guys, scoreTxt);
        mRecyclerView.setAdapter(mAdapter);



        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.app_color)));
    }
    @Override
    public void onStop(){
        super.onStop();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);


        scoreTxt.setText("Score:");
        scoreTxt.setTextColor(Color.WHITE);
        //tv.setOnClickListener(this);
        scoreTxt.setPadding(5, 0, 5, 0);

        scoreTxt.setTextSize(18);
        menu.add(0, 0, 1, "Score").setActionView(scoreTxt).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }

        return super.onOptionsItemSelected(item);
    }
}
