package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity  {

    EditText name2,model2,number2;
    Helper myDb;
    Button update,delete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        name2=(EditText)findViewById(R.id.txt1);
        model2=(EditText)findViewById(R.id.txt2);
        number2=(EditText)findViewById(R.id.txt3);
        update=(Button)findViewById(R.id.update) ;
        delete=(Button)findViewById(R.id.delete) ;
        myDb=new Helper(this);


        int i=getIntent().getIntExtra("id",0);
        ArrayList<Car>cars=new ArrayList<>();
        cars=myDb.getall();;

        name2.setText(cars.get(i).getName());
        model2.setText(cars.get(i).getModel());
        number2.setText(cars.get(i).getNumber());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Car car=new Car(name2.getText().toString(),model2.getText().toString(),number2.getText().toString());
                boolean result =myDb.update(car);
                if(result)
                { Toast.makeText(Main4Activity.this, " Data is updated", Toast.LENGTH_LONG).show();
                    finish();

                }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Main4Activity.this);


                builder.setMessage("are you sure you want to delete car");
                builder.setTitle("delet confirmation");


                builder.setNegativeButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String id= number2.getText().toString();
                        Car car=new Car(id);
                        int res = myDb.DeleteData(car);

                        Toast.makeText(Main4Activity.this, " تم حذف عنصر "+res, Toast.LENGTH_LONG).show();
                        finish();

                    }
                });


                builder.setPositiveButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();





            }
        });




    }
}
