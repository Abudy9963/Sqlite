package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText name ,model,number;
    Button add;
    Helper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myDb=new Helper(this);
        name=(EditText)findViewById(R.id.name);
        model=(EditText)findViewById(R.id.model);
        number=(EditText)findViewById(R.id.number);
        add=(Button)findViewById(R.id.add);
             add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String name1=name.getText().toString();
                String model1=model.getText().toString();
                String number1=number.getText().toString();

                Car c=new Car(name1,model1,number1);
                boolean isInserted= myDb.insertData(c);
                if(isInserted)
                { Toast.makeText(Main3Activity.this, "Contact Saved", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(Main3Activity.this,Main2Activity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(Main3Activity.this, "Failed to Save", Toast.LENGTH_LONG).show(); }
         });
              }
}
