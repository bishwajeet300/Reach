package com.review.sc.view.landing.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.review.sc.R;
import com.review.sc.data.model.Category;
import com.review.sc.interfaces.ItemTapController;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParentRecyclerAdapter extends RecyclerView.Adapter<ParentRecyclerAdapter.ParentItemViewHolder> {

    private Context mContext;
    private LinkedList<Category> mDataSet;
    private HorizontalAdapter horizontalAdapter;
    ItemTapController itemTapController;

    public ParentRecyclerAdapter(Context mContext, LinkedList<Category> mDataSet, ItemTapController bottomSheetController) {
        this.mContext = mContext;
        this.mDataSet = mDataSet;
        this.itemTapController = bottomSheetController;
    }


    @NonNull
    @Override
    public ParentItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        return new ParentItemViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final ParentItemViewHolder parentItemViewHolder, int i) {
        parentItemViewHolder.tvHeading.setText(mDataSet.get(i).getHeading());
        parentItemViewHolder.tvSubHeading.setText(mDataSet.get(i).getSubHeading());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        parentItemViewHolder.rvDateSet.setLayoutManager(linearLayoutManager);

        parentItemViewHolder.tvSubHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentItemViewHolder.rvDateSet.smoothScrollToPosition(0);
            }
        });

        parentItemViewHolder.rvDateSet.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == 2) {
                    parentItemViewHolder.tvSubHeading.setVisibility(View.VISIBLE);
                }
            }
        });

        horizontalAdapter = new HorizontalAdapter(mContext, mDataSet.get(i).getTracks(), itemTapController);
        parentItemViewHolder.rvDateSet.setHasFixedSize(true);
        parentItemViewHolder.rvDateSet.setNestedScrollingEnabled(false);
        parentItemViewHolder.rvDateSet.setAdapter(horizontalAdapter);
        horizontalAdapter.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public void notifyDataHasSetChanged(LinkedList<Category> tracks) {

        if(null == mDataSet) {
            mDataSet = tracks;
        } else {
            mDataSet.clear();
            mDataSet.addAll(tracks);
        }
        notifyDataSetChanged();
    }


    public class ParentItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rvDateSet)
        RecyclerView rvDateSet;

        @BindView(R.id.tvHeading)
        TextView tvHeading;

        @BindView(R.id.tvSubHeading)
        TextView tvSubHeading;


        public ParentItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
