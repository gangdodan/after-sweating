package com.after.sweating.gathering.repository

import com.after.sweating.comment.entity.Comment
import com.after.sweating.gathering.entity.Gathering
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment,Long> {
}