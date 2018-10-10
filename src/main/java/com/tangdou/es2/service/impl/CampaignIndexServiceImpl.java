//package com.tangdou.es2.service.impl;
//
//import com.google.common.collect.Lists;
//import com.tangdou.es2.entity.CampaignIndex;
//import com.tangdou.es2.repository.CampaignIndexRepository;
//import com.tangdou.es2.service.CampaignIndexService;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class CampaignIndexServiceImpl implements CampaignIndexService {
//
//
//    @Autowired
//    private CampaignIndexRepository campaignIndexRepository;
//
//
//    @Override
//    public void createCampaignIndex(CampaignIndex campaignIndex) {
//        CampaignIndex campaignIndex1 = new CampaignIndex();
//        campaignIndex1.setCampaign_id(campaignIndex.getCampaign_id());
//        campaignIndex1.setCampaign_name(campaignIndex.getCampaign_name());
//        campaignIndex1.setUser_id(campaignIndex.getUser_id());
//        campaignIndex1.setStart_time(campaignIndex.getStart_time());
//        campaignIndex1.setEnd_time(campaignIndex.getEnd_time());
//        campaignIndex1.setBudget(campaignIndex.getBudget());
//        campaignIndex1.setBudget_type(campaignIndex.getBudget_type());
//        campaignIndex1.setBudgetover(campaignIndex.getBudgetover());
//        campaignIndex1.setState(campaignIndex.getState());
//        campaignIndex1.setAdd_user_id(campaignIndex.getAdd_user_id());
//        campaignIndexRepository.save(campaignIndex1);
//    }
//
//    @Override
//    public void createCampaignIndex2() {
//        CampaignIndex campaignIndex1 = new CampaignIndex();
//        campaignIndex1.setCampaign_id(1);
//        campaignIndex1.setCampaign_name("testCampaign");
//        campaignIndex1.setUser_id(1989898L);
//        campaignIndex1.setStart_time(new Date(System.currentTimeMillis()));
//        campaignIndex1.setEnd_time(new Date(System.currentTimeMillis()));
//        campaignIndex1.setBudget(1000);
//        campaignIndex1.setBudget_type(0);
//        campaignIndex1.setBudgetover(0);
//        campaignIndex1.setState(0);
//        campaignIndex1.setAd_type(2);
//        campaignIndex1.setAdd_user_id("191919191919");
//        campaignIndexRepository.save(campaignIndex1);
//    }
//
//
//    @Override
//    public List<CampaignIndex> findByCampaignState(Integer state) {
//        return campaignIndexRepository.findByState(state);
//    }
//
//    @Override
//    public List<CampaignIndex> findByBudgetLessThan(Integer budget) {
//
//        return campaignIndexRepository.findByBudgetLessThan(budget);
//    }
//
//    @Override
//    public List<CampaignIndex> findByCampaign_nameIs(String campaignName) {
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.termQuery("campaign_id",40));
//        boolQueryBuilder.must(QueryBuilders.termQuery("campaign_name", campaignName));
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
//        NativeSearchQuery query = nativeSearchQueryBuilder.build();
//        return Lists.newArrayList(campaignIndexRepository.search(query));
//    }
//
//
//}
