package com.example.loggg;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg,_btnlogin;
    EditText _txtfname, _txtlname, _txtpass, _txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new DatabaseHelper(this);
        _btnreg = (Button)findViewById(R.id.btn_register);
        _txtfname = (EditText) findViewById(R.id.rt_fname);
        _txtlname = (EditText) findViewById(R.id.rt_lname);
        _txtpass = (EditText) findViewById(R.id.rt_password);
        _txtemail = (EditText) findViewById(R.id.rt_email);
        _btnlogin = (Button)findViewById(R.id.rt_login);

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname = _txtfname.getText().toString();
                String lname= _txtlname.getText().toString();
                String email= _txtemail.getText().toString();
                String pass= _txtpass.getText().toString();
                insertdata(fname, lname, pass, email);
                Toast.makeText(getApplicationContext(), "Registered Successfully",Toast.LENGTH_LONG).show();
            }
        });

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });

    }

    public void insertdata(String fname,String lname, String pass, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, fname);
        contentValues.put(DatabaseHelper.COL_3, lname);
        contentValues.put(DatabaseHelper.COL_4, email);
        contentValues.put(DatabaseHelper.COL_5, pass);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}
