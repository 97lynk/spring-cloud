//package io.a97lynk.studentservice.config;
//
//import io.a97lynk.studentservice.model.Student;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper;
//import org.springframework.kafka.support.converter.Jackson2JavaTypeMapper;
//import org.springframework.kafka.support.converter.RecordMessageConverter;
//import org.springframework.kafka.support.converter.StringJsonMessageConverter;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//public class KafkaConfig {
//
//    @Autowired
//    private KafkaProperties kafkaProperties;
//
//    @Bean
//    public ProducerFactory producerFactory() {
//        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
//    }
//
//    @Bean
//    public KafkaTemplate kafkaTemplate() {
//        KafkaTemplate kafkaTemplate = new KafkaTemplate<>(producerFactory());
//        kafkaTemplate.setDefaultTopic(kafkaProperties.getTemplate().getDefaultTopic());
//        return kafkaTemplate;
//    }
//
//
//    @Bean
//    public ConsumerFactory consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory kafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public RecordMessageConverter converter() {
//
//        Map<String, Class<?>> mappings = new HashMap<>();
//        mappings.put("student", Student.class);
//
//        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
//        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
//        typeMapper.addTrustedPackages("kr.co.metanet");
//        typeMapper.setIdClassMapping(mappings);
//
//        StringJsonMessageConverter converter = new StringJsonMessageConverter();
//        converter.setTypeMapper(typeMapper);
//        return converter;
//    }
//}
