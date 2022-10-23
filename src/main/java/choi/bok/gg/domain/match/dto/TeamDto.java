package choi.bok.gg.domain.match.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDto {

    private List<BanDto> bans;
//    private ObjectivesDto objectives;
    private int teamId;
    private boolean win;
}
