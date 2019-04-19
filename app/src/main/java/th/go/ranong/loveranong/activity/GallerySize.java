package th.go.ranong.loveranong.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.net.URL;

import th.go.ranong.loveranong.R;

public class GallerySize extends AppCompatActivity {

    Toolbar toolbar;
    String url;
    ImageView fullpic;
    Bitmap bMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_size);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("รูปภาพ");


        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        fullpic = findViewById(R.id.fullpic);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.mock)
                .error(R.drawable.mock);
        Glide
                .with(GallerySize.this)
                .applyDefaultRequestOptions(options)
                .load(url)
                .into(fullpic);




    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
