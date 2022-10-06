package com.sparta.week03.controller;

import com.sparta.week03.domain.*;
import com.sparta.week03.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@RestController // controller임을 알려주는 표시
public class MemoController {
    private final MemoRepository memoRepository;
    private final MemoService memoService;

    // PostMapping을 통해서, 같은 주소라도 방식이 다름을 구분합니다.
    @PostMapping("/api/memos")  // 이곳으로 들어오는 API주소를 mapping, /api/memos주소로 받겠다(localhost:8080/api/memos)
    // RequestBody를 사용해야 요청이 올 때 Body의 내용을 Dto에 넣어줌.
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        // requestDto 는, 생성 요청을 의미합니다.
        // 저장하는 것은 Dto가 아니라 Memo이니, Dto의 정보를 memo에 담아야 합니다.
        // 잠시 뒤 새로운 생성자를 만듭니다.
        Memo memo = new Memo(requestDto);
        // JPA를 이용하여 DB에 저장하고, 그 결과를 반환합니다.
        return memoRepository.save(memo);
    }
    //@RequestBody:
    // http 통신을 할 때 post의 body에 data를 넣어서 보내겠다라는 의미로,
    // RequestBody에 SearchParam 값들을 매칭시켜서 달라는 의미이다.


//    @GetMapping("/api/memos")
//    public MemoResponseDto getMemo() {
//        return (MemoResponseDto) memoRepository.findAllByOrderByModifiedAtDesc();
//    }
    @GetMapping("/api/memos")
    public List<Memo> getMemo() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/memos/{id}")
    public Optional<Memo> getMemo(@PathVariable Long id){
        return memoRepository.findById(id);
        // 데이터 하나 조회하기(findById)-이 데이터 하나를 유일하게 구분할 수 있는 PK로 ID의 의미를 생각하면 된다.
    }
//@PathVariable 뒤에오는 변수가 주소{id}로 넘어가서 대입됨
//=> PutMapping의 대상을 유일한 하나로 지정해줌

    @PostMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestBody MemoDeleteRequestDto deleteRequestDto) {
        Optional<Memo> memo = memoRepository.findById(id);
        if (memo.get().getPassword().equals(deleteRequestDto.getPassword())) {
            memoService.check(id, deleteRequestDto);
            return "true";
        } else {
            return "false";
        }
    }


    @PutMapping("/api/memos/{id}")
    public String updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        Optional<Memo> memo = memoRepository.findById(id);
        if (memo.get().getPassword().equals(requestDto.getPassword())) {
            memoService.update(id, requestDto);
            return "업데이트 완료";
        } else {
            return "비밀번호 불일치";
        }
    }
    @DeleteMapping("/api/memos/{id}")
    public String deletememo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return "true";
    }
}


