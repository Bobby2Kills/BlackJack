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
        String url = "http://localhost:7000/save?playerName=" + playerName;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Player name saved to the database.");
        }
        else {
            System.out.println("Failed to save the player name to the database. Status code: " + response.getStatusCodeValue());
        }


    }
}
