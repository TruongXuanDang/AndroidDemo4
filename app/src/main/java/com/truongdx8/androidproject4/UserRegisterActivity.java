package com.truongdx8.androidproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UserRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUserName;
    private Spinner spGender;
    private EditText etDescription;
    private CheckBox ck;
    private Button btRegister;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        db = new DBHelper(this);
        db.getReadableDatabase();

        initView();
    }

    private void initView()
    {
        String[] genders = {"Male","Female","Unknown"};

        etUserName = (EditText) findViewById(R.id.etUserName);
        spGender = (Spinner) findViewById(R.id.spGender);
        etDescription = (EditText) findViewById(R.id.etDescription);
        ck = (CheckBox) findViewById(R.id.ck);
        btRegister = (Button) findViewById(R.id.btRegister);
        btRegister.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spGender.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v == btRegister)
            onRegister();
    }

    private void onRegister()
    {
        if(etUserName.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Please enter username",Toast.LENGTH_LONG).show();
        }
        else if(!ck.isChecked())
        {
            Toast.makeText(this,"Please agree rules",Toast.LENGTH_LONG).show();
        }
        else {
            UserItem user = new UserItem(etUserName.getText().toString(),spGender.getSelectedItem().toString(),etDescription.getText().toString());
            String isAdd = db.insertData(user);
            Toast.makeText(this,isAdd,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(UserRegisterActivity.this,UserListActivity.class);
            startActivity(intent);
        }
    }
}
