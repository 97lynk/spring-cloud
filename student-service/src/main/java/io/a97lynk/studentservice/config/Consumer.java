//package io.a97lynk.studentservice.config;
//
//import io.a97lynk.studentservice.model.Student;
//import org.springframework.kafka.annotation.KafkaHandler;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@KafkaListener(topics = "student-mq")
//public class Consumer {
//
//    @KafkaHandler
//    public void studentHandler(Student student) {
//        System.out.println("student handler " + student);
//    }
//
//}
