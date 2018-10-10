//package com.tangdou.es2.repository;
//
//import com.tangdou.es2.entity.CampaignIndex;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//public interface CampaignIndexRepository extends ElasticsearchRepository<CampaignIndex,Integer> {
//
//    List<CampaignIndex> findByState(Integer state);
//
//    List<CampaignIndex> findByBudgetLessThan(Integer budget);
//
////    List<CampaignIndex> findByCampaign_nameIs(String campaignName);
//
//}
