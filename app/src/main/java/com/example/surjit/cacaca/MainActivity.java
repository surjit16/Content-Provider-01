package com.example.surjit.cacaca;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etname;
    Button bsave;

    ContentValues cv = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname = findViewById(R.id.et11);
        bsave = findViewById(R.id.but11);
        bsave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.put("name", etname.getText().toString());

                Uri uri = getContentResolver().insert(MyContentProvider.content_uri, cv);

                Toast.makeText(MainActivity.this, uri.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
