package com.boratsinc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.RatSighting;

import java.util.List;

public class RatSightingsListView extends AppCompatActivity {
    // --Commented out by Inspection (11/16/2017 10:30 PM):private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Model.getInstance().loadData();
        setContentView(R.layout.activity_rat_sightings_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context con = view.getContext();
                Intent intent = new Intent(con, AddRatSighting.class);
                con.startActivity(intent);
            }
        });


        RecyclerView mRecyclerView = findViewById(R.id.rat_sightings_list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<RatSighting> modelList = Model.getInstance().getSightings();
        RecyclerView.Adapter mAdapter = new MyAdapter(modelList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter
            extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<RatSighting> mSightingList;

        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        public MyAdapter(List<RatSighting> items) {
            mSightingList = items;
            Model.getInstance().setAdapter(this);
        }

        public void updateList(List<RatSighting> t) {
            mSightingList.clear();
            for(RatSighting rat: t) {
                mSightingList.add(rat);
            }
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/rat_sighting_list_content.xml
              If you look at the example file, you will see it's currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rat_sighting_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Model model = Model.getInstance();
            holder.mSighting = mSightingList.get(position);
            holder.mIdView.setText(mSightingList.get(position).getKey());
            //holder.mContentView.setText(mSightingList.get(position).toString());
            holder.mContentView.setText("");

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, RatSightingDetailActivity.class);

                        model.setCurrentSighting(holder.mSighting);

                        context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mSightingList.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a RatSighting) and the widgets in
         * the list view (in this case the 2 TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public RatSighting mSighting;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = view.findViewById(R.id.id);
                mContentView = view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
