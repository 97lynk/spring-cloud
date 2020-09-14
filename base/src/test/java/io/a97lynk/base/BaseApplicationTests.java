package io.a97lynk.base;

import io.a97lynk.base.jpa.EmployeeDto;
import io.a97lynk.base.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BaseApplicationTests {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Test
    public void insert() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Tuan");
        employeeService.add(employeeDto);
        System.out.println(employeeService.searchAll());
    }

}
