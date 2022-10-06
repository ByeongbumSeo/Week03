package com.sparta.week03.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@ToString
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Memo extends Timestamped { // 상속되었기 때문에 생성,수정 시간이 자동으로 만들어진다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다., 아이디값 하나씩 증가
    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    private Long id;
    //id라는 멤버변수는 @Id와 같이 primary key로 사용한다고 하는 것을 스프링에게 알려주어서,
    //유일한 값으로 객체들이 구분될 수 있도록 하는 역할
    @Column(nullable = false) //@Column : 이 테이블의 컬럼 값이고, nullable = false: 반드시 값이 존재해야 함을 나타냅니다.
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;
@JsonIgnore
    @Column(nullable = false)
    private String password;

    public Memo(String username, String contents, String title, String password) {
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.password = password;
    } // 생성자 추가

    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }
    //RequestDto : 클래스를 바로 가져다 쓰지 않는다. 데이터를 조작하거나 생성하기 위해서는 이 클래스에 내용을 담는 그릇을 만들어서 써야함.
    //memo라는 클래스 안에서 그릇역할만 하는 dto를 파라미터로 하는, 생성자 추가
    public void update(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }
    public void check(MemoDeleteRequestDto deleteRequestDto){
        this.password = deleteRequestDto.getPassword();
    }
}
