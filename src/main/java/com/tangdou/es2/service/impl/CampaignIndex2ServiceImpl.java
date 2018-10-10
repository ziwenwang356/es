//package com.tangdou.es2.service.impl;
//
//import com.tangdou.es2.entity.CampaignIndex2;
//import com.tangdou.es2.repository.CampaignIndex2Repository;
//import com.tangdou.es2.service.CampaignIndex2Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CampaignIndex2ServiceImpl implements CampaignIndex2Service {
//
//    @Autowired
//    CampaignIndex2Repository campaignIndex2Repository;
//
//    @Override
//    public CampaignIndex2 createOne(CampaignIndex2 campaignIndex2) {
//        CampaignIndex2 campaignIndex21 = new CampaignIndex2();
//        campaignIndex21.setCampaignid(campaignIndex2.getCampaignid());
//        campaignIndex21.setCampaignname(campaignIndex2.getCampaignname());
//        campaignIndex21.setUserid(campaignIndex2.getUserid());
//        campaignIndex21.setStarttime(campaignIndex2.getStarttime());
//        campaignIndex21.setEndtime(campaignIndex2.getEndtime());
//        campaignIndex21.setBudget(campaignIndex2.getBudget());
//        campaignIndex21.setBudgettype(campaignIndex2.getBudgettype());
//        campaignIndex21.setBudgetover(campaignIndex2.getBudgetover());
//        campaignIndex21.setState(campaignIndex2.getState());
//        campaignIndex21.setAdduserid(campaignIndex2.getAdduserid());
//        campaignIndex2Repository.save(campaignIndex21);
//        return campaignIndex21;
//    }
//
//    @Override
//    public List<CampaignIndex2> findByCampaignname(String name) {
//        return campaignIndex2Repository.findByCampaignname(name);
//    }
//
//    @Override
//    public List<CampaignIndex2> findTopByBudget(Integer budget) {
//        return campaignIndex2Repository.findTopByBudget(budget);
//    }
//
//    @Override
//    public List<CampaignIndex2> findByBudgetBetween(Integer start, Integer end) {
//        return null;
//    }
//}
