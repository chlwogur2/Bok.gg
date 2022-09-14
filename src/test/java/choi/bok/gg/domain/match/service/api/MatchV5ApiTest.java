package choi.bok.gg.domain.match.service.api;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class MatchV5ApiTest {

    @Test
    void matchV5ApiByPuuid() throws IOException {
        MatchV5Api matchV5Api = new MatchV5Api();
        String[] results = matchV5Api.matchV5ApiByPuuid("caNygIw8Ep77rd3K3C-0c6BefzqnV25bWmy4Uu6sxZOgw8uicXtTQzU5j1SogPKM2uYaLyE3yUwXAA", 0, 20);

        // stream -> forEach 문
        // String[] 배열이 아니고 그냥 String 이 넘어오네 뭐지
        Arrays.stream(results).forEach(s -> System.out.println(s));
    }
}