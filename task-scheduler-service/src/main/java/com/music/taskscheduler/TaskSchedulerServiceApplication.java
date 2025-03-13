package com.music.taskscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.music.*", "org.springdoc"} )
public class TaskSchedulerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedulerServiceApplication.class, args);
	}

}
