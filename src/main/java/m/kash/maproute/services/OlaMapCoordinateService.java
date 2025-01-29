package m.kash.maproute.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import m.kash.maproute.dtos.GeocodingResponse;
import m.kash.maproute.models.Coordinates;
import m.kash.maproute.services.MapCoordinateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class OlaMapCoordinateService implements MapCoordinateService {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Value("${api.key}")
    private String API_KEY;

    public OlaMapCoordinateService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    private static final String BASE_URL = "https://api.olamaps.io/places/v1/geocode";


    // Assuming the response body is deserialized into a GeocodingResponse object

    @Override
    public Coordinates getCoordinates(String address) {
        try {
            // URL encode the address
            String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
            System.out.println("Encoded Address: " + encodedAddress);

            // Construct the API URL with the address
            String apiUrl = String.format("%s?address=%s&language=English&api_key=%s", BASE_URL, encodedAddress, API_KEY);
            System.out.println("API URL: " + apiUrl);  // Log the URL

            // Create HttpHeaders and set the necessary headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            //headers.set("User-Agent", "Mozilla/5.0");

            // Create HttpEntity with headers
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make the API request with headers
            ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

            // Get the response body from the responseEntity
            String rawResponse = responseEntity.getBody();
            System.out.println("Raw Response: " + rawResponse); // Log raw JSON response for debugging

            // Deserialize the response body into GeocodingResponse
            GeocodingResponse geocodingResponse = objectMapper.readValue(rawResponse, GeocodingResponse.class);
            System.out.println("Geocoding Response: " + geocodingResponse);

            // Initialize coordinates object
            Coordinates coordinates = new Coordinates();

            // If response contains geocoding results, extract the coordinates
            if (geocodingResponse != null && geocodingResponse.getGeocodingResults() != null && !geocodingResponse.getGeocodingResults().isEmpty()) {
                GeocodingResponse.GeocodingResult firstResult = geocodingResponse.getGeocodingResults().get(0);
                GeocodingResponse.Location location = firstResult.getGeometry().getLocation();
                coordinates.setLatitude(location.getLat());
                coordinates.setLongitude(location.getLng());
            } else {
                System.out.println("No geocoding results found.");
            }

            // Print the coordinates for debugging
            System.out.println("Coordinates: Latitude = " + coordinates.getLatitude() + ", Longitude = " + coordinates.getLongitude());
            return coordinates;
        } catch (Exception e) {
            // Log any errors during the API call or deserialization
            System.out.println("Error during API call or deserialization: " + e.getMessage());
            e.printStackTrace();
            return new Coordinates();  // Return empty coordinates in case of an error
        }
    }

}
