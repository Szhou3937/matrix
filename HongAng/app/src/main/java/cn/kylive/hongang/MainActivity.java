package cn.kylive.hongang;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HongAng";
    public LocationClient mLocationClient;
    private TextView positionText;
    private MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        setContentView(R.layout.activity_main);
        positionText = (TextView) findViewById(R.id.position_text_view);
        positionText.setText("333");
        Log.i(TAG, "onCreate: positon");
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()){
            String [] permissons = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissons, 1);
        }else{

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestLocation();
    }

    private void requestLocation(){
        Log.d(TAG, "requestLocation: START to Get Location.");
        if(mLocationClient != null) {
           initLocation();
            mLocationClient.start();
        }
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(3000);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        option.setNeedDeviceDirect(false);
        option.setLocationNotify(false);
        option.setIgnoreKillProcess(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        option.SetIgnoreCacheException(false);

        option.setIsNeedAltitude(false);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    for (int result : grantResults){
                       if (result != PackageManager.PERMISSION_GRANTED){
                           Toast.makeText(this, "必须授权才能使用", Toast.LENGTH_SHORT).show();
                           finish();
                           return;
                       }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                default:
                    break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mLocationClient != null) {
            mLocationClient.unRegisterLocationListener(myLocationListener);
            if(mLocationClient.isStarted()){
                mLocationClient.stop();
            }
            Log.d(TAG, "onStop: STOP to get location.");
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            Log.d(TAG, "onReceiveLocation: get location.");
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("经度").append(location.getLatitude()).append("\n");
            currentPosition.append("纬度").append(location.getLongitude()).append("\n");
            currentPosition.append("定位方式");
            if(location.getLocType() == BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");
            }else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
                currentPosition.append("网络");
            }
            positionText.setText(currentPosition.toString());

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
