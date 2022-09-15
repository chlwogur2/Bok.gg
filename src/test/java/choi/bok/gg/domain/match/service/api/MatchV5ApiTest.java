package choi.bok.gg.domain.match.service.api;

import choi.bok.gg.domain.match.dto.MatchV5Dto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class MatchV5ApiTest {


    @Autowired
    MatchV5Api matchV5Api;

    @Test
    void matchV5ApiByPuuid() throws IOException {

        // 내 아이디의 puuid
        List<String> results = matchV5Api.matchIdsByPuuid("caNygIw8Ep77rd3K3C-0c6BefzqnV25bWmy4Uu6sxZOgw8uicXtTQzU5j1SogPKM2uYaLyE3yUwXAA", 0, 20);

        System.out.println("매치 id들= " + results);
    }

    @Test
    void matchByMatchId() throws IOException{
        List<String> results = matchV5Api.matchIdsByPuuid("caNygIw8Ep77rd3K3C-0c6BefzqnV25bWmy4Uu6sxZOgw8uicXtTQzU5j1SogPKM2uYaLyE3yUwXAA", 0, 20);

        MatchV5Dto matchV5Dto = matchV5Api.matchByMatchId(results.get(2));

        System.out.println("MatchDto 내용= " + matchV5Dto);
    }
}