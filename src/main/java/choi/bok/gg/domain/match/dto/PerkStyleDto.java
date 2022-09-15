package choi.bok.gg.domain.match.dto;

import lombok.Data;

import java.util.List;

@Data
public class PerkStyleDto {

    private String description;
    private List<PerkStyleSelectionDto> selections;
    private int style;
}
