package example.day58_day12_0930;

import example.Spring_P_Smart_Clotset.AddController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController @RequestMapping ("/api/redis")
@RequiredArgsConstructor 
public class RedisController {
    // [*] 레디스 접근(조작) 객체 (문자열 기반의 자료 레디스에 CRUD 삽입/조회/수정/삭제)
    private final StringRedisTemplate stringRedisTemplate;
    
    // 1.
    @GetMapping("/test")
    public Map<String,Object> test1(){
        // [2] 레디스에 자료 삽입, key : value,  모두 문자열타입으로  .opsForValue().set( key, value)
        // key 중복 불가, value 중복 됨 => NOSQL 
        stringRedisTemplate.opsForValue().set("유재석", "90");
        stringRedisTemplate.opsForValue().set("유재", "80");
        stringRedisTemplate.opsForValue().set("유석", "70");

        // [3] 레디스에 자료 조회, key("*"), 모든 자료덜의 키 조회, Set<String> 컬렉션으로 반환 
        // 참고: 컬렉션프레임워크 List, Map , Set
        Set<String> keys = stringRedisTemplate.keys("*");

        Map<String, Object> map = new HashMap<>();
        for( String key : keys){  // 모든 키들을 하나씩 반복 
            String data = stringRedisTemplate.opsForValue().get(key); // 키 이용하여 값 호출
            map.put(key, data);
        }
        return  map;
    } 

    // **** redis CRUD *** 
    private final ObjectMapper objectMapper = new ObjectMapper(); // 직렬화 객체
    @PostMapping("/member")
    public boolean save(@RequestBody MemberDto memberDto) {
        // 1. 중복 없는 key 구성 예) 도메인명 : 식별키
        String key = "member:"+memberDto.getMno();  // 예] member:3

        // 2. 문자열템플릿에 DTO / 자바객체 대입, DTO -> 문자열(직렬화), 문자열 -> DTO( 역직렬화)
        // .writeValueAsString(자바객체);
        String str = objectMapper.writeValueAsString( memberDto ); // dto -> String 직렬화
        // 3. 레디스에 저장
        stringRedisTemplate.opsForValue().set(key, str);  // { "member:1" : {mno:1, mid:qwe} }
        return  true;
        
        
    }

    // [2] 전체조회
    @GetMapping ("/member")
    public List<MemberDto> findAll() throws JsonMappingException, JsonProcessingException{
        // 1. 특정 패턴의 key 조회 member:*, 로 시작하는 모든 키 조회
        Set<String> keys = stringRedisTemplate.keys("member:*");
        // 2. 모든 키 반복 하여 하나씩 키에 대응하는 dto(값)호출
        List<MemberDto> list = new ArrayList<>();
        for( String key : keys){
            String vSalue = stringRedisTemplate.opsForValue().get(key);

            // 3. 역직렬화, 문자열 -> 자바객체 
            // objectMapper.readValue( 값 , 타입명.class );
            MemberDto memberDto = objectMapper.readValue( vSalue,  MemberDto.class);
            list.add(memberDto);
        }
        return list;
    }

    // [3] 개별 조회  http://localhost:8080/api/redis/member/find?mno=2
    @GetMapping("/member/find")
    public MemberDto find(@RequestParam  (name = "mno") Long mno) {
        // 1. 조회할 mno 매개변수로 받는다.
        // 2. 레디스에서 특정 mno의 키 조회
        String findKey = "member:"+mno;
        String value = stringRedisTemplate.opsForValue().get(findKey);
        if(value == null) return  null;

        // 3. 역직렬화: string - > 자바객체 (dto/ map / list 등)
        MemberDto memberDto = objectMapper.readValue(value, MemberDto.class);
        return  memberDto;
    }
    
    // [4] 삭제
    @DeleteMapping ("/member")
    public boolean delete(@RequestParam (name = "mno") Long mno){
        // 1. 삭제할 mno 매개변수로 받는다
        // 2. 삭제할 key 조합하여 삭제
        String deleteKey = "member:"+mno;
        boolean result = stringRedisTemplate.delete(deleteKey); // .delete(삭제할키)
        return  result;
    }

    // [5] 수정
    @PutMapping("/member")
    public boolean update(@RequestBody MemberDto memberDto) {
        // 1. 수정할 자료들을 dto 로 받는다  // 2. 수정할 key 조합하여 수정
        String updateKey = "member:"+memberDto.getMno();  // 수정할 키 값 꺼내기
        if(updateKey == null) return false;

        // 3. 동일한 키로 입력받은 dto 직렬화 저장
        String value = objectMapper.writeValueAsString(memberDto);  // dto -> String 직렬화
        stringRedisTemplate.opsForValue().set(updateKey, value);
        return true;
    }

    
}
