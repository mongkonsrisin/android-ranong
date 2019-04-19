package th.go.ranong.loveranong.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import th.go.ranong.loveranong.R;

public class MenuActivity extends AppCompatActivity {

    Typeface typeface;
    TextView tvTouristAttraction,tvProduct,tvAccommodation,tvRestaurant,tvPackage,tvSearch;
    ImageView btnLogo,btnSearchWay,btnTouristAttraction,btnProduct,btnAccommodation,btnRestaurant,btnTourPackage;
    private int count = 0;
    private long startMillis=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnProduct = findViewById(R.id.btnProduct);
        btnAccommodation = findViewById(R.id.btnAccommodation);
        btnTouristAttraction = findViewById(R.id.btnTouristAttraction);
        btnTourPackage = findViewById(R.id.btnTourPackage);
        btnSearchWay = findViewById(R.id.btnSearchWay);
        btnRestaurant = findViewById(R.id.btnRestaurant);
        btnLogo = findViewById(R.id.btnLogo);

        tvTouristAttraction = findViewById(R.id.tvTour);
        tvProduct = findViewById(R.id.tvProduct);
        tvAccommodation = findViewById(R.id.tvAccommodation);
        tvRestaurant = findViewById(R.id.tvRestaurant);
        tvPackage = findViewById(R.id.tvProgram);
        tvSearch = findViewById(R.id.tvSearch);


        typeface = Typeface.createFromAsset(getAssets(),  "superspace_regular.ttf");
        tvTouristAttraction.setTypeface(typeface);
        tvProduct.setTypeface(typeface);
        tvAccommodation.setTypeface(typeface);
        tvRestaurant.setTypeface(typeface);
        tvPackage.setTypeface(typeface);
        tvSearch.setTypeface(typeface);



        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MenuActivity.this,AboutActivity.class);
                    startActivity(intent);
            }
        });

        btnAccommodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PoiListActivity.class);
                intent.putExtra("filter", "1");
                intent.putExtra("menu","ที่พัก");
                startActivity(intent);
            }
        });

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PoiListActivity.class);
                intent.putExtra("filter", "4");
                intent.putExtra("menu","สินค้าชุมชน");
                startActivity(intent);
            }
        });

        btnTouristAttraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PoiListActivity.class);
                intent.putExtra("filter", "2");
                intent.putExtra("menu","สถานที่ท่องเที่ยว");
                startActivity(intent);
            }
        });

        btnTourPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PackageListActivity.class);
                startActivity(intent);
            }
        });

        btnSearchWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PoiListActivity.class);
                intent.putExtra("filter", "3");
                intent.putExtra("menu","ร้านอาหาร");
                startActivity(intent);
            }
        });
    }


}
