package choi.bok.gg.domain.match.exception;

// API에서 유저의 매치 기록을 찾아올 수 없는 경우
public class NoMatchResultsException extends RuntimeException{

    public NoMatchResultsException(String msg) {
        super(msg);
    }
}
