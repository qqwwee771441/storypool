package com.wudc.storypool.domain.story.service;

import com.wudc.storypool.domain.auth.service.AuthService;
import com.wudc.storypool.domain.story.dto.*;
import com.wudc.storypool.domain.story.entity.Story;
import com.wudc.storypool.domain.story.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class StoryService {
    private final AuthService authService;
    private final StoryRepository storyRepository;

    public GetStoryListResponse getStoryList(String after, int limit) {
        String userId = authService.getUserId();

        // limit 유효성 검사 (1~50)
        if (limit < 1 || limit > 50) {
            throw new RuntimeException("limit는 1~50 사이여야 합니다.");
        }

        // Spring Data Pageable 생성 (limit+1을 가져와서 hasNext 판별)
        Pageable pageReq = PageRequest.of(0, limit + 1);

        List<Story> fetched;
        if (after != null && !after.isBlank()) {
            fetched = storyRepository
                    .findByUserIdAndIdLessThanOrderByIdDesc(userId, after, pageReq);
        } else {
            fetched = storyRepository
                    .findByUserIdOrderByIdDesc(userId, pageReq);
        }

        // hasNext, nextCursor 계산: limit+1개 중 앞 limit개만 실제 응답
        boolean hasNext = fetched.size() > limit;
        List<Story> page = hasNext
                ? fetched.subList(0, limit)
                : fetched;

        String nextCursor = hasNext
                ? page.getLast().getId()
                : null;

        // DTO로 변환
        List<StorySummary> stories = page.stream()
                .map(d -> new StorySummary(
                        d.getId(),
                        d.getName(),
                        // excerpt: 100자 내외
                        d.getText().length() <= 100
                                ? d.getText()
                                : d.getText().substring(0, 100) + "...",
                        d.getCreatedAt().toString(),
                        d.getUpdatedAt().toString()
                ))
                .toList();

        return new GetStoryListResponse(stories, hasNext, nextCursor);
    }

    public GetStoryResponse getStory(String id) {
        Story story = storyRepository.findById(id).orElseThrow();
        return new GetStoryResponse(story.getId(), story.getName(), story.getText());
    }

    public CreateStoryResponse createStory(CreateStoryRequest req) {
        Story story = storyRepository.save(new Story(authService.getUserId(), req.getName(), req.getText()));
        return new CreateStoryResponse(story.getId(), story.getCreatedAt().toString());
    }

    public UpdateStoryResponse updateStory(String id, UpdateStoryRequest req) {
        Story story = storyRepository.findById(id).orElseThrow();
        story.update(req.getName(), req.getText());
        return new UpdateStoryResponse(story.getId(), story.getUpdatedAt().toString());
    }

    public DeleteStoryResponse deleteStory(String id) {
        storyRepository.deleteById(id);
        return new DeleteStoryResponse("Draft deleted successfully.");
    }
}
