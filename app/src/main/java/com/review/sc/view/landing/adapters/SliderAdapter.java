package com.review.sc.view.landing.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.CircularProgressDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.review.sc.R;
import com.review.sc.data.model.Track;
import com.review.sc.interfaces.ItemTapController;
import com.review.sc.util.GlideApp;

import java.util.LinkedList;
import java.util.List;

public class SliderAdapter extends PagerAdapter {


    public static final String TAG = SliderAdapter.class.getSimpleName();

    private List<Track> mDataSet;
    private LayoutInflater inflater;
    private Context mContext;
    private ItemTapController itemTapController;

    public SliderAdapter(Context context, List<Track> trackList) {
        this.mContext = context;
        this.mDataSet = trackList;
        this.itemTapController = (ItemTapController)context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getCount() {
        return mDataSet.size();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, final int position) {
        View imageLayout = inflater.inflate(R.layout.sliding_item, view, false);

        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.image);

        String imageURL = mDataSet.get(position).getArtwork_url();

        if(!TextUtils.isEmpty(imageURL)) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            int radius = mContext.getResources().getDimensionPixelSize(R.dimen.corner_radius);

            GlideApp.with(mContext)
                    .load(imageURL.replace("large", "t500x500"))
                    .centerCrop()
                    .transform(new RoundedCorners(radius))
                    .placeholder(circularProgressDrawable)
                    .error(R.mipmap.ic_launcher_round)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);

        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher_round));
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemTapController.showDetails(mDataSet.get(position));
            }
        });


        view.addView(imageLayout, 0);

        return imageLayout;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }


    @Override
    public Parcelable saveState() {
        return null;
    }


    public void notifyDataHasSetChanged(LinkedList<Track> favorites) {
        if(null == mDataSet) {
            mDataSet = favorites;
        } else {
            mDataSet.clear();
            mDataSet.addAll(favorites);
        }
        notifyDataSetChanged();
    }
}