package com.wimagguc.locationpicker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

public class LocationPickerActivity extends Activity {

	private WebView locationPickerView;
	private EditText searchText;

	private Float latitude = 0f;
	private Float longitude = 0f;
	private Integer zoom = 5;
	private String locationName;
	
	public static final String LATITUDE = "lat";
	public static final String LONGITUDE = "lang";
	public static final String LOCATION_NAME = "location_name";

	@Override
	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_picker);

		if (savedInstanceState != null) {
			latitude = savedInstanceState.getFloat("latitude");
			longitude = savedInstanceState.getFloat("longitude");
			zoom = savedInstanceState.getInt("zoom");
			locationName = savedInstanceState.getString("locationName");
		}
		else {
			// If a default latitude/longitude value has already been passed to it
			// through intent, then load that value
			latitude = getIntent().getFloatExtra(LATITUDE, 0.0f);
			longitude = getIntent().getFloatExtra(LONGITUDE, 0.0f);
		}

		// LOCATION PICKER WEBVIEW SETUP
		locationPickerView = (WebView) findViewById(R.id.locationPickerView);
		locationPickerView.setScrollContainer(false);
		locationPickerView.getSettings().setDomStorageEnabled(true);
		locationPickerView.getSettings().setJavaScriptEnabled(true);
		locationPickerView.addJavascriptInterface(new LocationPickerJSInterface(), "AndroidFunction");
		
		locationPickerView.loadUrl("file:///android_asset/locationPickerPage/index.html");

		locationPickerView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int progress) {
				if (progress == 100) {
					locationPickerView.loadUrl("javascript:activityInitialize(" + latitude + "," + longitude + "," + zoom + ")");
				}
			}
		});
		// ^^^
		
		// EVENT HANDLER FOR PERFORMING SEARCH IN WEBVIEW
		searchText = (EditText) findViewById(R.id.searchText);
		searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				locationPickerView.loadUrl("javascript:if (typeof activityPerformSearch == 'function') {activityPerformSearch(\"" + searchText.getText().toString() + "\")}");
				return true;
			}
		});
		// ^^^
	}

	public class LocationPickerJSInterface {
		@JavascriptInterface 
		public void getValues(String latitude, String longitude, String zoom, String locationName){
			LocationPickerActivity.this.latitude = Float.parseFloat(latitude);
			LocationPickerActivity.this.longitude = Float.parseFloat(longitude);
			LocationPickerActivity.this.zoom = Integer.parseInt(zoom);
			LocationPickerActivity.this.locationName = locationName;
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putFloat("latitude", latitude);
		outState.putFloat("longitude", longitude);
		outState.putInt("zoom", zoom);
		outState.putString("locationName", locationName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.location_picker, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_ok) {
			
		}
		return false;
	}
	
	public void onOkClick(MenuItem item) {
		Intent data = new Intent();
		data.putExtra(LATITUDE, latitude);
		data.putExtra(LONGITUDE, longitude);
		data.putExtra(LOCATION_NAME, locationName);
		setResult(Activity.RESULT_OK, data);
		finish();
	}
	
	public void onZoomInClick(MenuItem item) {
		locationPickerView.loadUrl("javascript:activityPerformZoom(1)");
	}
	
	public void onZoomOutClick(MenuItem item) {
		locationPickerView.loadUrl("javascript:activityPerformZoom(-1)");
	}
}
