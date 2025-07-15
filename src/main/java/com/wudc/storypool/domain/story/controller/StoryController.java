package com.wudc.storypool.domain.story.controller;

import com.wudc.storypool.domain.story.dto.*;
import com.wudc.storypool.domain.story.service.StoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;

    @Operation(summary = "스토리 목록 조회")
    @GetMapping
    public ResponseEntity<GetStoryListResponse> getStoryList(@RequestParam(name = "after", required = false) String after, @RequestParam(name = "limit", defaultValue = "20") int limit) {
        return ResponseEntity.ok(storyService.getStoryList(after, limit));
    }

    @Operation(summary = "스토리 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<GetStoryResponse> getStory(@PathVariable("id") String id) {
        return ResponseEntity.ok(storyService.getStory(id));
    }

    @Operation(summary = "스토리 생성")
    @PostMapping
    public ResponseEntity<CreateStoryResponse> createStory(@RequestBody CreateStoryRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storyService.createStory(req));
    }

    @Operation(summary = "스토리 수정")
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateStoryResponse> updateStory(@PathVariable("id") String id, @RequestBody UpdateStoryRequest req) {
        return ResponseEntity.ok(storyService.updateStory(id, req));
    }

    @Operation(summary = "스토리 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteStoryResponse> deleteStory(@PathVariable("id") String id) {
        return ResponseEntity.ok(storyService.deleteStory(id));
    }
}
