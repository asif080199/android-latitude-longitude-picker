package cubi.rafi.locationpickertest;

import com.wimagguc.locationpicker.LocationPickerActivity;
import com.wimagguc.locationpicker.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final int REQUEST_CODE = 1;
	
	private TextView mResultView;
	
	private float mLatitude = 23.726f;
	private float mLongitude = 90.393f;
	private String mLocationName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mResultView = (TextView) findViewById(R.id.result);
	}
	
	public void onPickLocationClick(View view) {
		Intent intent = new Intent(this, LocationPickerActivity.class);
		
		// The map will be initialized with this latitude and longitude (Optional)
		intent.putExtra(LocationPickerActivity.LATITUDE, mLatitude);
		intent.putExtra(LocationPickerActivity.LONGITUDE, mLongitude);
		
		startActivityForResult(intent, REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				// Get your selected location
				mLatitude = data.getFloatExtra(LocationPickerActivity.LATITUDE, 0);
				mLongitude = data.getFloatExtra(LocationPickerActivity.LONGITUDE, 0);
				mLocationName = data.getStringExtra(LocationPickerActivity.LOCATION_NAME);
				
				mResultView.setText("Latitude = " + mLatitude
								+ "\nLongitude = " + mLongitude
								+ "\nLocation = " + mLocationName);
			}
		}
	}

}
