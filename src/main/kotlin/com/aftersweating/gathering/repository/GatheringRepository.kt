package com.after.sweating.gathering.repository

import com.after.sweating.gathering.entity.Gathering
import org.springframework.data.jpa.repository.JpaRepository

interface GatheringRepository : JpaRepository<Gathering,Long> {
}