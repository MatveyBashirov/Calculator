package ru.neoflex.practice.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CalcController.class)
class CalcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalcController calcController;

    @Test
    public void sum() throws Exception {

        int a = 2;
        int b = 2;
        int expectedSum = a+b;

        BDDMockito.given(calcController.Sum(a,b))
                .willReturn(a+b);

        mockMvc.perform(MockMvcRequestBuilders.get("/plus/{a}/{b}",a, b))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedSum)));
    }

    @Test
    public void diff() throws Exception {

        int a = 5;
        int b = 2;
        int expectedDiff = a-b;

        BDDMockito.given(calcController.Diff(a,b))
                .willReturn(a-b);

        mockMvc.perform(MockMvcRequestBuilders.get("/minus/{a}/{b}", a, b))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedDiff)));
    }
}