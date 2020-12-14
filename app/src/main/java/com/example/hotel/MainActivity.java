package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView price,nyt;
    Spinner sp;
    SeekBar seek;
    RadioButton rb1, rb2, rb3, rb4;
    CheckBox ch1,ch2,ch3;
    Button book;
    ImageView i1,i2;
    public static double originalPrice =0;
    ArrayList<Hotel> RoomList= new ArrayList<Hotel>();
    ArrayList<String> Roomtypes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = findViewById(R.id.roomsp);
        i1=findViewById(R.id.img1);
        i2=findViewById(R.id.img2);

        fillData();
        seek = findViewById(R.id.seekBar);
        nyt = findViewById(R.id.shnt);
        rb1=findViewById(R.id.rbnor);
        rb2=findViewById(R.id.rbsup);
        rb3=findViewById(R.id.rblux);
        rb4=findViewById(R.id.rbsut);
        ch1=findViewById(R.id.cbpark);
        ch2=findViewById(R.id.chbreak);
        ch3=findViewById(R.id.chcan);
        book=findViewById(R.id.btnbk);
        //filling the spinner
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Roomtypes);
        sp.setAdapter(aa);
        sp.setOnItemSelectedListener(this);
        price = findViewById(R.id.price);
        price.setText(String.valueOf(RoomList.get(0).getRoomPrice()));
        originalPrice = RoomList.get(0).getRoomPrice();
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nyt.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rb1.setOnClickListener(new RadioButtonsAction());
        rb2.setOnClickListener(new RadioButtonsAction());
        rb3.setOnClickListener(new RadioButtonsAction());
        rb4.setOnClickListener(new RadioButtonsAction());

        ch1.setOnCheckedChangeListener(new CheckBoxActions());
        ch2.setOnCheckedChangeListener(new CheckBoxActions());
        ch3.setOnCheckedChangeListener(new CheckBoxActions());

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {

                double currentPrice = Double.parseDouble(price.getText().toString());
                double total = currentPrice*Double.parseDouble(nyt.getText().toString());

                price.setText(String.format("%.2f",total*1.13));
            }
        });

    }

        public void fillData(){


            RoomList.add(new Hotel("Single", 100));
            RoomList.add(new Hotel("Double", 180));
        RoomList.add(new Hotel("Triple", 200));
        RoomList.add(new Hotel("Deluxe", 190));
        RoomList.add(new Hotel("Premium", 350));
        for(int i=0;i<RoomList.size();i++){
            Roomtypes.add(RoomList.get(i).getRoomType());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        price.setText(String.valueOf(RoomList.get(i).getRoomPrice()));
        originalPrice = RoomList.get(i).getRoomPrice();
        rb1.setChecked(true);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class RadioButtonsAction implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.rbnor)
                price.setText(String.valueOf(originalPrice));
            else if(v.getId()==R.id.rbsup)
                price.setText(String.format("%.2f",originalPrice*1.25));
            else if(v.getId()==R.id.rblux)
                price.setText(String.format("%.2f",originalPrice*1.50));
            else if(v.getId()==R.id.rbsut)
                price.setText(String.format("%.2f",originalPrice*2));

        }
    }

    private class CheckBoxActions implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            double currentPrice = Double.parseDouble(price.getText().toString());
            if(compoundButton.getId()==R.id.cbpark)
                if(ch1.isChecked())
                    currentPrice+=25;
                else
                    currentPrice-=25;
            if(compoundButton.getId()==R.id.chbreak)
                if(ch2.isChecked())
                    currentPrice+=20;
                else
                    currentPrice-=20;
            if(compoundButton.getId()==R.id.chcan)
                if(ch3.isChecked())
                    currentPrice+=1.10;
                else
                    currentPrice-=1.10;
            price.setText(String.valueOf(currentPrice));

        }
    }
}
