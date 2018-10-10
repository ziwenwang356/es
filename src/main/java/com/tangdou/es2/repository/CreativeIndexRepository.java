package com.tangdou.es2.repository;

import com.tangdou.es2.entity.CreativeIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CreativeIndexRepository extends ElasticsearchRepository<CreativeIndex, Integer> {

    @Query("{\"query\" : {\"query\" : {\"match_all\" : {} }}")
    List<CreativeIndex> findAllCreativeIndex();
}
