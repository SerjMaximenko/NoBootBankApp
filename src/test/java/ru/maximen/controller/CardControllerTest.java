package ru.maximen.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addNewCard() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

/*    @Test
    public void
    givenUserExists_whenUserInformationIsRetrieved_thenRetrievedResourceIsCorrect()
            throws ClientProtocolException, IOException {

        // Given
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        // Then
        GitHubUser resource = RetrieveUtil.retrieveResourceFromResponse(
                response, GitHubUser.class);
        assertThat( "eugenp", Matchers.is( resource.getLogin() ) );
    }*/



}