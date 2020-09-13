//package io.a97lynk.studentservice;
//
//import io.a97lynk.navigator.config.TenantContext;
//import io.a97lynk.studentservice.mapper.StudentMapper;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//
////@Aspect
////@Component
////public class StudentServiceAspect {
////
////    @Autowired
////    EntityManager entityManager;
//
////    @Before("execution(* io.a97lynk.studentservice.mapper.*(..))&& target(studentMapper) ")
////    public void aroundExecution(JoinPoint pjp, StudentMapper studentMapper) throws Throwable {
//
////        org.hibernate.Filter filter = entityManager.unwrap(Session.class).enableFilter("tenantFilter");
////        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
////        filter.validate();
//    }
//}
