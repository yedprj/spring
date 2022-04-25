package com.samin.spring.service;

import com.samin.spring.domain.board.Board;
import com.samin.spring.domain.board.BoardRepository;
import com.samin.spring.domain.user.User;
import com.samin.spring.dto.board.BoardSaveRequestDto;
import com.samin.spring.dto.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 글 작성 로직
    @Transactional
    public Long save(BoardSaveRequestDto boardSaveRequestDto, User user) {
        boardSaveRequestDto.setUser(user);
        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
    }

    /*
      글 목록 로직
    */
    @Transactional(readOnly = true)
    public Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);
    }

    /*
       글 상세 로직
    */
    @Transactional(readOnly = true)
    public Board detail(Long id) {
       return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
    }

    /*
       글 삭제 로직
    */
    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    /*
      글 수정 로직
    */
    @Transactional
    public Long update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return id;
    }

    @Transactional
    public int updateCount(Long id) {
        return boardRepository.updateCount(id);
    }
}
