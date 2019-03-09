package com.example.android.agecalculator;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    public String date;
    public String date2, date3, date4;
    public int day1, day2, month1, month2, year1, year2;
    public TextView DoB;
    public TextView present;
    public DatePickerDialog.OnDateSetListener mdateSetListener;
    public DatePickerDialog.OnDateSetListener presentDatepicker;
    public Button button, clear;
    public TextView Years, Months, Days;
    public TextView TotalYears, TotalMonths, TotalWeeks, TotalDays, TotalHours, TotalMinutes, TotalSeconds;
    private DatePicker datepicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DoB = (TextView) findViewById(R.id.birth);
        present = (TextView) findViewById(R.id.present);
        button = (Button) findViewById(R.id.button);
        Years = (TextView) findViewById(R.id.years);
        Months = (TextView) findViewById(R.id.months);
        Days = (TextView) findViewById(R.id.days);
        clear = (Button) findViewById(R.id.buttonClear);
        TotalYears = (TextView) findViewById(R.id.TotalYearsAnswer);
        TotalMonths = (TextView) findViewById(R.id.TotalMonthsAnswer);
        TotalWeeks = (TextView) findViewById(R.id.TotalWeeksAnswer);
        TotalDays = (TextView) findViewById(R.id.TotalDaysAnswer);
        TotalHours = (TextView) findViewById(R.id.TotalHoursAnswer);
        TotalMinutes = (TextView) findViewById(R.id.TotalMinutesAnswer);
        TotalSeconds = (TextView) findViewById(R.id.TotalSecondAnswer);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });


        DoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int Year = cal.get(Calendar.YEAR);
                int Month = cal.get(Calendar.MONTH);
                int Day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(MainActivity.this,
                        mdateSetListener, Year, Month, Day);

                datePickerDialog1.show();

            }

        });


        mdateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date = " " + dayOfMonth + " / " + month + " / " + year;
                date3 = dayOfMonth + "/" + month + "/" + year;
                day1 = dayOfMonth;
                month1 = month;
                year1 = year;
                DoB.setText(date);

            }
        };


        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal1 = Calendar.getInstance();
                int Year = cal1.get(Calendar.YEAR);
                int Month = cal1.get(Calendar.MONTH);
                int Day = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        presentDatepicker, Year, Month, Day);

                datePickerDialog.show();

            }
        });

        presentDatepicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date2 = " " + dayOfMonth + " / " + month + " / " + year;
                date4 = dayOfMonth + "/" + month + "/" + year;
                day2 = dayOfMonth;
                month2 = month;
                year2 = year;

                present.setText(date2);
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (date3 != null && date4 != null) {
                    int resultDay = 0;
                    int resultmonth = 0;
                    int resultYear = 0;
                    if ((day2 == day1) && (month1 == month2) && (year2 == year1)) {
                        Years.setText(String.valueOf(resultYear));
                        Months.setText(String.valueOf(resultmonth));
                        Days.setText(String.valueOf(resultDay));
                    } else if ((day2 < day1) && (month2 <= month1)) {
                        day2 = day2 + 31;
                        month2 = ((month2 + 12) - 1);
                        year2 = year2 - 1;
                    } else if ((day2 < day1) && (month2 > month1)) {
                        day2 = day2 + 31;
                        month2 = month2 - 1;
                    } else if ((day2 > day1) && (month2 < month1)) {
                        month2 = month2 + 12;
                        year2 = year2 - 1;
                    }

                    resultDay = day2 - day1;
                    resultmonth = month2 - month1;
                    resultYear = year2 - year1;

                    Years.setText(String.valueOf(resultYear));
                    Months.setText(String.valueOf(resultmonth));
                    Days.setText(String.valueOf(resultDay));


                    String dateStart = date3;
                    String dateStop = date4;


                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

                    Date d1 = null;
                    Date d2 = null;

                    try {
                        d1 = format.parse(dateStart);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        d2 = format.parse(dateStop);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    long diff = d2.getTime() - d1.getTime();


                    long diffDays = diff / (24 * 60 * 60 * 1000);


                    long TotalNoOfMonths = (resultYear * 12) + resultmonth;
                    long TotalNoOfDays = diffDays;
                    long TotalNoOfWeeks = (TotalNoOfDays / 7);
                    long TotalNoOfHours = (TotalNoOfDays * 24);
                    long TotalNoOfMinutes = TotalNoOfHours * 60;
                    long TotalNoOfSeconds = TotalNoOfMinutes * 60;

                    TotalYears.setText(String.valueOf(resultYear));
                    TotalMonths.setText(String.valueOf(TotalNoOfMonths));
                    TotalWeeks.setText(String.valueOf(TotalNoOfWeeks));
                    TotalDays.setText(String.valueOf(TotalNoOfDays));
                    TotalHours.setText(String.valueOf(TotalNoOfHours));
                    TotalMinutes.setText(String.valueOf(TotalNoOfMinutes));
                    TotalSeconds.setText(String.valueOf(TotalNoOfSeconds));
                } else {
                    Toast.makeText(MainActivity.this, "You Can't leave the above fields empty", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}


