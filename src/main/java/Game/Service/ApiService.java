package Game.Service;

import Game.Models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiService {

    public void savePlayerName(String playerName) {
        // defines 'url' as the endpoint + the playerName entered
        String url = "http://localhost:8102/save?playerName=" + playerName;

        // Gives the playerName in JSON format
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Creates an instance of the HttpEntity using the header created
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // RestTemplate sends the POSt request, takes the URL and HTTP, Request Entity and gives the response type.
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        // Checks the response was actually successful
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Player name saved to the database.");
        }
        else {
            System.out.println("Failed to save the player name to the database. Status code: " + response.getStatusCodeValue());
        }


    }
}
