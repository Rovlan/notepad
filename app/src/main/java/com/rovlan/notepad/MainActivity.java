package com.rovlan.notepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();

    EditText etFileData;
    Button bSave;
    Button bLoad;
    Button bNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFileData = (EditText) findViewById(R.id.etFileData);
        bSave=(Button) findViewById(R.id.bSave);
        bLoad=(Button) findViewById(R.id.bLoad);
        bNew=(Button) findViewById(R.id.bNew);
        //getText().toString();

        bLoad.setOnClickListener(new View.OnClickListener() {


            public void doAll() throws IOException {
                File f = null;//new File(getFilesDir(), "input.txt");
                try {
                    Log.d(TAG, "File to load: " + f.getAbsolutePath());
                }catch(NullPointerException e){
                        e.printStackTrace();
                 }
                //if(f.exists()) {
                //try ()) {
                InputStreamReader rdr = new InputStreamReader(new FileInputStream(f));
                char[] buffer = new char[1024];
                int len = 0;
                StringBuilder sb = new StringBuilder();
                while ((len = rdr.read(buffer)) > 0) {
                    sb.append(buffer, 0, len);
                }
                etFileData.setText(sb.toString());

            }


            @Override
            public void onClick(View v) {
                File f = null ;// new File(getFilesDir(), "input.txt");
                try {
                    Log.d(TAG, "File to load: " + f.getAbsolutePath());
                }catch(NullPointerException e){
                    e.printStackTrace();
                }
                InputStreamReader rdr=null;
                FileInputStream fis = null;
                    try {
                        fis =new FileInputStream(f);
                        rdr = new InputStreamReader(fis);
                        char[] buffer = new char[1024];
                        int len = 0;
                        StringBuilder sb = new StringBuilder();
                        while ((len = rdr.read(buffer)) > 0) {
                            sb.append(buffer, 0, len);
                        }
                        etFileData.setText(sb.toString());

                }  catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally{
                        if(fis!=null){
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                //}
            }
        });
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(getFilesDir(), "input.txt");
                try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(f))){
                    writer.write(etFileData.getText().toString());
                }catch  (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
