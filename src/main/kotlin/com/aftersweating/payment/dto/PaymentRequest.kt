package com.aftersweating.payment.dto

import lombok.AccessLevel
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor


data class PaymentRequest(
    private val cid: String,
    private val partner_order_id: String,
    private val partner_user_id: String,
    private val item_name: String,
    private val quantity: Int,
    private val total_amount: Int,
    private val tax_free_amount: Int,
    private val approval_url: String,
    private val cancel_url: String,
    private val fail_url: String
) {

}