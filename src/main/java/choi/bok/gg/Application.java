package choi.bok.gg;

import choi.bok.gg.domain.summoner.service.api.SummonerV4Api;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {



	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
		SummonerV4Api summonerV4Api = new SummonerV4Api(new ObjectMapper());
		summonerV4Api.summonerV4ApiBySummonerName("재 렉");
	}

}
