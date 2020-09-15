package io.a97lynk.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.a97lynk.base.contoller.CORSFilter;
import io.a97lynk.base.contoller.EmployeeController;
import io.a97lynk.base.jpa.EmployeeDto;
import io.a97lynk.base.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class BaseControllerTests {


    private MockMvc mockMvc;

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void insert() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Tuan Nguyen");

        Mockito.when(employeeService.insert(any(EmployeeDto.class))).then(invocationOnMock -> {
            invocationOnMock.getArgument(0, EmployeeDto.class).setId(1l);
            return invocationOnMock;
        });

        mockMvc.perform(
                post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employeeDto)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
//        (mvcResult -> mvcResult.getResponse().getContentAsString())
    }

    private String asJsonString(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    @Test
    public void selectAll() {

    }

    @Test
    public void selectPage() {

    }

    @Test
    public void searchById() {

    }

    @Test
    public void searchById_fails() {

    }

    @Test
    public void update() {
    }

}
