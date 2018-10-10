package com.tangdou.es2.service;

import com.tangdou.es2.entity.CreativeIndex;

import java.util.List;

public interface CreativeIndexService {

    CreativeIndex getOne(Integer id);

    List<CreativeIndex> getOneT();

    List<CreativeIndex> getAll();

    List<CreativeIndex> findAllCreativeIndex();




}
