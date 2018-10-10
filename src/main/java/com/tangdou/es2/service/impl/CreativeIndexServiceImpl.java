package com.tangdou.es2.service.impl;

import com.google.common.collect.Lists;
import com.tangdou.es2.entity.CreativeIndex;
import com.tangdou.es2.repository.CreativeIndexRepository;
import com.tangdou.es2.service.CreativeIndexService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreativeIndexServiceImpl implements CreativeIndexService {

    @Autowired
    private CreativeIndexRepository creativeIndexRepository;

    @Override
    public CreativeIndex getOne(Integer id) {
//        CreativeIndex creativeIndexOptional = creativeIndexRepository.findById(id).get();
        return null;
    }

    @Override
    public List<CreativeIndex> getOneT() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("country_id", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("province_id", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("city_id", "0"));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        FieldSortBuilder sortBuilder = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        nativeSearchQueryBuilder.withSort(sortBuilder);
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        return Lists.newArrayList(creativeIndexRepository.search(query));
    }

    @Override
    public List<CreativeIndex> getAll() {
        Iterable<CreativeIndex> indexIterable = creativeIndexRepository.findAll();
        return Lists.newArrayList(indexIterable);
    }

    @Override
    public List<CreativeIndex> findAllCreativeIndex() {
        return null;
    }


}
