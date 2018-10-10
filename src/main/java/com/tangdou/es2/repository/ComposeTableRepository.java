package com.tangdou.es2.repository;

import com.tangdou.es2.entity.ComposeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposeTableRepository extends JpaRepository<ComposeTable,Long> {

    ComposeTable findByProvincename(String name);
}
