package m.kash.maproute.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import m.kash.maproute.dtos.RouteResponseDto;
import m.kash.maproute.dtos.RouteResponseWithDetailsDto;
import m.kash.maproute.models.Destination;
import m.kash.maproute.models.Source;
import m.kash.maproute.utility.StepExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

@Service
public class OlaMapRoutingService implements MapRoutingService {

    @Value("${api.key}")
    private String API_KEY;

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    public OlaMapRoutingService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public RouteResponseWithDetailsDto getRoute(Source source, Destination destination, String mode) {
        // Implement the method to get the route from the source to the destination
        String sourceDestinationLocation = formatSourceDestination(source, destination);

        String baseUrl = "https://api.olamaps.io/routing/v1/routeOptimizer";

        String uri = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("locations", sourceDestinationLocation)  // Combine source and destination
                .queryParam("source", "first") // Default parameter
                .queryParam("destination", "last") // Default parameter
                .queryParam("round_trip", "false") // Default parameter
                .queryParam("mode", mode) // Driving mode
                .queryParam("steps", "true") // Default parameter
                .queryParam("overview", "full") // Default parameter
                .queryParam("language", "en") // Default parameter
                .queryParam("traffic_metadata", "false") // Default parameter
                .queryParam("api_key", API_KEY) // API key
                .build()
                .toUriString();
        System.out.println("URI: " + uri); // Log the URI


            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            // Empty request body
        HttpEntity<String> entity = new HttpEntity<>("", headers);

            // Execute the request
        RequestEntity<String> requestEntity = new RequestEntity<>("", headers, HttpMethod.POST, URI.create(uri));
        System.out.println("Sending request to: " + uri);

        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
                // Parse the JSON response
                RouteResponseDto routeResponse = null;
                try {
                    routeResponse = objectMapper.readValue(response.getBody(), RouteResponseDto.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                // Extract and return step summaries
                return StepExtractor.extractStepSummaries(routeResponse);
            } else {
                throw new RuntimeException("API call failed with status: " + response.getStatusCode());
            }

    }

   public String formatSourceDestination(Source source, Destination destination) {
       DecimalFormat df = new DecimalFormat("#.######");

       String sourceLatitudeString = df.format(source.getCoordinates().getLatitude());
       String sourceLongitudeString = df.format(source.getCoordinates().getLongitude());
       String finalSource = sourceLatitudeString + "," + sourceLongitudeString;

       String destinationLatitudeString = df.format(destination.getCoordinates().getLatitude());
       String destinationLongitudeString = df.format(destination.getCoordinates().getLongitude());
       String finalDestination = destinationLatitudeString + "," + destinationLongitudeString;

       String finalSourceDestination = finalSource + "|" + finalDestination;
       return URLEncoder.encode(finalSourceDestination, StandardCharsets.UTF_8);
    }




}
