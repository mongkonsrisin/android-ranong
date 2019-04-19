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
import android.widget.Toast;

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

public class SearchResultActivity extends AppCompatActivity {
    Toolbar toolbar;
    PackageListCollectionDao packageListCollectionDao;
    PackageListAdapter packageListAdapter;
    ProgressDialog progressDialog;
    ListView listView;


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
        setContentView(R.layout.activity_search_result);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ผลการค้นหา");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listView = findViewById(R.id.listView);
        packageListAdapter = new PackageListAdapter();
        listView.setAdapter(packageListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = String.valueOf(packageListCollectionDao.getData().get(i).getPkId());
                Intent intent = new Intent(SearchResultActivity.this, PackageDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String dateStart = intent.getStringExtra("datestart");
        String dateEnd = intent.getStringExtra("dateend");
        String category = intent.getStringExtra("category");
        String type = intent.getStringExtra("type");
        String budget = intent.getStringExtra("budget");
        String people = intent.getStringExtra("people");

        String category2,type2;

        switch (category) {
            case "ทั้งหมด" : category2 = "0"; break;
            case "เชิงนิเวศ" : category2 = "1"; break;
            case "เชิงสุขภาพ" : category2 = "2"; break;
            default : category2 = "0"; break;
        }

        switch (type) {
            case "ทั้งหมด" : type2 = "0"; break;
            case "รถยนต์" : type2 = "1"; break;
            case "มอเตอร์ไซค์" : type2 = "2"; break;
            case "จักรยาน" : type2 = "3"; break;
            default : type2 = "0"; break;
        }


        progressDialog = new ProgressDialog(SearchResultActivity.this);
        progressDialog.setMessage("กำลังโหลด...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        ApiService mApiService = this.getInterfaceService();
        Call<PackageListCollectionDao> mService = mApiService.searchPackage(dateStart,dateEnd,category2,type2,budget,people);





        mService.enqueue(new Callback<PackageListCollectionDao>() {
            @Override
            public void onResponse(Call<PackageListCollectionDao> call, Response<PackageListCollectionDao> response) {
                if(response.isSuccessful()) {
                    packageListCollectionDao = response.body();
                        if (packageListCollectionDao.getData().size() > 0) {
                            packageListAdapter.setPackageListCollectionDao(packageListCollectionDao);
                            packageListAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SearchResultActivity.this);
                            builder.setTitle("ข้อผิดพลาด");
                            builder.setMessage("ไม่พบโปรแกรมท่องเที่ยว");
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


                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchResultActivity.this);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(SearchResultActivity.this);
                builder.setTitle("Error333");
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
