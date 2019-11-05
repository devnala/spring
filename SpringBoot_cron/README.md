# Spring Booot 2.2  :: Scheduler cron


Spring Boot에서 스케줄러(Cron)을 구현하는 법을 살펴본다.

Spring Web 프로세스 위에서 스케줄러 프로세스가 동작 하는방식으로 구현한다.

기본포트는 8080

​

1. 필요사항

 - 15분 정도의 시간

  - Eclipse Java EE IDE for Web Developers (4.9.0) 또는 그외 에디터

  - JDK 8 or later

  - Gradle 4+

​

2. Spring Boot 프로젝트 생성 방법은 Spring Initializ에서 구성해서 다운받는 방법과

  이클립스에 구성하는 방법이 있다. 

  Spring Initializ( https://start.spring.io/ )를 이용 기본패키지를 생성한다.

  아래 화면의 Dependencies 항목에서 Web을 검색해서 추가한다.

  그렇다  웹서버 위에서 실행되는 스케줄러를 구현한다.

  JPA, Spring Security등 필요한 항목을 더 추가해도 된다.

  Generate 버튼 클릭으로 다운로드 받는다.

  상기 화면에서 구성한 예제 프로젝트가 포함되어있다.


이클립스에서도 마찬가지로  프로젝트를 생성이 가능하다.

일단, 이클립스에서 프로젝트 생성하는 방법으로 진행한다.

프로젝트 명칭은 SampleCron, Build Tool은 Gradle, 나머지는 기본설정


spring boot project 생성 1


spring boot project 생성 2

좀 기다리면 프로젝트 생성이 완료된다. 

환경에 따라서 시간이 소요 되기도 한다.

필자는 5분 정도 걸린듯 하다.

아래와 같은 구성이 된다. 

Boot Dashboard는 상단메뉴 window>show view>others에서 검색


spring boot project 구성

이제 소스코드 작성해 보자

어플리케이션의 Config를 작성한다.

​

Java Config 방식

@SpringBootApplication
@EnableScheduling
public class SampleCronApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleCronApplication.class, args);
	}
	
	@Bean
	public ScheduledExecutorFactoryBean scheduledExecutorService() {
		ScheduledExecutorFactoryBean bean = new ScheduledExecutorFactoryBean();  
		bean.setPoolSize(1); // 스케줄러풀에서 관리되는 풀의 갯수
		return bean;
	}

}
application.yml 설정 방식

spring: 
  task: 
    scheduling: 
      pool: 
        size: 1 
application.properties 설정 방식

spring.task.scheduling.pool.size=1 
​

실제 동작하는 다양한 방식의 스케줄러 소스코드

import org.springframework.scheduling.annotation.Scheduled;


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
​

Boot Dashboard에서  SampleCron start

Spring boot 에서 스케줄러(Cron) 실행됨~~

 
