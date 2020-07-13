package com.fl.server.mapper;

import com.fl.server.object.Clients;
import com.fl.server.object.Features;

import java.util.ArrayList;

public interface DataSourceMapper {
    ArrayList<Features> selectAllFeatures();
    ArrayList<Clients> selectAllClients();
}
