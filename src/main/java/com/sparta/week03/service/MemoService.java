package com.sparta.week03.service;

import com.sparta.week03.domain.Memo;
import com.sparta.week03.domain.MemoDeleteRequestDto;
import com.sparta.week03.domain.MemoRequestDto;
import com.sparta.week03.domain.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service // 스프링에게 이 클래스는 서비스임을 명시, 최상위에 작성
public class MemoService {

    private final MemoRepository memoRepository;
// final: 서비스에게 꼭 필요한 녀석임을 명시
//repsitory를 사용하기 위해서 객체를 하나 인스턴스로 만들어줌

    @Transactional
    // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
// @Transactional을 붙이게 되면 이 update라는 메소드가, 이 SQL 쿼리 작업이 일어나야 한다는걸 스프링에게 또 알려주는 것
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        memo.update(requestDto);  //memo : 원본, requestDto : 바뀌어야 하는 내용
        return memo.getId();
//이 Update action이 순서대로 파라미터를 보게되면,
// id값, memo객체를 파라미터로 받은 다음,
//(순서주의!) Memo memo = memoRepository.findById(id): memo에는 일단 그 id로 우리가 바꿀 대상을 찾고,
//찾아서 memo라는 변수에 대입해준다.

    }

    public Long delete(Long id, MemoDeleteRequestDto deleteRequestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
                // IllegalArgumentException : 적합하지 않거나 적절하지 못한 인자를 메소드에 넘겨주었을 때
        );
        memo.delete(deleteRequestDto);
        return memo.getId();
    }
}