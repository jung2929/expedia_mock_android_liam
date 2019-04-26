package com.example.expedia.cluster;

import android.content.Context;

import com.example.expedia.entities.HotelClusteringItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class MarkerClusterRenderer<T extends ClusterItem> extends DefaultClusterRenderer<HotelClusteringItem> {

    public MarkerClusterRenderer(Context context, GoogleMap map, ClusterManager<HotelClusteringItem> clusterManager) {
        super(context, map, clusterManager);
    }
    @Override
    protected boolean shouldRenderAsCluster (Cluster cluster){
        return cluster.getSize() >= 2;
    }

    @Override
    protected void onBeforeClusterItemRendered(HotelClusteringItem item, MarkerOptions markerOptions) {
        markerOptions.title(item.getName());
        super.onBeforeClusterItemRendered(item, markerOptions);
    }
}
