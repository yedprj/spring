package com.samin.spring.dto;

import com.samin.spring.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class HelloResponseDtoTest {

    @Test
    public void lombok_test() {
        //given
        String name = "minsu";
        String nickname = "babo";

        //when
        //필드가 포함된 생성자를 만들어 준다
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, nickname);

        //then
        // get 메소드를 선언하지 않아도 getName()을 사용할 수 있다
        assertThat(helloResponseDto.getName(), is(equalTo(name)));
        assertThat(helloResponseDto.getNickname(), is(equalTo(nickname)));
    }
}
