package choi.bok.gg.domain.match.exception;

// DB에서 저장된 매치 정보를 찾을 수 없는 경우
public class NoMatchFoundException extends RuntimeException{

    public NoMatchFoundException(String msg){super(msg);}
}
