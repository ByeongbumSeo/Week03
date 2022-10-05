package com.sparta.week03;

import com.sparta.week03.domain.Memo;
import com.sparta.week03.domain.MemoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Timestemped 생성/수정 시간 자동 반영
@SpringBootApplication
public class Week03Application {

	public static void main(String[] args) {
		SpringApplication.run(Week03Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(MemoRepository memoRepository) {
		return (args) -> {
			memoRepository.save(new Memo("1111", "22222", "3333", "1234"));
		};
	}
}
