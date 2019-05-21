package com.review.sc.view.landing.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.review.sc.R;
import com.review.sc.data.model.Track;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogRecyclerAdapter extends RecyclerView.Adapter<DialogRecyclerAdapter.ViewHolder> {

    private Activity mContext;
    private List<Track> mDataSet;
    private String mType;


    public DialogRecyclerAdapter(@NonNull Activity context, @NonNull List<Track> objects, String type) {
        this.mContext = context;
        this.mDataSet = objects;
        this.mType = type;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        switch(mType) {
            case "TRACK":
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_item, viewGroup, false);
                return new DialogTrackItemViewHolder(v);
            case "USER":
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_item, viewGroup, false);
                return new DialogUserItemViewHolder(v);
            case "GENRE":
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_item, viewGroup, false);
                return new DialogGenreItemViewHolder(v);
            default:
                v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_item, viewGroup, false);
                return new DialogUserItemViewHolder(v);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        switch(mType) {
            case "TRACK":
                DialogTrackItemViewHolder trackItemViewHolder = (DialogTrackItemViewHolder)viewHolder;
                trackItemViewHolder.tvTitle.setText(mDataSet.get(i).getTitle());
                trackItemViewHolder.tvSubtitle.setText(mDataSet.get(i).getUser().getUsername());
                trackItemViewHolder.ivPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mDataSet.get(i).getPermalink_url();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        mContext.startActivity(i);
                    }
                });
                break;
            case "USER":
                DialogUserItemViewHolder userViewHolder = (DialogUserItemViewHolder)viewHolder;
                userViewHolder.tvTitle.setText(mDataSet.get(i).getUser().getUsername());
                userViewHolder.tvSubtitle.setText(mDataSet.get(i).getFavoritings_count() + " likes");
                userViewHolder.ivPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = mDataSet.get(i).getUser().getPermalink_url();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        mContext.startActivity(i);
                    }
                });
                break;
            case "GENRE":
                DialogGenreItemViewHolder genreViewHolder = (DialogGenreItemViewHolder)viewHolder;
                genreViewHolder.tvTitle.setText(mDataSet.get(i).getGenre());
                genreViewHolder.tvSubtitle.setVisibility(View.GONE);
                genreViewHolder.ivPlay.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    //Custom ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }


    public class DialogUserItemViewHolder extends ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvSubtitle)
        TextView tvSubtitle;

        @BindView(R.id.ivPlay)
        ImageView ivPlay;


        public DialogUserItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }


    public class DialogGenreItemViewHolder extends ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvSubtitle)
        TextView tvSubtitle;

        @BindView(R.id.ivPlay)
        ImageView ivPlay;


        public DialogGenreItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }


    public class DialogTrackItemViewHolder extends ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvSubtitle)
        TextView tvSubtitle;

        @BindView(R.id.ivPlay)
        ImageView ivPlay;


        public DialogTrackItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
