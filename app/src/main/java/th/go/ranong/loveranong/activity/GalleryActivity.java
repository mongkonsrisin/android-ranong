package th.go.ranong.loveranong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.adapter.RanongAdapter;
import th.go.ranong.loveranong.dao.RanongDao;
import th.go.ranong.loveranong.service.ApiService;

public class GalleryActivity extends AppCompatActivity {

    GridView gridView;
    String poiId;
    RanongAdapter ranongAdapter;
    RanongDao ranongDao;
    Toolbar toolbar;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://reg.ssru.ac.th/lovenaranong/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService apiService = retrofit.create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("รูปภาพ");

        gridView = findViewById(R.id.gridView);
        ranongAdapter = new RanongAdapter();
        gridView.setAdapter(ranongAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = ranongDao.getData().get(i);
                Intent intent = new Intent(GalleryActivity.this, GallerySize.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        poiId = intent.getStringExtra("poiid");

        Call<RanongDao> call = apiService.getPhotos(Integer.parseInt(poiId));
        call.enqueue(new Callback<RanongDao>() {
            @Override
            public void onResponse(Call<RanongDao> call, Response<RanongDao> response) {
                if(response.isSuccessful()) {
                    ranongDao = response.body();
                    if(ranongDao.isSuccess()) {
                        ranongAdapter.setRanongDao(ranongDao);
                        ranongAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<RanongDao> call, Throwable t) {

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
}
