package io.a97lynk.cityservice.discriminator;

import io.a97lynk.cityservice.config.TenantContext;
import io.a97lynk.cityservice.service.CityService;
import org.aspectj.lang.JoinPoint;
import org.hibernate.Session;

//@Aspect
//@Component
public class CityServiceAspect {

//    @Before("execution(* io.a97lynk.cityservice.service.CityService.*(..))&& target(cityService) ")
    public void aroundExecution(JoinPoint pjp, CityService cityService) throws Throwable {
        org.hibernate.Filter filter = cityService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
        filter.validate();
    }
}
