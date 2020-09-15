package io.a97lynk.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.a97lynk.base.contoller.CORSFilter;
import io.a97lynk.base.contoller.EmployeeController;
import io.a97lynk.base.jpa.EmployeeDto;
import io.a97lynk.base.jpa.EmployeeEntity;
import io.a97lynk.base.jpa.EmployeeRepository;
import io.a97lynk.base.service.impl.BaseServiceImpl;
import io.a97lynk.base.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class BaseControllerTests {


    private MockMvc mockMvc;

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private BaseServiceImpl<EmployeeEntity, EmployeeDto, EmployeeRepository> baseService;

    private boolean init = false;

    @InjectMocks
    private EmployeeController employeeController;

    private List<EmployeeDto> dtos = new ArrayList<>();

    @BeforeEach
    public void init() {
        if (init) return;

        init = true;

        // setup mock
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                .addFilters(new CORSFilter())
                .build();

        // init data
        EmployeeDto dto1 = new EmployeeDto();
        dto1.setId(1l);
        dto1.setName("Tuan Nguyen 1");

        EmployeeDto dto2 = new EmployeeDto();
        dto2.setId(2l);
        dto2.setName("Tuan Nguyen 2");

        dtos.add(dto1);
        dtos.add(dto2);

        Mockito.when(employeeService.searchAll()).thenReturn(dtos);
        Mockito.when(employeeService.searchAll(any(Pageable.class))).thenReturn(new PageImpl<>(dtos));
        Mockito.when(employeeService.insert(any(EmployeeDto.class))).then(invocationOnMock -> dto1);

    }

    @Test
    public void insert() throws Exception {

        mockMvc.perform(
                post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dtos.get(0))))
                .andExpect(status().isCreated())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
    }


    @Test
    public void selectAll() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().is2xxSuccessful())
                .andDo(mvcResult -> System.out.println(mvcResult.getResponse().getContentAsString()));
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

    private String asJsonString(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
