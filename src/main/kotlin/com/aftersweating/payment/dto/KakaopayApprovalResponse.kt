package com.aftersweating.payment.dto

import java.time.LocalDateTime

class KakaopayApprovalResponse(
    val aid: String,
    val tid: String,
    val cid: String,
    val sid: String,
    val amount: Amount,
    val card_info: CardInfo,
    val item_name: String,
    val item_code: String,
    val quantity: Int,
    val created_at: LocalDateTime,
    val approved_at: LocalDateTime,
    val payload: String,


    ) {
    class Amount(
        val total: Int,
        val tax_free: Int,
        val vat: Int,
        val point: Int,
        val discount: Int,
        val green_deposit: Int
    )

    class CardInfo(
        val purchase_corp: String,
        val purchase_corp_code: String,
        val issuer_corp: String,
        val issuer_corp_code: String,
        val kakaopay_purchase_corp: String,
        val kakaopay_purchase_corp_code: String,
        val kakaopay_issuer_corp: String,
        val kakaopay_issuer_corp_code: String,
        val bin: String,
        val card_type: String,
        val install_month: String,
        val approved_id: String,
        val card_mid: String,
        val interest_free_install: String,
        val card_item_code: String
    )
}