package com.fl.server.mapper;

import com.fl.server.pojo.DataDict;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface DataDictMapper {
    ArrayList<DataDict> getAllDataDict();
    ArrayList<DataDict> selectByProvider(@Param("provider") String provider);
    ArrayList<DataDict> selectLabelByProvider(@Param("provider") String provider);

}
