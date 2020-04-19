package com.truongdx8.androidproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    private DBHelper db;
    UserItem user = new UserItem();

    EditText tvName;
    EditText tvGender;
    EditText tvDescription;
    Button btUpdate;
    Button btDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        db = new DBHelper(this);
        db.getWritableDatabase();

        initView();
    }

    private void initView()
    {
        Intent intent = getIntent();
        int identity = intent.getIntExtra("identityValue",0);

        user = db.getItem(identity);

        tvName = findViewById(R.id.etUserName);
        tvGender = findViewById(R.id.etGender);
        tvDescription = findViewById(R.id.etDescription);

        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(this);
        btDelete = findViewById(R.id.btDelete);
        btDelete.setOnClickListener(this);

        tvName.setText(user.getName());
        tvGender.setText(user.getGender());
        tvDescription.setText(user.getDescription());
    }

    private UserItem newUserItem()
    {
        UserItem newUser = new UserItem(user.getId(),tvName.getText().toString(),tvGender.getText().toString(),tvDescription.getText().toString());
        return newUser;
    }

    private void onUpdate(UserItem userItem)
    {
        String mes = db.updateData(userItem);
        Toast.makeText(this,mes, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void onDelete(int id)
    {
        String mes = db.deleteData(id);
        Toast.makeText(this,mes, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btUpdate:
                onUpdate(newUserItem());
                break;
            case R.id.btDelete:
                onDelete(user.getId());
                break;
            default:
                break;
        }
    }
}
