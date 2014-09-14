package com.example.stolendance;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Called when the user scans bar code */
    public void getScannedItem(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, ViewItemActivity.class);
        JSONArray items = this.getItemData("reference"); //should be the reference from scanner
        intent.putExtra("itemData", items.toString());
    	startActivity(intent);
    }

    private JSONArray getItemData(String reference)
    {
        InputStream inputStream = getResources().openRawResource(R.raw.data);
        ByteArrayOutputStream byteArrayOutputStream = this.getByteStream(inputStream);
        JSONArray items = new JSONArray();
        try {
            // Parse the data into jsonobject to get original data in form of json.
            JSONObject jObject = new JSONObject(
                    byteArrayOutputStream.toString());
            items = jObject.getJSONArray("Items");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;

    }

    private ByteArrayOutputStream getByteStream(InputStream inputStream)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }
}
