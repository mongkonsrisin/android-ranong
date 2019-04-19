package th.go.ranong.loveranong.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import th.go.ranong.loveranong.R;

public class AboutActivity extends AppCompatActivity {

    Typeface typeface;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        tv1 = findViewById(R.id.TvDownload);

        typeface = Typeface.createFromAsset(getAssets(),  "superspace_bold.ttf");

        tv1.setTypeface(typeface);

    }
}
