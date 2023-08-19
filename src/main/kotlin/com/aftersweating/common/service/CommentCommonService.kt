package com.after.sweating.gatherings.service

import com.after.sweating.comment.dto.CommentRequest
import com.after.sweating.comment.entity.Comment
import com.after.sweating.gathering.repository.CommentRepository
import com.after.sweating.gathering.repository.GatheringRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class CommentCommonService(val commentRepository: CommentRepository, val gatherRepository: GatheringRepository) {
    fun registerComment(request: CommentRequest, userId: Long): Long? {
        return createComment(request, userId).id
    }

    fun updateComment(commentId: Long, userId: Long, request: CommentRequest): Long? {
        val comment = commentRepository.findByIdOrNull(commentId)
        comment?.content = request.content
        return comment?.id
    }

    fun createComment(request: CommentRequest, userId: Long): Comment {
        return commentRepository.save(
            Comment(
                content = request.content,
                gathering = gatherRepository?.findByIdOrNull(request.gatheringId),
                userId = userId
            )
        )
    }

    fun removeComment(commentId: Long, userId: Long) {
        commentRepository.deleteById(commentId)
    }
}