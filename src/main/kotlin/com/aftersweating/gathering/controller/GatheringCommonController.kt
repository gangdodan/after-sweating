//package com.after.sweating.gathering.controller
//
//import com.after.sweating.gathering.dto.GatheringRequest
//import com.after.sweating.gathering.service.AttendanceCommonService
//import org.springframework.stereotype.Controller
//import org.springframework.web.bind.annotation.DeleteMapping
//import org.springframework.web.bind.annotation.PatchMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestMapping
//
//@Controller
//@RequestMapping("/gatherings")
//class GatheringCommonController(
//    private var gatheringService: AttendanceCommonService
//) {
//    @PostMapping
//    fun registerGathering(request: GatheringRequest): Long {
//        return gatheringService.registerGathering(request)
//    }
//
//    @PatchMapping("/{gathering-id}")
//    fun editGathering(request: GatheringRequest, @PathVariable("gathering_id") gatheringId: Long): Long? {
//        return gatheringService.updateGathering(gatheringId,request)
//    }
//    @DeleteMapping("/{gathering-id}")
//    fun deleteGathering(request: GatheringRequest, @PathVariable("gathering_id") gatheringId: Long): Long? {
//        return gatheringService.closeGathering(gatheringId)
//    }
//
//}