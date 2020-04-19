package com.truongdx8.androidproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    private DBHelper db;
    List<UserItem> userList = new ArrayList<>();
    UserAdapter adapter;
    ListView lvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        db = new DBHelper(this);
        db.getWritableDatabase();

        getAllUser();

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int _id = userList.get(position).getId();

                Intent intent = new Intent(UserListActivity.this,UserUpdateActivity.class);
                intent.putExtra("identityValue",_id);
                startActivity(intent);
            }
        });
    }

    private void getAllUser()
    {
        userList = db.getData();
        adapter = new UserAdapter(this,userList);

        lvUser = findViewById(R.id.lvListUser);
        lvUser.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getAllUser();

        db.close();
    }
}
