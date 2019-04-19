package th.go.ranong.loveranong.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import th.go.ranong.loveranong.R;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback , GoogleMap.InfoWindowAdapter{
    public GoogleMap mMap;
    String poiLat,poiLng,poiName,poiUrl;
    Toolbar toolbar;
    Bitmap bmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("แผนที่");

        Intent intent = getIntent();
        poiLat = intent.getStringExtra("lat");
        poiLng = intent.getStringExtra("lng");
        poiName = intent.getStringExtra("name");
        poiUrl = intent.getStringExtra("url");
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);


        double latDouble = Double.parseDouble(poiLat);
        double lngDouble = Double.parseDouble(poiLng);


        LatLng location = new LatLng(latDouble, lngDouble);

        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(location)
                .title(poiName)
               );


        mMap.setInfoWindowAdapter(this);
        marker.showInfoWindow();

        CameraUpdate locate = CameraUpdateFactory.newLatLngZoom(location, 17);
        mMap.moveCamera(locate);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent mapIntent = new Intent("android.intent.action.VIEW", Uri.parse("geo:" + marker.getPosition().latitude + "," + marker.getPosition().longitude + "?q=" + marker.getPosition().latitude + "," + marker.getPosition().longitude + "(" + marker.getTitle() + ")"));
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(MapActivity.this.getPackageManager()) != null) {
                    MapActivity.this.startActivity(mapIntent);
                }
            }
        });

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
    public View getInfoWindow(Marker marker) {

        return null;
    }

    @Override
    public View getInfoContents(final Marker marker) {
        LayoutInflater inflater = getLayoutInflater();
        View infoWindow = inflater.inflate(R.layout.info_window, null);
        final ImageView poi_photo = infoWindow.findViewById(R.id.poi_photo);

        poi_photo.setImageBitmap(bmap);
        TextView tv = infoWindow.findViewById(R.id.poi_title);
        tv.setText(marker.getTitle());
        return infoWindow;
    }
}



