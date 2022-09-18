package choi.bok.gg.domain.match.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MatchMetadataDto {

    private String dataVersion;
    private String matchId;
    private List<String> participants;

}
