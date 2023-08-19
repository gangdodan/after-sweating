package com.after.sweating.gathering.service

import com.after.sweating.gathering.dto.GatheringRequest
import com.after.sweating.gathering.entity.Address
import com.after.sweating.gathering.entity.DateTime
import com.after.sweating.gathering.entity.Gathering
import com.after.sweating.gathering.enums.GatheringState
import com.after.sweating.gathering.repository.GatheringRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.transaction.Transactional

@Service
@Transactional
class GatheringCommonService(val repository: GatheringRepository) {
    fun registerGathering(request: GatheringRequest): Long {
        return createGathering(request).id
    }

    fun updateGathering(gatheringId: Long, request: GatheringRequest): Long? {
        val gathering = repository.findByIdOrNull(gatheringId)
        return gathering?.updateGathering(createGathering(request))
    }

    fun createGathering(request: GatheringRequest): Gathering {
        val date = LocalDate.parse(request.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val time = LocalTime.parse(request.time, DateTimeFormatter.ofPattern("hh:mm"))

        return repository.save(
            Gathering(
                title = request.title,
                content = request.content,
                datetime = DateTime(date, time),
                location = Address(request.location.xPoint, request.location.yPoint, request.location.roadAddress),
                capacity = request.capacity
            )
        )
    }

    fun closeGathering(gatheringId: Long): Long? {
        var gathering = repository.findByIdOrNull(gatheringId)
        gathering?.state = GatheringState.CLOSE     //내부 메서드로 변경하자~
        return gathering?.id
    }
}