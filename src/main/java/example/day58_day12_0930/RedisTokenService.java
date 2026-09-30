package example.day58_day12_0930;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class RedisTokenService {
    // [1] 레디스 조작 객체 주입
    private final StringRedisTemplate stringRedisTemplate;

    // [2] Refresh 토큰 redis 저장
    public void setRefreshToken( Long mno, String token){
        // key에는 RT:회원번호 조합     // value = refresh token
        // 만료기간 : Duration.ofXXX(수)
        stringRedisTemplate.opsForValue().set("RT:"+mno, token, Duration.ofDays(7) );
    }

    // [3] Refresh 토큰 조회
    public String getRefreshToken( Long mno ){
        String findKey = "RT:"+mno; // 조회할 key 조합
        return stringRedisTemplate.opsForValue().get(findKey);  // 조회할 키값을 넣어서 조회
    }

    // [4] Refresh 토큰 삭제
    public boolean deleteRefreshToken( Long mno ){
        return stringRedisTemplate.delete("RT:"+mno); // 삭제할 key 조합하여 조회
    }
}
