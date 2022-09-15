package choi.bok.gg.domain.match.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MatchV5Dto {


    // json key 이름으로 필드명 변경
    @JsonProperty("metadata")
    private MatchMetadataDto matchMetadataDto;

    @JsonProperty("info")
    private MatchInfoDto matchInfo;
}
