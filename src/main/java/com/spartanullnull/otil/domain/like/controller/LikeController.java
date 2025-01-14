package com.spartanullnull.otil.domain.like.controller;

import com.spartanullnull.otil.domain.comment.entity.Comment;
import com.spartanullnull.otil.domain.like.service.LikeService;
import com.spartanullnull.otil.domain.post.entity.Post;
import com.spartanullnull.otil.security.Impl.UserDetailsImpl;
import java.util.List;
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
public class LikeController {

    private final LikeService likeService;

    // 좋아요 누르기 API
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> createLikeForPost(
        @PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.createLikeForPost(userDetails.getUser().getId(), postId);
        return ResponseEntity.ok("좋아요 성공!");
    }

    // 좋아요 취소하기 API
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> deleteLikeForPost(
        @PathVariable Long postId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.deleteLikeForPost(userDetails.getUser().getId(), postId);
        return ResponseEntity.ok("좋아요 취소!");
    }

    // 좋아요 개수 조회 API
    @GetMapping("/{postId}/count")
    public ResponseEntity<String> getLikeCountForPost(@PathVariable Long postId) {
        Long likeCount = likeService.getLikeCountForPost(postId);
        return ResponseEntity.ok("좋아요 : " + likeCount);
    }

    // 댓글 좋아요 누르기 API
    @PostMapping("/{postId}/comments/{commentId}/like")
    public ResponseEntity<String> createLikeForComment(
        @PathVariable Long postId,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.createLikeForComment(userDetails.getUser().getId(), commentId);
        return ResponseEntity.ok("댓글 좋아요 성공!");
    }

    // 댓글 좋아요 취소하기 API
    @DeleteMapping("/{postId}/comments/{commentId}/like")
    public ResponseEntity<String> deleteLikeForComment(
        @PathVariable Long postId,
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        likeService.deleteLikeForComment(userDetails.getUser().getId(), commentId);
        return ResponseEntity.ok("댓글 좋아요 취소!");
    }

    // 댓글 좋아요 개수 조회 API
    @GetMapping("/{postId}/comments/{commentId}/count")
    public ResponseEntity<String> getLikeCountForComment(@PathVariable Long commentId) {
        Long likeCount = likeService.getLikeCountForComment(commentId);
        return ResponseEntity.ok("댓글 좋아요 : " + likeCount);
    }

    // 좋아요가 가장 많은 상위 3개의 게시물 조회 API
    @GetMapping("/top3")
    public ResponseEntity<List<Post>> getTop3PostsByLikes() {
        // 좋아요가 가장 많은 상위 3개의 게시물 조회
        List<Post> top3Posts = likeService.getTop3PostsByLikes();
        return ResponseEntity.ok(top3Posts);
    }
}