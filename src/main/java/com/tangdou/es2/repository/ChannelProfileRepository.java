package com.tangdou.es2.repository;

import com.tangdou.es2.entity.ChannelProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelProfileRepository extends JpaRepository<ChannelProfile,Integer> {
}
