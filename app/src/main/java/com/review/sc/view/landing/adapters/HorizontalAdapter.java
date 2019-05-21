package com.review.sc.view.landing.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.review.sc.R;
import com.review.sc.data.model.Track;
import com.review.sc.interfaces.ItemTapController;
import com.review.sc.util.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ItemViewHolder> {

    public static final String TAG = HorizontalAdapter.class.getSimpleName();


    private Context mContext;
    private List<Track> mDataSet;
    private ItemTapController itemTapController;

    public HorizontalAdapter(Context context, List<Track> tracks, ItemTapController bottomSheetController) {
        this.mContext = context;
        this.mDataSet = tracks;
        this.itemTapController = bottomSheetController;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_item, viewGroup, false);
        return new ItemViewHolder(v);
    }


    @SuppressLint("HandlerLeak")
    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder viewHolder, final int position) {

        viewHolder.rlHorizontalItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemTapController.showDetails(mDataSet.get(position));
            }
        });

        if(null != mDataSet.get(position).getArtwork_url()) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            GlideApp.with(mContext)
                    .load(mDataSet.get(position).getArtwork_url().replace("large", "t500x500"))
                    .transforms(new CenterCrop(), new RoundedCorners(40))
                    .into(viewHolder.ivArtwork);
        } else {
            viewHolder.ivArtwork.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_empty));
        }

        viewHolder.tvTitle.setText(mDataSet.get(position).getTitle());
        viewHolder.tvSubTitle.setText(mDataSet.get(position).getUser().getUsername());
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rlHorizontalItem)
        LinearLayout rlHorizontalItem;

        @BindView(R.id.ivArtwork)
        ImageView ivArtwork;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvSubTitle)
        TextView tvSubTitle;


        ItemViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }


    }
}
