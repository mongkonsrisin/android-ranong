package th.go.ranong.loveranong.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.config.GoogleDirectionConfiguration;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.request.DirectionDestinationRequest;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.dao.PackageDetailCollectionDao;
import th.go.ranong.loveranong.dao.PackageDetailListItemDao;
import th.go.ranong.loveranong.dao.PoiListCollectionDao;
import th.go.ranong.loveranong.http.Http;
import th.go.ranong.loveranong.service.ApiService;

public class RouteActivity extends AppCompatActivity implements OnMapReadyCallback, DirectionCallback, GoogleMap.InfoWindowAdapter {
    private GoogleMap mMap;
    PackageDetailCollectionDao packageDetailCollectionDao;

    Toolbar toolbar;
    int r;


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
        setContentView(R.layout.activity_route);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("เส้นทางการเดินทาง");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setInfoWindowAdapter(this);


        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        packageDetailCollectionDao = gson.fromJson(strObj, PackageDetailCollectionDao.class);

        int i = packageDetailCollectionDao.getData().getPkRoutedetail().size()-1;
        String serverKey = "AIzaSyA80KJdqVeezeHpq-K8zb5FIzDntgmfmdM";
        LatLng start = new LatLng(Double.parseDouble(packageDetailCollectionDao.getData().getPkRoutedetail().get(0).getPoiLat()), Double.parseDouble(packageDetailCollectionDao.getData().getPkRoutedetail().get(0).getPoiLng()));

         LatLng end = new LatLng(Double.parseDouble(packageDetailCollectionDao.getData().getPkRoutedetail().get(i).getPoiLat()), Double.parseDouble(packageDetailCollectionDao.getData().getPkRoutedetail().get(i).getPoiLng()));

        List<LatLng> waypoints = new ArrayList();
        for (int j = 0; j < packageDetailCollectionDao.getData().getPkRoutedetail().size(); j++) {
            waypoints.add(new LatLng(Double.parseDouble(packageDetailCollectionDao.getData().getPkRoutedetail().get(j).getPoiLat()),
                    Double.parseDouble(packageDetailCollectionDao.getData().getPkRoutedetail().get(j).getPoiLng())));
            String pin = packageDetailCollectionDao.getData().getPkRoutedetail().get(j).getPoiPin();
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
                    .position(new LatLng(Double.parseDouble(packageDetailCollectionDao.getData().getPkRoutedetail().get(j).getPoiLat()),
                            Double.parseDouble(packageDetailCollectionDao.getData().getPkRoutedetail().get(j).getPoiLng())))
                    .icon(BitmapDescriptorFactory.fromResource(r))
                    .title(packageDetailCollectionDao.getData().getPkRoutedetail().get(j).getPoiName())
                    .snippet(String.valueOf(packageDetailCollectionDao.getData().getPkRoutedetail().get(j)))) ;
        }




        GoogleDirectionConfiguration.getInstance().setLogEnabled(true);
        GoogleDirection.withServerKey(serverKey)
                .from(start)
                .and(waypoints)
                .to(end)
                .transportMode(TransportMode.DRIVING)
                .execute(this);





    }
    private void setCameraWithCoordinationBounds(Route route) {
        LatLng southwest = route.getBound().getSouthwestCoordination().getCoordination();
        LatLng northeast = route.getBound().getNortheastCoordination().getCoordination();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        if (direction.isOK()) {
            Route route = direction.getRouteList().get(0);
            int legCount = route.getLegList().size();
            for (int index = 0; index < legCount; index++) {
                Leg leg = route.getLegList().get(index);
                //mMap.addMarker(new MarkerOptions().position(leg.getStartLocation().getCoordination()));
                if (index == legCount - 1) {
                    //mMap.addMarker(new MarkerOptions().position(leg.getEndLocation().getCoordination()));
                }
                List<Step> stepList = leg.getStepList();
                ArrayList<PolylineOptions> polylineOptionList = DirectionConverter.createTransitPolyline(this, stepList, 5, Color.RED, 3, Color.BLUE);
                for (PolylineOptions polylineOption : polylineOptionList) {
                    mMap.addPolyline(polylineOption);
                }
            }
            setCameraWithCoordinationBounds(route);
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = getLayoutInflater();
        View infoWindow = inflater.inflate(R.layout.info_window, null);
        final ImageView icon = infoWindow.findViewById(R.id.icon);

        //icon.setImageBitmap(bmap);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.mock)
                .error(R.drawable.mock);
        Glide
                .with(RouteActivity.this)
                .applyDefaultRequestOptions(options)
                .load(marker.getSnippet())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                        return false;
                    }
                })

                .into(icon);
        TextView tv = infoWindow.findViewById(R.id.title);
        tv.setText(marker.getTitle());
        return infoWindow;    }
}

