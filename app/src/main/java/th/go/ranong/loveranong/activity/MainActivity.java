package th.go.ranong.loveranong.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.go.ranong.loveranong.BuildConfig;
import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.dao.ConfigCollectionDao;
import th.go.ranong.loveranong.dao.PackageDetailCollectionDao;
import th.go.ranong.loveranong.http.Http;
import th.go.ranong.loveranong.service.ApiService;

public class MainActivity extends AppCompatActivity {

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
        setFullScreenActivity();
        setContentView(R.layout.activity_main);


        int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Check version here
                ApiService mApiService = getInterfaceService();
                Call<ConfigCollectionDao> mService = mApiService.checkVersion("android");
                mService.enqueue(new Callback<ConfigCollectionDao>() {
                    @Override
                    public void onResponse(Call<ConfigCollectionDao> call, Response<ConfigCollectionDao> response) {
                        if(response.isSuccessful()) {

                            ConfigCollectionDao configCollectionDao = response.body();



                            if(BuildConfig.VERSION_NAME.equals(configCollectionDao.getData().getCfgValue())) {
                                //Latest version
                                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                MainActivity.this.startActivity(intent);
                                MainActivity.this.finish();
                            } else {
                                //Need update
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("แจ้งเตือน");
                                builder.setMessage("กรุณาอัพเดตแอพเป็นเวอร์ชัน " + configCollectionDao.getData().getCfgValue() + "\n"
                                        + "(เวอร์ชันปัจจุบันของคุณคือ "  +  BuildConfig.VERSION_NAME + ")"

                                );
                                builder.setCancelable(false);


                                builder.setNegativeButton(
                                        "อัพเดตภายหลัง",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                                                MainActivity.this.startActivity(intent);
                                                MainActivity.this.finish();
                                            }
                                        });
                                builder.setPositiveButton(
                                        "อัพเดตตอนนี้",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //Go to Play Store
                                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                                intent.setData(Uri.parse("market://details?id=th.go.ranong.loveranong"));
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }


                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                        }
                    }

                    @Override
                    public void onFailure(Call<ConfigCollectionDao> call, Throwable t) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                    }
                });



            }
        }, SPLASH_DISPLAY_LENGTH);

    }



    private void setFullScreenActivity() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


}
