package com.example.googlemap;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

public class MainActivity extends Activity {
	private GoogleMap mMap;
	private MapView mMapView;
	static final LatLng MELBOURNE = new LatLng(37.35, -122.0);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try
		{
			MapsInitializer.initialize(this);
		}
		catch (GooglePlayServicesNotAvailableException e)
		{
			e.printStackTrace();
		}
		
		GoogleMapOptions options = new GoogleMapOptions();
		options.mapType(GoogleMap.MAP_TYPE_NORMAL);
		options.camera(new CameraPosition(MELBOURNE, 14, 0, (float)112.5)); 
		mMapView = new MapView(this, options);
		mMapView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); 
		mMapView.onCreate(savedInstanceState);
		setContentView(mMapView);
		mMap = mMapView.getMap();
		
		Marker melbourne = mMap.addMarker(new MarkerOptions().draggable(true).position(MELBOURNE).title("Melbourne")
				.snippet("Population: 4,137,400")
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconmarka)));
//		melbourne.setDraggable(true);
		
		mMap.setOnMarkerClickListener(new OnMarkerClickListener()
		{
			
			@Override
			public boolean onMarkerClick(Marker arg0)
			{
				Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		mMap.setOnMarkerDragListener(new OnMarkerDragListener()
		{
			
			@Override
			public void onMarkerDragStart(Marker arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onMarkerDragEnd(Marker arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onMarkerDrag(Marker arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener()
		{
			
			@Override
			public void onInfoWindowClick(Marker arg0)
			{
				// TODO Auto-generated method stub
				arg0.setVisible(false);
			}
		});
		
		mMap.setInfoWindowAdapter(new InfoWindowAdapter()
		{
			
			@Override
			public View getInfoWindow(Marker arg0)
			{
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public View getInfoContents(Marker arg0)
			{
				// TODO Auto-generated method stub
				return null;
			}
		});
		
//		mMap.addMarker(new MarkerOptions().position(new LatLng(22.545033, 113.924752)).title("深圳市南山区玉泉路附近"));
		UiSettings us = mMap.getUiSettings();
		us.setZoomControlsEnabled(false);
		us.setZoomGesturesEnabled(true);
		us.setScrollGesturesEnabled(true);
		us.setCompassEnabled(false);
		us.setRotateGesturesEnabled(true);
		us.setTiltGesturesEnabled(true);
		
//		// Instantiates a new Polyline object and adds points to define a rectangle
//		PolylineOptions rectOptions = new PolylineOptions()
//		        .add(new LatLng(37.35, -122.0))
//		        .add(new LatLng(37.45, -122.0))  // North of the previous point, but at the same longitude
//		        .add(new LatLng(37.45, -122.2))  // Same latitude, and 30km to the west
//		        .add(new LatLng(37.35, -122.2))  // Same longitude, and 16km to the south
//		        .add(new LatLng(37.35, -122.0)); // Closes the polyline.
//
//		// Get back the mutable Polyline
//		Polyline polyline = mMap.addPolyline(rectOptions);
		
		// Instantiates a new Polygon object and adds points to define a rectangle
		PolygonOptions rectOptions = new PolygonOptions()
		              .add(new LatLng(37.35, -122.0),
		                   new LatLng(37.45, -122.0),
		                   new LatLng(37.45, -122.2),
		                   new LatLng(37.35, -122.2),
		                   new LatLng(37.35, -122.0));

		// Get back the mutable Polygon
		Polygon polygon = mMap.addPolygon(rectOptions);
		
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		mMapView.onResume();
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		mMapView.onPause();
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		mMapView.onDestroy();
	}
}