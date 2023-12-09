package com.spartanullnull.otil.domain.like.controller;

import com.spartanullnull.otil.domain.like.service.CommentLikeService;
import com.spartanullnull.otil.security.Impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    // 댓글 좋아요 누르기 API
    @PostMapping("/{postId}/comments/{commentId}/like")
    public ResponseEntity<String> createLikeForComment(
        @PathVariable Long postId,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentLikeService.createLikeForComment(userDetails.getUser().getId(), commentId);
        return ResponseEntity.ok("댓글 좋아요 성공!");
    }

    // 댓글 좋아요 취소하기 API
    @DeleteMapping("/{postId}/comments/{commentId}/like")
    public ResponseEntity<String> deleteLikeForComment(
        @PathVariable Long postId,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentLikeService.deleteLikeForComment(userDetails.getUser().getId(), commentId);
        return ResponseEntity.ok("댓글 좋아요 취소!");
    }

    // 댓글 좋아요 개수 조회 API
    @GetMapping("/{postId}/comments/{commentId}/count")
    public ResponseEntity<String> getLikeCountForComment(@PathVariable Long commentId) {
        Long likeCount = commentLikeService.getLikeCountForComment(commentId);
        return ResponseEntity.ok("댓글 좋아요 : " + likeCount);
    }
}