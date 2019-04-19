package th.go.ranong.loveranong.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.dao.PoiCollectionDao;
import th.go.ranong.loveranong.dao.PoiListCollectionDao;
import th.go.ranong.loveranong.dao.RoomListCollectionDao;
import th.go.ranong.loveranong.http.Http;
import th.go.ranong.loveranong.service.ApiService;

public class PoiDetailActivity extends AppCompatActivity {

    Typeface typeface;
    Typeface typeface2;
    Typeface typeface3;
    Toolbar toolbar;
    ImageView ivPhoto;
    TextView tvTitle,tvContent,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tvDay,tvTime,tvCellPhone,tvPhone,tvEmail,tvFacebook,tvLine,tvWeb,tvOpenTime,tvCloseTime;
    String poiId,poiName,poiDescription,poiPhone,poiWebsite,poiOpentime,poiClosetime,poiOpenday,poiMobile,poiEmail,poiFacebook,poiLine,poiLat,poiLng,poiCat,poiUrl;
    FloatingActionButton btnMap;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    PoiCollectionDao poiCollectionDao;
    Bitmap bmap;

    byte[] byteArray;

    //API Service method
    private ApiService getInterfaceService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Http.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiService mInterfaceService = retrofit.create(ApiService.class);
        return mInterfaceService;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_detail);




        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(collapsingToolbarLayout.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
                    collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
                } else {
                    collapsingToolbarLayout.setTitle("");
                }
            }
        });

        Intent intent = getIntent();
        poiId = intent.getStringExtra("id");
        poiCat = intent.getStringExtra("cat");
        poiUrl = intent.getStringExtra("url");
        poiName = intent.getStringExtra("name");
        poiDescription = intent.getStringExtra("description");
        poiPhone = intent.getStringExtra("phone");
        poiWebsite = intent.getStringExtra("website");
       // poiOpentime = intent.getStringExtra("opentime");
        //poiClosetime = intent.getStringExtra("closetime");
        poiOpenday = intent.getStringExtra("openday");
        poiMobile = intent.getStringExtra("mobile");
        poiEmail = intent.getStringExtra("email");
        poiFacebook = intent.getStringExtra("facebook");
        poiLine = intent.getStringExtra("line");
        poiLat = intent.getStringExtra("lat");
        poiLng = intent.getStringExtra("lng");



        ivPhoto = findViewById(R.id.ivPhoto);
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        toolbar = findViewById(R.id.toolbar);
        tv1 = findViewById(R.id.Tv1);
        tv2 = findViewById(R.id.Tv2);
        tv3 = findViewById(R.id.Tv3);
        tv4 = findViewById(R.id.Tv4);
        tv5 = findViewById(R.id.Tv5);
        tv6 = findViewById(R.id.Tv6);
        tv7 = findViewById(R.id.Tv7);
        tv8 = findViewById(R.id.Tv8);
        tvDay = findViewById(R.id.tvDay);
        tvCellPhone = findViewById(R.id.tvCellPhone);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        tvFacebook = findViewById(R.id.tvFacebook);
        tvLine = findViewById(R.id.tvLine);
        btnMap = findViewById(R.id.btnMap);
        tvWeb = findViewById(R.id.tvWeb);
        tvOpenTime = findViewById(R.id.tvOpenTime);

        typeface = Typeface.createFromAsset(getAssets(),  "superspace_light.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(),"superspace_regular.ttf");
        typeface3 = Typeface.createFromAsset(getAssets(),"superspace_bold.ttf");








        tvTitle.setTypeface(typeface3);
        tvContent.setTypeface(typeface);
        tv1.setTypeface(typeface3);
        tv2.setTypeface(typeface3);
        tv3.setTypeface(typeface3);
        tv4.setTypeface(typeface3);
        tv5.setTypeface(typeface3);
        tv6.setTypeface(typeface3);
        tv7.setTypeface(typeface3);
        tv8.setTypeface(typeface3);
        tvDay.setTypeface(typeface2);
        tvCellPhone.setTypeface(typeface2);
        tvPhone.setTypeface(typeface2);
        tvEmail.setTypeface(typeface2);
        tvFacebook.setTypeface(typeface2);
        tvLine.setTypeface(typeface2);
        tvWeb.setTypeface(typeface2);
        tvOpenTime.setTypeface(typeface2);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");







        ApiService mApiService = this.getInterfaceService();
        Call<PoiCollectionDao> mService = mApiService.viewPoi(poiId);
        mService.enqueue(new Callback<PoiCollectionDao>() {
            @Override
            public void onResponse(Call<PoiCollectionDao> call, Response<PoiCollectionDao> response) {
                if(response.isSuccessful()) {
                    poiCollectionDao = response.body();
                    tvTitle.setText(poiCollectionDao.getData().getPoiName());
                    tvContent.setText(poiCollectionDao.getData().getPoiDescription());
                    tvPhone.setText (poiCollectionDao.getData().getPoiPhone());
                    tvWeb.setText(poiCollectionDao.getData().getPoiWebsite());
                    tvOpenTime.setText(poiCollectionDao.getData().getPoiTime());
                    tvDay.setText(poiCollectionDao.getData().getPoiOpenday());
                    tvCellPhone.setText(poiCollectionDao.getData().getPoiMobile());
                    tvEmail.setText(poiCollectionDao.getData().getPoiEmail());
                    tvFacebook.setText(poiCollectionDao.getData().getPoiFacebook());
                    tvLine.setText(poiCollectionDao.getData().getPoiLine());

                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.mock)
                            .error(R.drawable.mock);
                    Glide
                            .with(PoiDetailActivity.this)
                            .applyDefaultRequestOptions(options)
                            .load(poiCollectionDao.getData().getPoiPhoto())

                            .into(ivPhoto);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PoiDetailActivity.this);
                    builder.setTitle("Error");
                    builder.setMessage(response.errorBody().toString());
                    builder.setCancelable(false);
                    builder.setNegativeButton(
                            "Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }

            @Override
            public void onFailure(Call<PoiCollectionDao> call, Throwable t) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PoiDetailActivity.this);
                builder.setTitle("Error");
                builder.setMessage(t.getMessage().toString());
                builder.setCancelable(false);
                builder.setNegativeButton(
                        "Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });








//        tvTitle.setText(poiName);
//        tvContent.setText(poiDescription);
//        tvPhone.setText (poiPhone);
//        tvWeb.setText(poiWebsite);
//        tvOpenTime.setText(poiOpentime);
//        tvCloseTime.setText(poiClosetime);
//        tvDay.setText(poiOpenday);
//        tvCellPhone.setText(poiMobile);
//        tvEmail.setText(poiEmail);
//        tvFacebook.setText(poiFacebook);
//        tvLine.setText(poiLine);

        Glide.with(this)
                .asBitmap()
                .load(getIntent().getStringExtra("url"))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        bmap = resource;
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byteArray = stream.toByteArray();
                    }
                });



        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PoiDetailActivity.this, MapActivity.class);
                intent.putExtra("lat", poiLat);
                intent.putExtra("lng", poiLng);
                intent.putExtra("name", poiName);
                intent.putExtra("url", poiUrl);
                intent.putExtra("image", byteArray);
                startActivity(intent);
            }
        });


        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PoiDetailActivity.this, GalleryActivity.class);
                intent.putExtra("poiid", poiId);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        if (item.getItemId() == R.id.info) {
            Intent intent = new Intent(PoiDetailActivity.this, ProductActivity.class);
            intent.putExtra("id", poiId);
            intent.putExtra("cat", poiCat);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
