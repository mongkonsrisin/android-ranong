package th.go.ranong.loveranong.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.dao.PoiListCollectionDao;
import th.go.ranong.loveranong.http.Http;
import th.go.ranong.loveranong.service.ApiService;

public class MapAllActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    public GoogleMap mMap;
    PoiListCollectionDao poiListCollectionDao;
    Toolbar toolbar;
    int r;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_all);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("แผนที่");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        poiListCollectionDao = gson.fromJson(strObj, PoiListCollectionDao.class);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);

        int j = poiListCollectionDao.getData().size();
        for(int i = 0; i < j; i++) {
            final LatLng location = new LatLng(Double.parseDouble(poiListCollectionDao.getData().get(i).getPoiLat().trim()), Double.parseDouble(poiListCollectionDao.getData().get(i).getPoiLng().trim()));
            final String pin = poiListCollectionDao.getData().get(i).getPoiPin();



            final String url = poiListCollectionDao.getData().get(i).getPoiPhoto();
            final String title = poiListCollectionDao.getData().get(i).getPoiName();

            final int id = poiListCollectionDao.getData().get(i).getPoiId();

            Glide.with(MapAllActivity.this)
                    .asBitmap()
                    .load(url)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            switch (pin) {
                                case "0" : r = R.drawable.pin0; break;
                                case "1" : r = R.drawable.pin1; break;
                                case "2" : r = R.drawable.pin2; break;
                                case "3" : r = R.drawable.pin3; break;
                                case "4" : r = R.drawable.pin4; break;
                                case "5" : r = R.drawable.pin5; break;
                                case "6" : r = R.drawable.pin6; break;
                                case "7" : r = R.drawable.pin7; break;
                                case "8" : r = R.drawable.pin8; break;
                                case "9" : r = R.drawable.pin9; break;
                                case "10" : r = R.drawable.pin10; break;
                                case "11" : r = R.drawable.pin11; break;
                                case "12" : r = R.drawable.pin12; break;
                                case "13" : r = R.drawable.pin13; break;
                                case "14" : r = R.drawable.pin14; break;
                                case "15" : r = R.drawable.pin15; break;

                                default: break;
                            }
                            mMap.addMarker(new MarkerOptions()
                                    .position(location)
                                    .title(title)
                                    .snippet(bitmapToBase64(resource))
                                    .icon(BitmapDescriptorFactory.fromResource(r)))
                                    .setTag(id);
                        }
                    });



//            mMap.addMarker(new MarkerOptions()
//                    .position(location)
//                    .title(poiListCollectionDao.getData().get(i).getPoiName())
//                    .snippet(String.valueOf(poiListCollectionDao.getData().get(i).getPoiId()))
//                    .icon(BitmapDescriptorFactory.fromResource(r)))
//            .setTag(poiListCollectionDao.getData().get(i).getPoiPhoto());



        }


        LatLng location = new LatLng(9.962533, 98.640371);

        CameraUpdate locate = CameraUpdateFactory.newLatLngZoom(location, 10);
        mMap.moveCamera(locate);
        mMap.setInfoWindowAdapter(this);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapAllActivity.this, PoiDetailActivity.class);
                intent.putExtra("id",marker.getTag().toString());
                startActivity(intent);
            }
        });

    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(final Marker marker) {
        LayoutInflater inflater = getLayoutInflater();
        View infoWindow = inflater.inflate(R.layout.info_window, null);
        final ImageView poi_photo = infoWindow.findViewById(R.id.poi_photo);
        poi_photo.setImageBitmap(base64ToBitmap(marker.getSnippet()));

        //poi_photo.setImageBitmap(bmap);
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(R.drawable.mock)
//                .error(R.drawable.mock);
//        Glide
//                .with(MapAllActivity.this)
//                .applyDefaultRequestOptions(options)
//                .load(marker.getSnippet())
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//
//
//                        return false;
//                    }
//                })
//
//                .into(poi_photo);



        TextView tv = infoWindow.findViewById(R.id.poi_title);
        tv.setText(marker.getTitle());
        return infoWindow;
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        bitmap.recycle();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private Bitmap base64ToBitmap(String base64) {
        byte[] byteArray = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

}
