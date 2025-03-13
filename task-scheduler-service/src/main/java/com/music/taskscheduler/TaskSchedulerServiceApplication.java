package com.music.taskscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.music.*", "org.springdoc"} )
@EnableScheduling
@EnableFeignClients
public class TaskSchedulerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSchedulerServiceApplication.class, args);
	}

}
