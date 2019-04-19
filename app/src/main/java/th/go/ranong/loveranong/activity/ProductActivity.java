package th.go.ranong.loveranong.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.adapter.PackageDetailAdapter;
import th.go.ranong.loveranong.adapter.ProductAdapter;
import th.go.ranong.loveranong.dao.PackageDetailCollectionDao;
import th.go.ranong.loveranong.dao.ProductCollectionDao;
import th.go.ranong.loveranong.http.Http;
import th.go.ranong.loveranong.service.ApiService;

public class ProductActivity extends AppCompatActivity {

    ListView listView;
    ProductAdapter productAdapter;
    ProductCollectionDao productCollectionDao;
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
        setContentView(R.layout.activity_product);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        progressDialog = new ProgressDialog(ProductActivity.this);
        progressDialog.setMessage("กำลังโหลด...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Intent intent = getIntent();
        String poiid = intent.getStringExtra("id");
        String cat = intent.getStringExtra("cat");

        listView = findViewById(R.id.listView);

        productAdapter = new ProductAdapter();
        listView.setAdapter(productAdapter);


        ApiService mApiService = this.getInterfaceService();
        Call<ProductCollectionDao> mService = mApiService.loadProduct(poiid,cat);
        mService.enqueue(new Callback<ProductCollectionDao>() {
            @Override
            public void onResponse(Call<ProductCollectionDao> call, Response<ProductCollectionDao> response) {
                if(response.isSuccessful()) {
                    productCollectionDao = response.body();


                        productAdapter.setProductCollectionDao(productCollectionDao);
                    productAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                } else {


                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
                    builder.setTitle("ข้อผิดพลาด");
                    builder.setMessage("ไม่พบข้อมูล");
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
            public void onFailure(Call<ProductCollectionDao> call, Throwable t) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
                builder.setTitle("ข้อผิดพลาด");
                builder.setMessage("ไม่พบข้อมูล");
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
