package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Cron {

	/* Cron 표현식*/
	@Scheduled(cron="* * * * * *") 
	public void cron1() throws Exception {
		
	}
	/* milliseconds단위, 이전 작업이 끝난 시점으로 부터 고정된 시간을 설정한다*/
	@Scheduled(fixedDelay = 1000) 
	public void cron2_1() throws Exception {
		System.out.println("fixedDelay : 1000");
	}
	
	/* milliseconds단위, 이전 작업이 수행되기 시작한 시점으로 부터 고정된 시간을 설정한다*/
	@Scheduled(fixedRate = 1000) 
	public void cron3() throws Exception {
		System.out.println("fixedRate : 1000"); 
	}
	
	/* milliseconds단위, 초기 지연시간을 설정한다.*/
	@Scheduled(initialDelay  = 1000, fixedRate = 1000) 
	public void cron4() throws Exception {
		System.out.println("initialDelay + fixedRate ");
	}
}
