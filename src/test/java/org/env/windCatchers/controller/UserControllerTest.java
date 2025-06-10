package org.env.windCatchers.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.env.windCatchers.controllers.UsersController;
import org.env.windCatchers.model.User;
import org.env.windCatchers.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(UsersController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockitoBean
    UserRepository repository;

    private final List<User> users = new ArrayList<>();


    @BeforeEach
    void setUp() { users.add( new User("Jhon", "jhon@mail.com", "password", "Admin", "0100000000")); }




    @Test
    void shouldFindAllUsers() throws Exception {
        when(repository.findAll()).thenReturn(users);
        mvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(users.size())));
    }

    @Test
    void shouldFindUserById() throws Exception {
        User user  =  users.get(0);
        when(repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(user));
        mvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(user.getName())))
            .andExpect(jsonPath("$.email", is(user.getEmail())))
            .andExpect(jsonPath("$.role", is(user.getRole())))
            .andExpect(jsonPath("$.phone", is(user.getPhone())));
    }



    @Test
    void shouldCreateNewUsers() throws Exception {
        var user = new User("Jhony", "jhony@mail.com", "password1", "Admin", "0100000000");
        mvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(user))
         ).andExpect(status().isCreated());
    }
}
