package com.tangdou.es2.service;

import com.tangdou.es2.entity.ComposeTable;

import java.util.List;

public interface ComposeTableService {
    List<ComposeTable> getAllData();

    ComposeTable getOne(Long id);

    ComposeTable findByProvincename(String name);
}
