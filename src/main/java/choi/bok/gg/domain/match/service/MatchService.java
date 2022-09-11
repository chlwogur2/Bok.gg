package choi.bok.gg.domain.match.service;

import choi.bok.gg.domain.match.dto.MatchResponseDto;
import choi.bok.gg.domain.match.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

//    public Page<MatchResponseDto> findAllMatch() {
//
//        PageRequest pageRequest = PageRequest.of(0, 3);
//        return matchRepository.findAll(pageRequest).map(MatchResponseDto::from);
//    }
}
