package com.fl.server.mapper;

import com.fl.server.pojo.Node;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface NodeMapper {
    ArrayList<Node> getAllNode();
    ArrayList<Node> findNode(@Param("nodeName") String nodeName);
    boolean insert(@Param("node") Node node);
    boolean update(@Param("node") Node node, String old_nodeName);
    boolean delete(@Param("nodeName") String nodeName);
}
