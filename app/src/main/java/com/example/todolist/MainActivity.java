package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText ItemET;
    private Button btn;
    private ListView ItemList;
    private ArrayList<String> items;
    private ArrayAdapter<String> Adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ItemET = findViewById(R.id.ED1);
        btn = findViewById(R.id.Bt);
        ItemList= findViewById(R.id.List);

        items =FileHelper.reasData(this);
        Adap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        ItemList.setAdapter(Adap);


        btn.setOnClickListener(this);
        ItemList.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Bt:
                String itemEntered =ItemET.getText().toString();
                Adap.add(itemEntered);
                ItemET.setText("");
                FileHelper.Writedata(items,this);
                Toast.makeText(this,"Work_added",LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        Adap.notifyDataSetChanged();
        Toast.makeText(this,"Delete",LENGTH_LONG).show();

    }
}
