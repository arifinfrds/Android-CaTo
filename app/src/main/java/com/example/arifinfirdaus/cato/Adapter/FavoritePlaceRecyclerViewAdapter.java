package com.example.arifinfirdaus.cato.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arifinfirdaus.cato.FavoritePlaceDetailActivity;
import com.example.arifinfirdaus.cato.FavoritePlaceDetailFragment;
import com.example.arifinfirdaus.cato.R;
import com.example.arifinfirdaus.cato.dummy.DummyContent;

import java.util.List;

/**
 * Created by arifinfirdaus on 3/31/17.
 */

public class FavoritePlaceRecyclerViewAdapter
        extends RecyclerView.Adapter<FavoritePlaceRecyclerViewAdapter.ViewHolder> {

    private final List<DummyContent.DummyItem> mValues;
    private FragmentManager fragmentManager;
    private boolean twoPane;

    public FavoritePlaceRecyclerViewAdapter(List<DummyContent.DummyItem> items, FragmentManager fragmentManager, boolean twoPane) {
        mValues = items;
        this.fragmentManager = fragmentManager;
        this.twoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_place_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (twoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(FavoritePlaceDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                    FavoritePlaceDetailFragment fragment = new FavoritePlaceDetailFragment();
                    fragment.setArguments(arguments);

                    if (fragmentManager != null) {
                        fragmentManager.beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    }
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, FavoritePlaceDetailActivity.class);
                    intent.putExtra(FavoritePlaceDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyContent.DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}