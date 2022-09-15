package choi.bok.gg.domain.match.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchMetadataDto {

    private String dataVersion;
    private String matchId;
    private List<String> participants;
}
