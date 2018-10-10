package com.tangdou.es2.repository;

import com.tangdou.es2.entity.CreativeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreativeInfoRepository extends JpaRepository<CreativeInfo,Long> {

    List<CreativeInfo> findByCampaignid(Integer campaignId);
    CreativeInfo findBySubscribeid(Integer subscribeId);
}
