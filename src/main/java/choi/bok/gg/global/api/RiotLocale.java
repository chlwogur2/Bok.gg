package choi.bok.gg.global.api;

import lombok.Getter;

@Getter
public enum RiotLocale {
    KR("한국"), NA("북미");

    private final String where;
    RiotLocale(String description) {
        this.where = description;
    }
}
