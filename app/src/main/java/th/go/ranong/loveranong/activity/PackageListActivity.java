package th.go.ranong.loveranong.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.adapter.PackageListAdapter;
import th.go.ranong.loveranong.dao.PackageListCollectionDao;
import th.go.ranong.loveranong.http.Http;
import th.go.ranong.loveranong.service.ApiService;

public class PackageListActivity extends AppCompatActivity {

    ListView listView;
    PackageListAdapter packageListAdapter;
    PackageListCollectionDao packageListCollectionDao;
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
        setContentView(R.layout.activity_package_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("โปรแกรมท่องเที่ยว");
        progressDialog = new ProgressDialog(PackageListActivity.this);
        progressDialog.setMessage("กำลังโหลด...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        listView = findViewById(R.id.listView);
        packageListAdapter = new PackageListAdapter();
        listView.setAdapter(packageListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = String.valueOf(packageListCollectionDao.getData().get(i).getPkId());
                Intent intent = new Intent(PackageListActivity.this, PackageDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });




        ApiService mApiService = this.getInterfaceService();
        Call<PackageListCollectionDao> mService = mApiService.loadAllPackage();
        mService.enqueue(new Callback<PackageListCollectionDao>() {
            @Override
            public void onResponse(Call<PackageListCollectionDao> call, Response<PackageListCollectionDao> response) {
                if(response.isSuccessful()) {
                    packageListCollectionDao = response.body();
                    packageListAdapter.setPackageListCollectionDao(packageListCollectionDao);
                    packageListAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PackageListActivity.this);
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
            public void onFailure(Call<PackageListCollectionDao> call, Throwable t) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PackageListActivity.this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
