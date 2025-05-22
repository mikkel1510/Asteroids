package dk.sdu.cbse.scoringsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoringSystem {

	private int totalPoints = 0;

	public static void main(String[] args) {
		SpringApplication.run(ScoringSystem.class, args);
	}

	@GetMapping("/points")
	public int getPoints() {
		return totalPoints;
	}

	@PostMapping("/points")
	public void incrementPoints(@RequestParam("points") int points){
		totalPoints += points;
	}

}
