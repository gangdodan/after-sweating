package com.after.sweating.gathering.controller

import com.after.sweating.gatherings.service.CommentCommonService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/gatherings")
class GatheringQueryController(
    private var gatheringService: CommentCommonService
) {

}