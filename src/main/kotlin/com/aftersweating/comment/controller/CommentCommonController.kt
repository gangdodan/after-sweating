package com.after.sweating.comment.controller

import com.after.sweating.comment.dto.CommentRequest
import com.after.sweating.gathering.dto.GatheringRequest
import com.after.sweating.gatherings.service.CommentCommonService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/comments")
class CommentCommonController(
    private var commentService: CommentCommonService

) {
    val userId = 1L //security 적용 전 임시 할당
    @PostMapping
    fun registerComment(request: CommentRequest): Long? {
        return commentService.registerComment(request, userId)
    }

    @PatchMapping("/{comment-id}")
    fun editGathering(request: CommentRequest, @PathVariable("comment_id") commentId: Long): Long? {
        return commentService.updateComment(commentId,userId,request)
    }
    @DeleteMapping("/{comment-id}")
    fun deleteGathering( @PathVariable("comment_id") commentId: Long) {
        return commentService.removeComment(commentId,userId)
    }

}