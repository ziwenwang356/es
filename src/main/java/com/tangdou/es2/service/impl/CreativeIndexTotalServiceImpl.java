package com.tangdou.es2.service.impl;

import com.google.common.collect.Lists;
import com.tangdou.es2.entity.CreativeIndexTotal;
import com.tangdou.es2.repository.CreativeIndexTotalRepository;
import com.tangdou.es2.service.CreativeIndexTotalService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CreativeIndexTotalServiceImpl implements CreativeIndexTotalService {

    @Autowired
    private CreativeIndexTotalRepository creativeIndexTotalRepository;


    @Override
    public CreativeIndexTotal createOne(CreativeIndexTotal creativeIndexTotal) {
        CreativeIndexTotal creativeIndexTotal1 = creativeIndexTotalRepository.save(creativeIndexTotal);
        return creativeIndexTotal1;
    }

    @Override
    public List<CreativeIndexTotal> findByProvince(String province) {
        return creativeIndexTotalRepository.findByRegion2(province);
    }

    @Override
    public List<CreativeIndexTotal> findByCity(String city) {
        return creativeIndexTotalRepository.findByRegion3(city);
    }


    @Override
    public List<CreativeIndexTotal> findByType(Integer adtype) {
        return creativeIndexTotalRepository.findByAdtype(adtype);
    }

    // 如下是最终版本的入口，还需要加一个需求：and (position1 or position2 or ...)
    @Override
    public List<CreativeIndexTotal> findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3(Integer adtype, Integer positionid, String region1, String region2, String region3) {
        Map<Long, CreativeIndexTotal> map = new HashMap<>();
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalRepository.findByAdtypeOrPositionidOrRegion1OrRegion2OrRegion3(adtype, positionid, region1, region2, region3);
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            if (null == creativeIndexTotal) {
                continue;
            }
            Integer adtype2 = creativeIndexTotal.getAdtype();
            Integer positionid2 = creativeIndexTotal.getPositionid();
            String region2_2 = creativeIndexTotal.getRegion2();
            String region3_3 = creativeIndexTotal.getRegion3();
            Boolean flag = ((adtype == adtype2) && (positionid == positionid2) && ((region2.equalsIgnoreCase(region2_2)) || (region3.equalsIgnoreCase(region3_3))));
            if (!flag) {
                continue;
            }
            List<CreativeIndexTotal> temp = findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo2(creativeIndexTotal.getAdtype(), creativeIndexTotal.getPositionid(), creativeIndexTotal.getProvinceid(), creativeIndexTotal.getCityid());
            for (CreativeIndexTotal one : temp) {
                if (null == temp) {
                    continue;
                }
                if (null == map.get(one.getCreativeid())) {


                    map.put(one.getCreativeid(), one);
                }
            }
        }
        Iterator<Map.Entry<Long, CreativeIndexTotal>> entryIterator = map.entrySet().iterator();
        List<CreativeIndexTotal> result = new ArrayList<>();
        while (entryIterator.hasNext()) {
            Map.Entry<Long, CreativeIndexTotal> entry = entryIterator.next();
            result.add(entry.getValue());
        }
        return result;
    }

    @Override
    public List<CreativeIndexTotal> findByAdtypeAndPositionidOrRegion2idOrRegion3id(Integer adtype, Integer positionid, Integer provinceid, Integer cityid) {
        return null;
    }

    @Override
    public List<CreativeIndexTotal> findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo(Integer adtype, String positionname, String region1, String region2, String region3) {
        BoolQueryBuilder firt = QueryBuilders.boolQuery();
        firt.must(QueryBuilders.termQuery("adtype", adtype));
        firt.must(QueryBuilders.termQuery("positionname", positionname));
        BoolQueryBuilder second = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("region2", region2)).should(QueryBuilders.termQuery("region3", region3));
        BoolQueryBuilder third = new BoolQueryBuilder().must(firt).must(second);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(third).withSort(SortBuilders.fieldSort("adtype").order(SortOrder.ASC));
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        return Lists.newArrayList(creativeIndexTotalRepository.search(query));
    }


    @Override
    public List<CreativeIndexTotal> findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo2(Integer adtype, Integer positionid, Integer region2id, Integer region3id) {
        BoolQueryBuilder firt = QueryBuilders.boolQuery();
        firt.must(QueryBuilders.termQuery("adtype", adtype));
        firt.must(QueryBuilders.termQuery("positionid", positionid));
        BoolQueryBuilder second = QueryBuilders.boolQuery().should(QueryBuilders.termQuery("provinceid", region2id)).should(QueryBuilders.termQuery("cityid", region3id));
        BoolQueryBuilder third = new BoolQueryBuilder().must(firt).must(second);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(third).withSort(SortBuilders.fieldSort("adtype").order(SortOrder.ASC));
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        return Lists.newArrayList(creativeIndexTotalRepository.search(query));
    }

    // 国-->省-->市
    @Override
    public List<CreativeIndexTotal> findByAdtypeAndPositionnameAndRegion1AndRegion2AndRegion3(Integer adtype, Integer positionid, Integer region2id, Integer region3id) {
        BoolQueryBuilder firt = QueryBuilders.boolQuery();
        firt.must(QueryBuilders.termQuery("adtype", adtype));
        firt.must(QueryBuilders.termQuery("positionid", positionid));
        firt.must(QueryBuilders.termQuery("provinceid", region2id));
        firt.must(QueryBuilders.termQuery("cityid", region3id));
        BoolQueryBuilder third = new BoolQueryBuilder().must(firt);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(third).withSort(SortBuilders.fieldSort("adtype").order(SortOrder.ASC));
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        return Lists.newArrayList(creativeIndexTotalRepository.search(query));
    }

    //国-->省
    @Override
    public List<CreativeIndexTotal> findByAdtypeAndPositionnameAndRegion1AndRegion2(Integer adtype, Integer positionid, Integer region2id) {
        BoolQueryBuilder firt = QueryBuilders.boolQuery();
        firt.must(QueryBuilders.termQuery("adtype", adtype));
        firt.must(QueryBuilders.termQuery("positionid", positionid));
        firt.must(QueryBuilders.termQuery("provinceid", region2id));
        BoolQueryBuilder third = new BoolQueryBuilder().must(firt);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(third).withSort(SortBuilders.fieldSort("adtype").order(SortOrder.ASC));
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        return Lists.newArrayList(creativeIndexTotalRepository.search(query));
    }

    @Override
    public List<CreativeIndexTotal> findByAdtypeAndPositionnameAndProvinceAndCity(Integer adtype, Integer positionid, String province, String city) {
        return this.creativeIndexTotalRepository.findByAdtypeAndPositionidAndRegion2AndRegion3(adtype, positionid, province, city);
    }

    @Override
    public List<CreativeIndexTotal> findByAdtypeInAndPositionidInAndProvinceInAndCityIn(String adtype, String positionid, String province, String city) {
        if (null == adtype) {
            return new ArrayList<CreativeIndexTotal>();
        }
        if (null == positionid) {
            return new ArrayList<CreativeIndexTotal>();
        }
        if (null == province) {
            province = "";
        }
        if (null == city) {
            city = "";
        }
        String[] ad = adtype.split(",");
        int i = 0;
        Integer[] adInt = new Integer[ad.length];
        for (String s : ad) {
            adInt[i++] = Integer.parseInt(s);
        }
        String[] po = positionid.split(",");
        int j = 0;
        Integer[] poInt = new Integer[po.length];
        for (String s : po) {
            poInt[j++] = Integer.parseInt(s);
        }
        String[] pro = province.split(",");
        String[] ci = city.split(",");
        List<CreativeIndexTotal> result = new ArrayList<>();
        List<CreativeIndexTotal> finalResults = new ArrayList<>();
        result = this.creativeIndexTotalRepository.findByAdtypeInAndPositionidInAndRegion2InAndRegion3InOrderByAdtypeDesc(adInt, poInt, pro, ci);
        Map<Long, CreativeIndexTotal> map = new HashMap<>();
        for (CreativeIndexTotal c : result) {
            map.put(c.getCreativeid(), c);
        }
        List<Long> creativeIdArr = new ArrayList<>();
        List<Integer> listAdType = Arrays.asList(adInt);
        List<Integer> listPosition = Arrays.asList(poInt);
        List<String> listProvince = Arrays.asList(pro);
        List<String> listCity = Arrays.asList(ci);
        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listAdType.contains(entry.getValue().getAdtype())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listPosition.contains(entry.getValue().getPositionid())) {
                creativeIdArr.add(entry.getKey());
            }
        }
        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listProvince.contains(entry.getValue().getRegion2())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listCity.contains(entry.getValue().getRegion3())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Long id : creativeIdArr) {
            map.remove(id);
        }
        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            finalResults.add(entry.getValue());
        }
        return finalResults;
    }

    private Integer[] string2Array(String string) {
        if (null == string) {
            return new Integer[]{};
        }
        String[] arr = string.split(",");
        int i = 0;
        Integer[] resultInt = new Integer[arr.length];
        for (String s : arr) {
            resultInt[i++] = Integer.parseInt(s);
        }
        return resultInt;
    }

    @Override
    public List<CreativeIndexTotal> findByAdtypeInAndPositionidInAndProvinceInAndCityInAndCreativetypeInAndLocalindustryInAndNetworkInAndClientInAndFrequencyInAndCrowdInAndPriceidIn(
            String adtype, String positionid, String province, String city, String creativetype, String localindustry, String network, String client, String frequency,
            String crowd, String priceid) {
        if (null == adtype) {
            return new ArrayList<CreativeIndexTotal>();
        }
        if (null == positionid) {
            return new ArrayList<CreativeIndexTotal>();
        }
        if (null == province) {
            province = "";
        }
        if (null == city) {
            city = "";
        }
        if (null == creativetype) {
            creativetype = "4";//'类型，1视频，2图文，3文字, 4图片',我自己做了默认值处理,后期可以修改，我这个属于测试
        }
        if (null == localindustry) {
            localindustry = "1";//我自己做了默认值处理,后期可以修改，我这个属于测试
        }
        if (null == network) {
            network = "3";//'网络环境，0:所有网络环境，1:wifi, 2:4G, 3:3G',我自己做了默认值处理,后期可以修改，我这个属于测试
        }
        if (null == client) {
            client = "0";//'系统，0:所有端，1:ios, 2:android',我自己做了默认值处理,后期可以修改，我这个属于测试
        }
        if (null == frequency) {
            frequency = "0";//我自己做了默认值处理,后期可以修改，我这个属于测试
        }
        if (null == crowd) {
            crowd = "0";//'人群，预留字段，一期不做',我自己做了默认值处理,后期可以修改，我这个属于测试
        }
        if (null == priceid) {
            crowd = "0";//'外键，对应价格表id',我自己做了默认值处理,后期可以修改，我这个属于测试
        }
        Integer[] adInt = string2Array(adtype);
        Integer[] poInt = string2Array(positionid);
        String[] pro = province.split(",");
        String[] ci = city.split(",");
        Integer[] creativeTy = string2Array(creativetype);
        Integer[] localIn = string2Array(localindustry);
        Integer[] net = string2Array(network);
        Integer[] clien = string2Array(client);
        Integer[] frequenc = string2Array(frequency);
        Integer[] crow = string2Array(crowd);
        Integer[] price = string2Array(priceid);
        List<CreativeIndexTotal> result = new ArrayList<>();
        List<CreativeIndexTotal> finalResults = new ArrayList<>();
        result = this.creativeIndexTotalRepository.findByAdtypeInAndPositionidInAndRegion2InAndRegion3InAndCreativetypeInAndLocalindustryInAndNetworkInAndClientInAndFrequencyInAndCrowdInAndPriceidIn(
                adInt, poInt, pro, ci, creativeTy, localIn, net, clien, frequenc, crow, price
        );
        Map<Long, CreativeIndexTotal> map = new HashMap<>();
        for (CreativeIndexTotal c : result) {
            map.put(c.getCreativeid(), c);
        }
        List<Long> creativeIdArr = new ArrayList<>();
        List<Integer> listAdType = Arrays.asList(adInt);
        List<Integer> listPosition = Arrays.asList(poInt);
        List<String> listProvince = Arrays.asList(pro);
        List<String> listCity = Arrays.asList(ci);
        List<Integer> listCreative = Arrays.asList(creativeTy);
        List<Integer> listLocal = Arrays.asList(localIn);
        List<Integer> listNet = Arrays.asList(net);
        List<Integer> listClient = Arrays.asList(clien);
        List<Integer> listFrequen = Arrays.asList(frequenc);
        List<Integer> listCrow = Arrays.asList(crow);
        List<Integer> listPrice = Arrays.asList(price);

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listAdType.contains(entry.getValue().getAdtype())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listPosition.contains(entry.getValue().getPositionid())) {
                creativeIdArr.add(entry.getKey());
            }
        }
        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listProvince.contains(entry.getValue().getRegion2())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listCity.contains(entry.getValue().getRegion3())) {
                creativeIdArr.add(entry.getKey());
            }
        }
        //


        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listCreative.contains(entry.getValue().getCreativetype())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listLocal.contains(entry.getValue().getLocalindustry())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listNet.contains(entry.getValue().getNetwork())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listClient.contains(entry.getValue().getClient())) {
                creativeIdArr.add(entry.getKey());
            }
        }
        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listFrequen.contains(entry.getValue().getFrequency())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listCrow.contains(entry.getValue().getCrowd())) {
                creativeIdArr.add(entry.getKey());
            }
        }

        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            if (!listPrice.contains(entry.getValue().getPriceid())) {
                creativeIdArr.add(entry.getKey());
            }
        }


        for (Long id : creativeIdArr) {
            map.remove(id);
        }
        for (Map.Entry<Long, CreativeIndexTotal> entry : map.entrySet()) {
            finalResults.add(entry.getValue());
        }
        return finalResults;
    }

    @Override
    public List<CreativeIndexTotal> findByCityInAndProvinceInAndPositionidInAndAdtypeIn(String city, String province, String positionid, String adtype) {
        String[] ad = adtype.split(",");
        int i = 0;
        Integer[] adInt = new Integer[ad.length];
        for (String s : ad) {
            adInt[i++] = Integer.parseInt(s);
        }
        String[] po = positionid.split(",");
        int j = 0;
        Integer[] poInt = new Integer[po.length];
        for (String s : po) {
            poInt[j++] = Integer.parseInt(s);
        }
        String[] pro = province.split(",");
        String[] ci = city.split(",");
        return this.creativeIndexTotalRepository.findByRegion3InAndRegion2InAndPositionidInAndAdtypeIn(ci, pro, poInt, adInt);
    }

    @Override
    public List<CreativeIndexTotal> findByRegion3In(String city) {
        BoolQueryBuilder firt = QueryBuilders.boolQuery();
//        firt.should(QueryBuilders.termQuery("region3","渭南市"));
        firt.must(QueryBuilders.termQuery("region3", "西安市"));
//        firt.filter(QueryBuilders.boolQuery().should(QueryBuilders.termQuery("region3","渭南市")).should(QueryBuilders.termQuery("region3","西安市")));
//        firt.should(QueryBuilders.termQuery("region3","西安市"));
        BoolQueryBuilder third = new BoolQueryBuilder().must(firt);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(third).withSort(SortBuilders.fieldSort("adtype").order(SortOrder.ASC));
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        return Lists.newArrayList(creativeIndexTotalRepository.search(query));


//        String[] ci = city.split(",");
//        List<String> result=Arrays.asList(ci);
//        return this.creativeIndexTotalRepository.findByRegion3In(result);


    }

    @Override
    public List<CreativeIndexTotal> findByRegion3InAAndRegion2In(String city, String province) {
        String[] ci = city.split(",");
        String[] pro = province.split(",");
        return this.creativeIndexTotalRepository.findByRegion3InAndRegion2In(ci, pro);
    }

    @Override
    public List<CreativeIndexTotal> findByAdtypeIn(String adtype) {
        String[] ad = adtype.split(",");
        int i = 0;
        Integer[] adInt = new Integer[ad.length];
        for (String s : ad) {
            adInt[i++] = Integer.parseInt(s);
        }
        return this.creativeIndexTotalRepository.findByAdtypeIn(adInt);
    }

    @Override
    public List<CreativeIndexTotal> findByAdtypeInAAndPositionidIn(String adtype, String positionid) {
        String[] ad = adtype.split(",");
        int i = 0;
        Integer[] adInt = new Integer[ad.length];
        for (String s : ad) {
            adInt[i++] = Integer.parseInt(s);
        }
        String[] po = positionid.split(",");
        int j = 0;
        Integer[] poInt = new Integer[po.length];
        for (String s : po) {
            poInt[j++] = Integer.parseInt(s);
        }
        return this.creativeIndexTotalRepository.findByAdtypeInAndPositionidIn(adInt, poInt);
    }


    @Override
    public List<CreativeIndexTotal> findByDemo3(Integer adtype, String positionname) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("positionname", positionname))).withSort(SortBuilders.fieldSort("adtype").order(SortOrder.ASC)).build();
        return Lists.newArrayList(creativeIndexTotalRepository.search(nativeSearchQuery));
    }

    @Override
    public List<CreativeIndexTotal> findByProvinceidOrCityid(Integer provinceid, Integer cityid) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.termQuery("provinceid", provinceid)).should(QueryBuilders.termQuery("cityid", cityid));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withSort(SortBuilders.fieldSort("adtype").order(SortOrder.ASC));
        return Lists.newArrayList(creativeIndexTotalRepository.search(nativeSearchQueryBuilder.build()));
    }

    @Override
    public List<CreativeIndexTotal> findByAdtypeOrPositionnameOrRegion1OrRegion2OrRegion3(Integer adtype, String positionname, String region1, String region2, String region3) {

//        return creativeIndexTotalRepository.findByAdtypeOrPositionidOrRegion1OrRegion2OrRegion3(adtype, positionname, region1, region2, region3);
        return null;
    }

    @Override
    public void updateCreativeTitle(Long creativeid, String title) {
        if (creativeIndexTotalRepository.findById(creativeid).isPresent()) {
            CreativeIndexTotal creativeIndexTotal = creativeIndexTotalRepository.findById(creativeid).get();
//            creativeIndexTotal.setTitle(title);
            creativeIndexTotal.setAdtype(Integer.parseInt(title));
//            creativeIndexTotal.setCreativeid(Long.parseLong(title));
//            creativeIndexTotal.setBid(Integer.parseInt(title));
            creativeIndexTotalRepository.save(creativeIndexTotal);
//            creativeIndexTotalRepository.delete(creativeIndexTotal);
        }

    }

    @Override
    public List<CreativeIndexTotal> findAllData() {
        Long[] arr = {844L, 836L, 829L, 33L, 34L, 32L, 845L, 846L, 849L, 848L, 35L, 36L, 38L, 98989898988L, 834L, 835L, 856L, 31L, 837L};
        List<CreativeIndexTotal> result = new ArrayList<>();
        for (Long l : arr) {
            result.add(creativeIndexTotalRepository.findById(l).get());
        }
        return result;
    }

}
