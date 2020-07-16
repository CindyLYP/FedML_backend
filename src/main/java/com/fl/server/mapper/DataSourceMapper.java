package com.fl.server.mapper;

import com.fl.server.object.Clients;
import com.fl.server.object.Feature;

import java.util.ArrayList;

public interface DataSourceMapper {
    ArrayList<Feature> selectAllFeatures();
    ArrayList<Clients> selectAllClients();
}
