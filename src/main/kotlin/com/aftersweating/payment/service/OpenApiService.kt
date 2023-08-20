package com.aftersweating.payment.service

import com.after.sweating.common.exception.CustomException
import com.after.sweating.common.exception.ErrorCode
import com.aftersweating.attendance.dto.AttendanceRequest
import com.aftersweating.payment.dto.KakaopayReadyResponse
import com.aftersweating.payment.dto.PaymentRequest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.Objects

/**
 * 1. 결제 준비_인증
 * 2. 결제 요청
 *
 */
@Service
class OpenApiService() {
    fun requestRedirectURL(request: AttendanceRequest): String {
        val rt = RestTemplate()
        var response: KakaopayReadyResponse? = null

        val uri: URI = URI.create("https://kapi.kakao.com/v1/payment/ready")

        val header = HttpHeaders()
        header.set("Authorization", "KakaoAK {SERVICE_APP_ADMIN_KEY}")
        header.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
        header.contentType = MediaType.APPLICATION_JSON

        val paymentRequest = PaymentRequest(
            cid = "TC0ONETIME",
            partner_order_id ="",
            partner_user_id = "",
            item_name ="",
            quantity =0,
            total_amount =0,
            tax_free_amount =0,
            approval_url =0,
            cancel_url ="",
            fail_url = ""
        )
//        val params = LinkedMultiValueMap<String, String>()
//        params.add("cid", "TC0ONETIME");
//        params.add("partner_order_id", "1001");
//        params.add("partner_user_id", "gorany");
//        params.add("item_name", "갤럭시S9");
//        params.add("quantity", "1");
//        params.add("total_amount", "2100");
//        params.add("tax_free_amount", "100");
//        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
//        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
//        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

        try {
            response = rt.postForObject(
                uri,
                HttpEntity(paymentRequest, header),
                KakaopayReadyResponse::class.java
            )
        } catch (e: Exception) {
            throw CustomException(ErrorCode.DATA_NOT_FOUND, (""))
        }
        if (response == null) return ""
        return response.next_redirect_pc_url;
    }
}