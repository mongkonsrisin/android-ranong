package th.go.ranong.loveranong.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import th.go.ranong.loveranong.adapter.PoiListAdapter;
import th.go.ranong.loveranong.dao.PoiListCollectionDao;
import th.go.ranong.loveranong.http.Http;
import th.go.ranong.loveranong.service.ApiService;

public class PoiListActivity extends AppCompatActivity {

    String filter;

    ListView listView;
    PoiListAdapter poiListAdapter;
    PoiListCollectionDao poiListCollectionDao;
    Toolbar toolbar;
    String poiId,poiUrl,poiName,poiDescription,poiPhone,poiWebsite,poiOpentime,poiClosetime,poiOpenday,poiMobile,poiEmail,poiFacebook,poiLine,poiLat,poiLng,poiCat;
    ProgressDialog progressDialog;
    String menu = "";
    FloatingActionButton fab;

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
        setContentView(R.layout.activity_poi_list);
        final Intent intent = getIntent();
        filter = intent.getStringExtra("filter");
        menu = intent.getStringExtra("menu");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(menu);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(PoiListActivity.this, "TEST", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PoiListActivity.this, MapAllActivity.class);
                //intent.putExtra("filter", "1");
                Gson gson = new Gson();
                intent.putExtra("obj", gson.toJson(poiListCollectionDao));
                startActivity(intent);

            }
        });

        progressDialog = new ProgressDialog(PoiListActivity.this);
        progressDialog.setMessage("กำลังโหลด...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();



        listView = findViewById(R.id.listView);
        poiListAdapter = new PoiListAdapter();
        listView.setAdapter(poiListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < poiListAdapter.getCount()) {
                    //POI Item ID
                    poiId = String.valueOf(poiListCollectionDao.getData().get(position).getPoiId());
                    poiCat = String.valueOf(poiListCollectionDao.getData().get(position).getPoiCat());
                    poiUrl = poiListCollectionDao.getData().get(position).getPoiPhoto();
                    poiName = poiListCollectionDao.getData().get(position).getPoiName();
                    poiDescription = poiListCollectionDao.getData().get(position).getPoiDescription();
                    poiPhone = poiListCollectionDao.getData().get(position).getPoiPhone();
                    poiWebsite = poiListCollectionDao.getData().get(position).getPoiWebsite();
                    poiOpentime = poiListCollectionDao.getData().get(position).getPoiOpentime();
                    poiClosetime = poiListCollectionDao.getData().get(position).getPoiClosetime();
                    poiOpenday = poiListCollectionDao.getData().get(position).getPoiOpenday();
                    poiMobile = poiListCollectionDao.getData().get(position).getPoiMobile();
                    poiEmail = poiListCollectionDao.getData().get(position).getPoiEmail();
                    poiFacebook = poiListCollectionDao.getData().get(position).getPoiFacebook();
                    poiLine = poiListCollectionDao.getData().get(position).getPoiLine();
                    poiLat = poiListCollectionDao.getData().get(position).getPoiLat();
                    poiLng = poiListCollectionDao.getData().get(position).getPoiLng();
                    Intent intent = new Intent(PoiListActivity.this, PoiDetailActivity.class);
                    intent.putExtra("id",poiId);
                    intent.putExtra("cat",poiCat);
                    intent.putExtra("url",poiUrl);
                    intent.putExtra("name",poiName);
                    intent.putExtra("description",poiDescription);
                    intent.putExtra("phone",poiPhone);
                    intent.putExtra("website",poiWebsite);
                    intent.putExtra("opentime",poiOpentime);
                    intent.putExtra("closetime",poiClosetime);
                    intent.putExtra("openday",poiOpenday);
                    intent.putExtra("mobile",poiMobile);
                    intent.putExtra("email",poiEmail);
                    intent.putExtra("facebook",poiFacebook);
                    intent.putExtra("line",poiLine);
                    intent.putExtra("lat",poiLat);
                    intent.putExtra("lng",poiLng);
                    intent.putExtra("filter",filter);
                    startActivity(intent);
                }
            }
        });




        ApiService mApiService = this.getInterfaceService();
        Call<PoiListCollectionDao> mService = mApiService.loadAllPoi(filter);
        mService.enqueue(new Callback<PoiListCollectionDao>() {
            @Override
            public void onResponse(Call<PoiListCollectionDao> call, Response<PoiListCollectionDao> response) {
                if(response.isSuccessful()) {
                    poiListCollectionDao = response.body();
                    poiListAdapter.setPoiListCollectionDao(poiListCollectionDao);
                    poiListAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();


                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PoiListActivity.this);
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
            public void onFailure(Call<PoiListCollectionDao> call, Throwable t) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PoiListActivity.this);
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
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuSearch = menu.findItem(R.id.search);
        SearchView searchView =  (SearchView) menuSearch.getActionView();
        searchView.setQueryHint("ค้นหา...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                Toast.makeText(PoiListActivity.this, query, Toast.LENGTH_SHORT).show();
                if(query.isEmpty()) {
                    ApiService mApiService = getInterfaceService();
                    Call<PoiListCollectionDao> mService = mApiService.loadAllPoi(filter);
                    mService.enqueue(new Callback<PoiListCollectionDao>() {
                        @Override
                        public void onResponse(Call<PoiListCollectionDao> call, Response<PoiListCollectionDao> response) {
                            if(response.isSuccessful()) {
                                poiListCollectionDao = response.body();
                                poiListAdapter.setPoiListCollectionDao(poiListCollectionDao);
                                poiListAdapter.notifyDataSetChanged();
                                progressDialog.dismiss();


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(PoiListActivity.this);
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
                        public void onFailure(Call<PoiListCollectionDao> call, Throwable t) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(PoiListActivity.this);
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

                } else {
                    //Search here...
                    ApiService mApiService = getInterfaceService();
                    Call<PoiListCollectionDao> mService = mApiService.searchPoi(filter,query);
                    mService.enqueue(new Callback<PoiListCollectionDao>() {
                        @Override
                        public void onResponse(Call<PoiListCollectionDao> call, Response<PoiListCollectionDao> response) {


                            if(response.isSuccessful()) {
                                poiListCollectionDao = response.body();
                                if(poiListCollectionDao.getData().size() > 0) {
                                    poiListAdapter.setPoiListCollectionDao(poiListCollectionDao);
                                    poiListAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(PoiListActivity.this, "ไม่พบข้อมูล", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                Toast.makeText(PoiListActivity.this, "ไม่พบข้อมูล", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<PoiListCollectionDao> call, Throwable t) {

                            Toast.makeText(PoiListActivity.this, "ไม่พบข้อมูล", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;

            }
        });


        return true;
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
