package com.tangdou.es2.service.impl;


import com.tangdou.es2.entity.ComposeTable;
import com.tangdou.es2.repository.ComposeTableRepository;
import com.tangdou.es2.service.ComposeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComposeTableServiceImpl implements ComposeTableService {


    @Autowired
    private ComposeTableRepository composeTableRepository;


    @Override
    public List<ComposeTable> getAllData() {
        return composeTableRepository.findAll();
    }

    @Override
    public ComposeTable getOne(Long id) {
        return composeTableRepository.getOne(id);
    }

    @Override
    public ComposeTable findByProvincename(String name) {
        return composeTableRepository.findByProvincename(name);
    }
}
