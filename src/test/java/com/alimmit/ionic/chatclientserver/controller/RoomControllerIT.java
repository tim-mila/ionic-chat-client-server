package com.alimmit.ionic.chatclientserver.controller;

import com.alimmit.ionic.chatclientserver.ChatClientServerApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoomControllerIT extends ChatClientServerApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private RoomController roomController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }

    @Test
    public void testSchedule() throws Exception {
        mockMvc.perform(post("/api/v1/rooms").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\"name\": \"foo\"}")).andDo(this::print);
        mockMvc.perform(get("/api/v1/rooms").contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(this::print);
    }

    private void print(final MvcResult result) throws Exception{
        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());
    }
}
