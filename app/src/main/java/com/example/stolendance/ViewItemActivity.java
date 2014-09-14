package com.example.stolendance;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;

public class ViewItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_item);

        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("itemData");

        try {
            JSONArray items = new JSONArray(jsonArray);
            String imageName = items.getJSONObject(0).getString("image_id");
            int resourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());

            ImageView i = (ImageView) findViewById(R.id.imageView);
            i.setImageResource(resourceId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_item, menu);
		return true;
	}

}
