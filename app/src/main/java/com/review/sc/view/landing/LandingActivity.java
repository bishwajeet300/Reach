package com.review.sc.view.landing;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.review.sc.R;
import com.review.sc.crawler.DefaultExecutorSupplier;
import com.review.sc.data.local.dao.TracksDAO;
import com.review.sc.data.model.Category;
import com.review.sc.data.model.FollowerResponse;
import com.review.sc.data.model.Track;
import com.review.sc.interfaces.ItemTapController;
import com.review.sc.util.BounceAnimation;
import com.review.sc.view.landing.adapters.DialogRecyclerAdapter;
import com.review.sc.view.landing.adapters.ParentRecyclerAdapter;
import com.review.sc.view.landing.adapters.SliderAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class LandingActivity extends AppCompatActivity implements ILandingContract.ILandingView, ItemTapController, View.OnClickListener {

    private static final String TAG = LandingActivity.class.getSimpleName();

    @Inject
    ILandingContract.ILandingPresenter mPresenter;

    @BindView(R.id.fullscreen_content)
    View mContentView;

    @BindView(R.id.nsvMainLayout)
    NestedScrollView nsvMainLayout;

    @BindView(R.id.rlLoader)
    RelativeLayout rlLoader;

    @BindView(R.id.vpTopContent)
    ViewPager vpTopContent;

    @BindView(R.id.tvOption1)
    TextView tvOption1;

    @BindView(R.id.tvOption2)
    TextView tvOption2;

    @BindView(R.id.tvOption3)
    TextView tvOption3;

    @BindView(R.id.tvOption4)
    TextView tvOption4;

    @BindView(R.id.rvParentRecyclerView)
    RecyclerView rvParentRecyclerView;


    ParentRecyclerAdapter parentRecyclerAdapter;
    SliderAdapter sliderAdapter;
    Thread networkThread;
    Handler networkHandler;
    ImageView ivArtwork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        initUI();
    }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(base));
    }


    private void initUI() {
        tvOption1.setOnClickListener(this);
        tvOption2.setOnClickListener(this);
        tvOption3.setOnClickListener(this);
        tvOption4.setOnClickListener(this);

        rvParentRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        fetchFollowers();
    }


    private void fetchFollowers() {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks()
            .execute(new Runnable() {
                @Override
                public void run() {
                    if(mPresenter.getStoredTrackCount() > 0) {
                        Log.e(TAG, "From DB");

                        mPresenter.dispatchTrackDataToUI(mPresenter.getAllRecords());
                    } else {
                        Log.e(TAG, "From API");
                        mPresenter.fetchFollowerRecords("16948903");
                    }


                    try {
                        Process process = Runtime.getRuntime().exec("logcat ");
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                        StringBuilder log = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            log.append(line);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                    }

                }
            });
    }


    private void fetchFavorites(final List<FollowerResponse.CollectionBean> followers) {

        DefaultExecutorSupplier.getInstance().forBackgroundTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.fetchFavoriteRecords(followers);
                    }
                });
    }


    public void setRecyclerData(LinkedList<Category> tracks) {
        if(null == parentRecyclerAdapter) {
            parentRecyclerAdapter = new ParentRecyclerAdapter(this, tracks, this);
            rvParentRecyclerView.setAdapter(parentRecyclerAdapter);
        } else {
            parentRecyclerAdapter.notifyDataHasSetChanged(tracks);
        }

        nsvMainLayout.setVisibility(View.VISIBLE);
        rlLoader.setGravity(View.GONE);
    }


    @Override
    public void onFollowerRecordFetchComplete(List<FollowerResponse.CollectionBean> followers) {
        fetchFavorites(followers);
    }


    @Override
    public void onFavoriteRecordFetchComplete(LinkedList<Track> favorites) {

        if(null != favorites && !favorites.isEmpty()) {
            if (null == sliderAdapter) {
                sliderAdapter = new SliderAdapter(this, mPresenter.getFilteredRecords(TracksDAO.FAVORITINGS_COUNT, 5));
                vpTopContent.setClipToPadding(false);
                vpTopContent.setPadding(80,0,80,0);
                vpTopContent.setPageMargin(40);
                vpTopContent.setAdapter(sliderAdapter);
            } else {
                sliderAdapter.notifyDataHasSetChanged(favorites);
            }


            final LinkedList<Category> tracks = new LinkedList<>();
            tracks.add(new Category("Most Liked", getResources().getString(R.string.heading2), mPresenter.getFilteredRecords(TracksDAO.FAVORITINGS_COUNT, 50)));
            tracks.add(new Category("Latest Release", getResources().getString(R.string.heading2), mPresenter.getFilteredRecords(TracksDAO.RELEASE_MONTH, 50)));
            tracks.add(new Category("Most Talked About", getResources().getString(R.string.heading2), mPresenter.getFilteredRecords(TracksDAO.COMMENT_COUNT, 50)));

            LinkedList<Track> topGenre = mPresenter.getFilteredRecords(TracksDAO.GENRE, 3);

            if (topGenre.size() >= 1) {
                tracks.add(new Category("Best of " + topGenre.get(0).getGenre(), getResources().getString(R.string.heading2), mPresenter.getFilteredRecords(TracksDAO.GENRE, topGenre.get(0).getGenre(), 50)));
            }

            if (topGenre.size() >= 2) {
                tracks.add(new Category("Best of " + topGenre.get(1).getGenre(), getResources().getString(R.string.heading2), mPresenter.getFilteredRecords(TracksDAO.GENRE, topGenre.get(1).getGenre(), 50)));
            }

            if (topGenre.size() >= 3) {
                tracks.add(new Category("Best of " + topGenre.get(2).getGenre(), getResources().getString(R.string.heading2), mPresenter.getFilteredRecords(TracksDAO.GENRE, topGenre.get(2).getGenre(), 50)));
            }

            setRecyclerData(tracks);
        }
    }


    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showDetails(final Track track) {
        final Dialog dialog = new Dialog(LandingActivity.this, R.style.DetailsDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(Boolean.TRUE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_details);

        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        ivArtwork = dialog.findViewById(R.id.ivArtwork);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        TextView tvSubTitle = dialog.findViewById(R.id.tvSubTitle);
        LinearLayout llListen = dialog.findViewById(R.id.llListen);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        try {
            networkThread = new Thread(new ImageDownloader(track.getArtwork_url()));
            networkThread.start();
            networkHandler = new ImageFormatterHandler(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        tvTitle.setText(track.getTitle());
        tvSubTitle.setText(track.getUser().getUsername());

        RelativeLayout dialog_upper_layout = dialog.findViewById(R.id.rl_parent_layout);

        llListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = track.getPermalink_url();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        new BounceAnimation(LandingActivity.this).loadAnimation(dialog_upper_layout);
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tvOption1:
                showListDialog(mPresenter.getFilteredRecords(TracksDAO.PLAYBACK_COUNT, 10), getString(R.string.option1), "TRACK");
                break;
            case R.id.tvOption2:
                showListDialog(mPresenter.getFilteredRecords(" (" + TracksDAO.COMMENT_COUNT + " + (" + TracksDAO.FAVORITINGS_COUNT + "/2))", 10), getString(R.string.option2), "USER");
                break;
            case R.id.tvOption3:
                showListDialog(mPresenter.getFilteredRecords(TracksDAO.RELEASE_YEAR, 20), getString(R.string.option3), "TRACK");
                break;
            case R.id.tvOption4:
                showListDialog(mPresenter.getFilteredRecords(TracksDAO.GENRE, 10), getString(R.string.option4), "GENRE");
                break;

        }
    }


    private void showListDialog(LinkedList<Track> filteredRecords, String viewType, String type) {
        final Dialog dialog = new Dialog(LandingActivity.this, R.style.DetailsDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(Boolean.TRUE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.option_items);

        ImageView ivClose = dialog.findViewById(R.id.ivClose);
        TextView tvTitle = dialog.findViewById(R.id.tvTitle);
        RecyclerView rvItems = dialog.findViewById(R.id.rvItems);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvTitle.setText(viewType);

        rvItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DialogRecyclerAdapter adapter = new DialogRecyclerAdapter(this, filteredRecords, type);
        rvItems.setAdapter(adapter);

        RelativeLayout dialog_upper_layout = dialog.findViewById(R.id.rl_parent_layout);
        new BounceAnimation(LandingActivity.this).loadAnimation(dialog_upper_layout);
        dialog.show();
    }


    class ImageDownloader implements Runnable {

        String imageURL;

        private ImageDownloader(String imageURL) {
            this.imageURL = imageURL;
        }

        @Override
        public void run() {
            try {
                Message imageMessage = Message.obtain();
                if(TextUtils.isEmpty(imageURL)) {
                    imageMessage.obj = BitmapFactory.decodeResource(LandingActivity.this.getResources(), R.drawable.ic_empty);
                } else {
                    imageMessage.obj = Glide.with(LandingActivity.this).asBitmap().load(imageURL.replace("large", "t500x500")).submit(400, 400).get();
                }

                networkHandler.sendMessage(imageMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static class ImageFormatterHandler extends Handler {
        private final WeakReference<LandingActivity> activityWeakReference;

        private ImageFormatterHandler(LandingActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            try {

                LandingActivity landingActivity = activityWeakReference.get();

                if (null != landingActivity) {
                    Bitmap mbitmap = (Bitmap) msg.obj;

                    Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
                    Canvas canvas = new Canvas(imageRounded);
                    Paint mpaint = new Paint();
                    mpaint.setAntiAlias(true);
                    mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

                    //For Circular Bounds
                    canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 200, 200, mpaint);// Round Image Corner 100 100 100 100

                    //For Rounded Corner Square
                    landingActivity.ivArtwork.setImageBitmap(imageRounded);
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
}
