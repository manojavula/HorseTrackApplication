package com.manu.horsetrack;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.manu.horsetrack.repository.HorseRepository;
import com.manu.horsetrack.repository.InventoryRepository;
import com.manu.horsetrack.service.HorseService;
import com.manu.horsetrack.service.ReportService;

@SpringBootApplication
public class HorseTrackApplication implements CommandLineRunner{
	
	  @Autowired
	  private ConfigurableApplicationContext context;

	  @Autowired
	  HorseRepository horseRepository;

	  @Autowired
	  InventoryRepository inventoryRepository;

	  @Autowired
	  HorseService horseService;

	  @Autowired
	  ReportService reporterService;

	  @Autowired
	  HorseTrack horseTract;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HorseTrackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		horseTract.initialize();
		horseTract.printStartupMessages();

	    try (Scanner commandString = new Scanner(System.in)) {
			while (!(horseTract.quit())) {
					horseTract.execute(commandString.nextLine());
			}
		}
	    System.exit(SpringApplication.exit(context));
		
	}

}
