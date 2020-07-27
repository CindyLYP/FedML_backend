package com.fl.server.mapper.old;

import com.fl.server.object.old.Client;
import com.fl.server.object.old.Feature;

import java.util.ArrayList;

public interface DataSourceMapper {
    ArrayList<Feature> selectAllFeatures();
    ArrayList<Client> selectAllClients();
}
