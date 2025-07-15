package com.wudc.storypool.domain.story.repository;

import com.wudc.storypool.domain.story.entity.Story;
import com.wudc.storypool.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository  extends JpaRepository<Story, String> {
    // after(cursor)가 있을 때
    List<Story> findByUserIdAndIdLessThanOrderByIdDesc(
            String userId, String after, Pageable pageable);

    // 첫 페이지(커서 없음)일 때
    List<Story> findByUserIdOrderByIdDesc(
            String userId, Pageable pageable);
}
