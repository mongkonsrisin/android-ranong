package th.go.ranong.loveranong.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.adapter.PackageDetailAdapter;
import th.go.ranong.loveranong.dao.PackageDetailCollectionDao;
import th.go.ranong.loveranong.http.Http;
import th.go.ranong.loveranong.service.ApiService;

public class PackageDetailActivity extends AppCompatActivity {

    ListView listView;
    PackageDetailAdapter packageDetailAdapter;
    PackageDetailCollectionDao packageDetailCollectionDao;
    Toolbar toolbar;
    ProgressDialog progressDialog;

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
        setContentView(R.layout.activity_package_detail);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("โปรแกรมท่องเที่ยว");

        progressDialog = new ProgressDialog(PackageDetailActivity.this);
        progressDialog.setMessage("กำลังโหลด...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        listView = findViewById(R.id.listView);

        packageDetailAdapter = new PackageDetailAdapter();
        listView.setAdapter(packageDetailAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PackageDetailActivity.this,PoiDetailActivity.class);
               intent.putExtra("id",String.valueOf(packageDetailCollectionDao.getData().getPkRoutedetail().get(position).getPoiId()));
                startActivity(intent);
            }
        });


        ApiService mApiService = this.getInterfaceService();
        Call<PackageDetailCollectionDao> mService = mApiService.loadPackage(id);
        mService.enqueue(new Callback<PackageDetailCollectionDao>() {
            @Override
            public void onResponse(Call<PackageDetailCollectionDao> call, Response<PackageDetailCollectionDao> response) {
                if(response.isSuccessful()) {
                    packageDetailCollectionDao = response.body();
                    packageDetailAdapter.setPackageListItemDao(packageDetailCollectionDao.getData());
                    packageDetailAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PackageDetailActivity.this);
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
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<PackageDetailCollectionDao> call, Throwable t) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PackageDetailActivity.this);
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
                progressDialog.dismiss();
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
            Intent intent = new Intent(PackageDetailActivity.this, RouteActivity.class);
            Gson gson = new Gson();
            intent.putExtra("obj", gson.toJson(packageDetailCollectionDao));
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
