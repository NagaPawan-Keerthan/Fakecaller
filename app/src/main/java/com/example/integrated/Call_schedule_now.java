package com.example.integrated;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Call_schedule_now extends AppCompatActivity {

    EditText text;
    Button btn1,btn2;
    private static final int RESULT_PICK_CONTACT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_schedule_now);

        text = findViewById(R.id.txtno);
        btn1 = findViewById(R.id.btncall);
        btn2 = findViewById(R.id.cont);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String no = text.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +no));
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(i,RESULT_PICK_CONTACT);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    ContactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(this, "Failed to pickup", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void ContactPicked(Intent data){
        Cursor cursor = null;

        try {
            String PhoneNo = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri,null,null,null);
            cursor.moveToFirst();
            int PhoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            PhoneNo = cursor.getString(PhoneIndex);
            text.setText(PhoneNo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}