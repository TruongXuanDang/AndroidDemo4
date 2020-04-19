package com.truongdx8.androidproject4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FilesActivity extends AppCompatActivity implements View.OnClickListener{

    public final static String root = Environment.getExternalStorageDirectory().toString();
    public final static String FILE_NAME = "note.txt";
    public final static String FILE_PATH = root+"/Demo";
    private static final int PERMISSION_REQUEST_CODE = 1;

    EditText edText;
    Button btSave;
    Button btRead;
    RadioGroup radGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        btRead=findViewById(R.id.btRead);
        edText=findViewById(R.id.edMultiLineText);
        btSave=findViewById(R.id.btSaveButton);
        radGroup=findViewById(R.id.rgGroup);

        btSave.setOnClickListener(this);
        btRead.setOnClickListener(this);
    }

    private void checkPermission() {
        int result = ContextCompat.checkSelfPermission(FilesActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(FilesActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    private void onSaveInternal()
    {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
            fos.write(edText.getText().toString().getBytes());
            fos.flush();
            fos.close();
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onReadInternal()
    {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuffer sb = new StringBuffer();
            String line  = reader.readLine();
            while (line!=null)
            {
                sb.append(line);
                line = reader.readLine();
            }

            Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onSaveExternal()
    {
        File directory = new File(FILE_PATH);
        File file = new File(FILE_PATH+"/"+FILE_NAME);
        try {
            if(!directory.exists())
            {
                directory.mkdir();
            }

            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(edText.getText().toString().getBytes());
            fos.flush();
            fos.close();
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private void onReadExternal()
    {
        File file = new File(FILE_PATH+"/"+FILE_NAME);
        try {

            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuffer sb = new StringBuffer();
            String line  = reader.readLine();
            while (line!=null)
            {
                sb.append(line);
                line = reader.readLine();
            }

            Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        checkPermission();

        int idChecked = radGroup.getCheckedRadioButtonId();
        switch (v.getId())
        {
            case R.id.btSaveButton:
                if(idChecked == R.id.radInternal)
                {
                    onSaveInternal();
                }
                else if (idChecked == R.id.radExternal)
                {
                    onSaveExternal();
                }
                break;
            case R.id.btRead:
                if(idChecked == R.id.radInternal)
                {
                    onReadInternal();
                }
                else if (idChecked == R.id.radExternal)
                {
                    onReadExternal();
                }
                break;
        }
    }
}
