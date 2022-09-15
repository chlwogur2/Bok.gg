package choi.bok.gg.domain.match.dto;

import lombok.Data;

import java.util.List;

@Data
public class PerksDto {

    private PerkStatsDto statPerks;
    private List<PerkStyleDto> styles;
}
