//package com.tangdou.es2.config;
//
//
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import java.net.InetAddress;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.tangdou.es2.repository")
//@ComponentScan(basePackages = { "com.tangdou.es2.service" })
//public class EsConfig {
//
//    @Value("${spring.elasticsearch.host}")
//    private String host;
//
//    @Value("${spring.elasticsearch.port}")
//    private int port;
//
////
////    @Value("${elasticsearch.clustername}")
////    private String esClusterName;
//    @Bean
//    public Client client() throws Exception {
//        Settings elasticsearchSettings = Settings.builder().put("client.transport.sniff", true).build();
//        TransportClient client = new PreBuiltTransportClient(elasticsearchSettings);
//        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
//        return client;
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(client());
//    }
//}
