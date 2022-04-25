package com.samin.spring.controller.api;

import com.samin.spring.config.auth.PrincipalDetail;
import com.samin.spring.dto.board.BoardSaveRequestDto;
import com.samin.spring.dto.board.BoardUpdateRequestDto;
import com.samin.spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    // 글작성 api
    @PostMapping("/api/v1/board")
    public Long save(@RequestBody BoardSaveRequestDto boardSaveRequestDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        return boardService.save(boardSaveRequestDto, principalDetail.getUser());
    }

    // 글삭제 api
    @DeleteMapping("/api/v1/board/{id}")
    public Long deleteById(@PathVariable Long id){
        boardService.deleteById(id);
        return id;
    }

    // 글수정 api
    @PutMapping("/api/v1/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        return boardService.update(id, boardUpdateRequestDto);
    }
}
