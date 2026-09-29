package example.day57_day11_0929;
import example.Spring_P_Smart_Clotset.AddController;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor 
public class MemberService {
    private final MemberRepository memberRepository;

    // *** [*] 비크립트(단방향 암호화사용) 라이브러리 객체 주입
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    
    // [1] 회원가입 = 등록 = Create = C, 
    public boolean signUp(MemberDto memberDto){
        // 1) 회원가입/등록할 정보들 컨트롤러에게 받아 
        // 2) entity 변환
        MemberEntity memberEntity = memberDto.toEntity();
        // 3) entity save
            // *** 저장하기전 평문(원문 비밀번호) --> 암호문으로 변환
            // passwordEncoder.encode("평문");  암호문 변환
        memberEntity.setMpwd( passwordEncoder.encode( memberDto.getMpwd() )); // 입력받은 평문 -> 암호문

        MemberEntity saved = memberRepository.save(memberEntity);
        if( saved.getMno() >= 1){ return  true;}
        return  false;
    }

    // [2] 로그인 = 조회 = Read = R
    public MemberDto login(MemberDto memberDto){
        // 1) 로그인시 입력받은 아이디/비밀번호 컨트롤러에게 받는다.
        // 2) 입력받은 아이디가 존재하는지 검증, findByMid 추상 정의.
        MemberEntity memberEntity = memberRepository.findByMid(memberDto.getMid());
        if(memberEntity == null){return null;}  // 아이디가 존재하지 않으면 null 반환
        
        // 3) 존재하면 [***] 평문(로그인시 입력받은)과 암호문(회원가입시 입력받은) 비교!
        // passwordEncoder.matches( "평문", "암호문" )
        boolean 비밀번호일치 = passwordEncoder.matches( memberDto.getMpwd(), memberEntity.getMpwd() );
        if(비밀번호일치==false){return  null;} // 비밀번호 불일치 이면 로그인 실패 ,, null 이 실패

        // 4) entity -> dto 변환하여 반환, 주로  로그인 전용 loginDto 있으면 좋다?
        return MemberDto.from(memberEntity);
    }

    // [3] 내 정보조회 ( PK: 회원번호 ) 
    public MemberDto getMyInfo( Long mno ){
        // 1) 컨트롤러에게 조회할 회원 번호 받음
        // 2) 해당하는 회원번호의 정보 확인
        Optional<MemberEntity> optional = memberRepository.findById(mno);
        if( optional.isPresent() ){
            MemberEntity memberEntity = optional.get();
            return MemberDto.from(memberEntity);
        }
        return  null;  // 4) 조회 결과 없으면 null 
    }
    
    // [4] 로그아웃
    
    
}
