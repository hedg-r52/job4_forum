package ru.job4j.forum.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @MockBean
    private UserService users;

    @MockBean
    private PasswordEncoder encoder;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenGetRegistrationShouldReturnRegistrationPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void whenPostRegistrationShouldCreateNewUser() throws  Exception {
        this.mockMvc.perform(post("/reg")
                .param("username", "testing")
                .queryParam("password", "testing"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(users).save(argument.capture());
        assertThat(argument.getValue().getUsername(), is("testing"));
        assertThat(argument.getValue().getPassword(), is(encoder.encode("testing")));
    }

}
