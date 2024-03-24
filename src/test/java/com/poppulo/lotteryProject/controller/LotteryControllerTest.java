package com.poppulo.lotteryProject.controller;

import com.poppulo.lotteryProject.exception.ErrorResponse;
import com.poppulo.lotteryProject.service.LotteryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LotteryController.class)
@ExtendWith(SpringExtension.class)
public class LotteryControllerTest {

    @Autowired
    LotteryController lotteryController;

    @MockBean
    LotteryService lotteryService;
    @MockBean
    ErrorResponse errorResponse;

    @Autowired
    MockMvc mockMvc;

    /**
     *
     * Test happy case for POST call
     */
    @Test
    public void testGenerateTicket_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/lottery/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberOfLines\":\"3\"}"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    /**
     *
     * Test POST call for negative numberOfLines
     */
    @Test
    public void testGenerateTicket_Fail_Negative_Line_Number() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/lottery/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberOfLines\":\"-1\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    /**
     *
     * Test POST call for zero numberOfLines
     */
    @Test
    public void testGenerateTicket_Fail_Zero_Line_Number() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/lottery/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberOfLines\":\"0\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    /**
     *
     * Test POST call for Invalid input
     */
    @Test
    public void testGenerateTicket_Fail_Wrong_input() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/lottery/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"Lines\":\"0\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    /**
     *
     * Test POST call for input having extra fields
     */
    @Test
    public void testGenerateTicket_Fail_extra_input() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/lottery/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberOfLines\":\"0\" , \"random\":\"0\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    /**
     *
     * Test happy case for amendTicket PUT call
     */
    @Test
    public void test_amend_ticket_success() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/lottery/ticket/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberOfLines\":\"2\"}"))
                .andExpect(status().isAccepted())
                .andReturn();
    }

    /**
     *
     * Test amendTicket PUT call for input having negative number
     */
    @Test
    public void test_amend_ticket_fail_negative_ticket_number() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/lottery/ticket/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberOfLines\":\"-2\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    /**
     *
     * Test amendTicket PUT call for input having zero number
     */
    @Test
    public void test_amend_ticket_fail_zero_ticket_number() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/lottery/ticket/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"numberOfLines\":\"0\"}"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
