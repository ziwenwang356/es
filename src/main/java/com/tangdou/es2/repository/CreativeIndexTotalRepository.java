package com.tangdou.es2.repository;

import com.tangdou.es2.entity.CreativeIndexTotal;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CreativeIndexTotalRepository extends ElasticsearchRepository<CreativeIndexTotal, Long> {


    @Query("{ \"bool\" :{ \"must\" :{ \"field\" : {\"adtype\" : \"?\"} }}}")
    List<CreativeIndexTotal> findByAdtype(Integer adtype);


//    List<CreativeIndexTotal> findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3(Integer adtype, String positionname, String region1, String region2, String region3);


//    {"sort" : [{ "adtype" : {"order" : "asc"} }],"bool" : {"must" : [ {"field" : {"adtype" : "?"}}, {"field" : {"positionname" : "?"}}, {"should" : [ {"field" : {"region1" : "?"}}, {"field" : {"region2" : "?"}},{"field" : {"region3" : "?"}} ]} ]}}

//
//    @Query("{\"sort\" : [{ \"adtype\" : {\"order\" : \"asc\"} }],\"query\": { \"bool\" : {\"must\" : [ {\"field\" : {\"adtype\" : \"?\"}}, {\"field\" : {\"positionname\" : \"?\"}}, {\"should\" : [ {\"field\" : {\"region1\" : \"?\"}}, {\"field\" : {\"region2\" : \"?\"}},{\"field\" : {\"region3\" : \"?\"}} ]} ]}}}")
//    List<CreativeIndexTotal> findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo(Integer adtype, String positionname, String region1, String region2, String region3);
//

    @Query("{\"query\" : {\"query\" : {\"match_all\" : {} }}")
    List<CreativeIndexTotal> findAllData();


//    List<CreativeIndexTotal> findByPositionname(String positionname);

    List<CreativeIndexTotal> findByRegion2(String region2);

    List<CreativeIndexTotal> findByRegion3(String region3);


    List<CreativeIndexTotal> findByAdtypeOrPositionidOrRegion1OrRegion2OrRegion3(Integer adtype, Integer positionid, String region1, String region2, String region3);

    List<CreativeIndexTotal> findByAdtypeAndPositionidAndRegion2AndRegion3(Integer adtype, Integer positionid, String province,String city);

    List<CreativeIndexTotal> findByAdtypeInAndPositionidInAndRegion2InAndRegion3InOrderByAdtypeDesc(Integer[] adtype,Integer[] positionid,String[] province,String[] city);

    List<CreativeIndexTotal> findByRegion3InAndRegion2InAndPositionidInAndAdtypeIn(String[]city,String[]province,Integer[]positionid,Integer[]adtype);

    List<CreativeIndexTotal> findByAdtypeInAndPositionidInAndRegion2InAndRegion3InAndCreativetypeInAndLocalindustryInAndNetworkInAndClientInAndFrequencyInAndCrowdInAndPriceidIn(
            Integer[] adtype,Integer[] positionid,String[] province,String[] city,Integer[]creativetype,Integer[]localindustry,Integer[]network,Integer[]client,Integer[]frequency,
            Integer[]crowd,Integer[]priceid
    );

    List<CreativeIndexTotal> findByRegion3In(List<String>city);

    List<CreativeIndexTotal> findByRegion3InAndRegion2In(String[]city,String[]province);

    List<CreativeIndexTotal> findByAdtypeIn(Integer[]adtype);

    List<CreativeIndexTotal> findByAdtypeInAndPositionidIn(Integer[]adtype,Integer[]positionid);
}
