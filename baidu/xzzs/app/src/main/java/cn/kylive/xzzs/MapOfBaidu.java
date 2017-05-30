package cn.kylive.xzzs;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;

/**
 * Created by Administrator on 2017/5/30 0030.
 */

public class MapOfBaidu {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private BitmapDescriptor mCurrentMarker= null;
    private MyLocationConfiguration.LocationMode mCurrentMode= null;

    //定位相关
    private LocationClient mLocationClient = null;
    private BDLocation mLocation = null;
    private BDLocationListener myListener = null;
    private Context appContext= null;
    private String[] mPermissionStrAry = null;



}
