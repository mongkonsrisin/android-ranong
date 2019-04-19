package th.go.ranong.loveranong.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.layernet.thaidatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import th.go.ranong.loveranong.R;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnSearch;
    Typeface boldTypeface;
    EditText etDateStart,etDateEnd,etCat,etType,etBudget,etPeople;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ค้นหาสถานที่");

        boldTypeface = Typeface.createFromAsset(getAssets(),"superspace_bold.ttf");
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setTypeface(boldTypeface);

        etDateStart = findViewById(R.id.etDateStart);
        etDateEnd = findViewById(R.id.etDateEnd);
        etCat = findViewById(R.id.etCat);
        etType = findViewById(R.id.etType);
        etBudget = findViewById(R.id.etBudget);
        etPeople = findViewById(R.id.etPeople);


        etDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String day,month;
                        if (dayOfMonth < 10) { day = "0" + String.valueOf(dayOfMonth); } else {day = String.valueOf(dayOfMonth); }
                        if (monthOfYear+1 < 10) { month = "0" + String.valueOf(monthOfYear+1); } else {month = String.valueOf(monthOfYear+1); }
                        etDateStart.setText(day+"/"+month+"/"+(year+543));

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show(getFragmentManager(),"Datepickerdialog");

            }
        });



        etDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String day,month;
                        if (dayOfMonth < 10) { day = "0" + String.valueOf(dayOfMonth); } else {day = String.valueOf(dayOfMonth); }
                        if (monthOfYear+1 < 10) { month = "0" + String.valueOf(monthOfYear+1); } else {month = String.valueOf(monthOfYear+1); }
                        etDateEnd.setText(day+"/"+month+"/"+(year+543));

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show(getFragmentManager(),"Datepickerdialog");

            }
        });


        final String[] cats = {"ทั้งหมด","เชิงนิเวศ","เชิงสุขภาพ"};
        final ArrayAdapter<String> cats2 = new ArrayAdapter<>(SearchActivity.this,android.R.layout.simple_list_item_1,cats);

        etCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(SearchActivity.this)
                        .setTitle("เลือกประเภทการท่องเที่ยว")
                        .setAdapter(cats2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                etCat.setText(cats[i]);
                                dialogInterface.dismiss();
                            }
                        }).create().show();

            }
        });



        final String[] types = {"ทั้งหมด","รถยนต์","มอเตอร์ไซค์","จักรยาน"};
        final ArrayAdapter<String> types2 = new ArrayAdapter<>(SearchActivity.this,android.R.layout.simple_list_item_1,types);

        etType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(SearchActivity.this)
                        .setTitle("เลือกรูปแบบการเดินทาง")
                        .setAdapter(types2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                etType.setText(types[i]);
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }
        });





        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateStart = etDateStart.getText().toString().trim();
                String dateEnd = etDateEnd.getText().toString().trim();
                String category = etCat.getText().toString();
                String type = etType.getText().toString().trim();
                String budget = etBudget.getText().toString().trim();
                String people = etPeople.getText().toString().trim();






                if(!dateStart.equals("") && !dateEnd.equals("") && !category.equals("") && !type.equals("") && !budget.equals("") && !people.equals("")) {


                    Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
                    intent.putExtra("datestart",dateStart);
                    intent.putExtra("dateend",dateEnd);
                    intent.putExtra("category",category);
                    intent.putExtra("type",type);
                    intent.putExtra("budget",budget);
                    intent.putExtra("people",people);

                    startActivity(intent);
                } else {
                    Toast.makeText(SearchActivity.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }




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
