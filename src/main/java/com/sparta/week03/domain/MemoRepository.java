package com.sparta.week03.domain;

import com.sparta.week03.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.time.LocalDateTime;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
//MemoRepository만 가지고는, JPA를 활용할 수 없다. 그래서 미리 구현된 JpaRepository를 가져와서 사용(상속)
    List<Memo> findAllByOrderByModifiedAtDesc();     // JPA 공식문서를 보고 규칙에 맞게 작성한다면 JPA가 알아서 SQL문 짜줌 => 최신순 정렬
}
// Timestamped의 ModifiedAt 을 가져온다.
// find All By Order By ModifiedAt at Desc
// 수정된 날짜를 기준으로 내림차순 정렬 해줘


// 조회 시간으로부터 24시간 이내로 바꾸기 :
// List<Memo> findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime start, LocalDateTime end);