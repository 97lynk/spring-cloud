//package io.a97lynk.studentservice.config;
//
//import io.a97lynk.studentservice.model.Student;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Producer {
//
//    private final KafkaTemplate template;
//
//    @Autowired
//    public Producer(KafkaTemplate template) {
//        this.template = template;
//    }
//
//    public void sendMessage(Student student) {
//        template.sendDefault(student);
//    }
//}
