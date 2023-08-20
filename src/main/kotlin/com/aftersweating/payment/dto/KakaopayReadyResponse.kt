package com.aftersweating.payment.dto

class KakaopayReadyResponse(
    val tid: String,
    val next_redirect_mobile_url: String,
    val next_redirect_pc_url: String,
    val ios_app_scheme: String,
    val created_at: String

)