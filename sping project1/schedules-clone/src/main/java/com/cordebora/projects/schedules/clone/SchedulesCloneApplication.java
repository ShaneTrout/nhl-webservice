package com.cordebora.projects.schedules.clone;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.*;
import java.time.YearMonth;
import com.cordebora.projects.schedules.clone.demo.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.cordebora.projects.schedules.clone.NHLdata;

import java.io.IOException;
import java.time.YearMonth;
@SpringBootApplication
@RestController
public class SchedulesCloneApplication {


	// Method to fetch game info from NHLData
	@GetMapping("/schedule")
	public String getGamesInfo() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		String webNhlData = new NHLdata().getJsonResponse();
		JsonNode node = objectMapper.readTree(webNhlData);

		ScheduleObject response = objectMapper.treeToValue(node, ScheduleObject.class);


		StringBuilder result = new StringBuilder();
		for (GamesObject games : response.getGames()) {
			result.append("Date: ").append(games.getGameDate()).append("<br>")
					.append("Venue: ").append(games.getVenue().getDefaultVenue()).append("<br>")
					.append("Home Team: ").append(games.getHomeTeam().getAbbrev())
					.append(", Score: ").append(games.getHomeTeam().getScore()).append("<br>")
					.append("Away Team: ").append(games.getAwayTeam().getAbbrev())
					.append(", Score: ").append(games.getAwayTeam().getScore()).append("<br><br>");
		}
		return result.toString();
	}

	// Main method to run the application
	public static void main(String[] args) {
		SpringApplication.run(SchedulesCloneApplication.class, args);
	}
}