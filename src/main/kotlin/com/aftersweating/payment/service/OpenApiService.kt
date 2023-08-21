package com.aftersweating.payment.service

import com.after.sweating.common.exception.CustomException
import com.after.sweating.common.exception.ErrorCode
import com.aftersweating.attendance.dto.AttendanceRequest
import com.aftersweating.payment.Payment
import com.aftersweating.payment.dto.KakaopayApprovalResponse
import com.aftersweating.payment.dto.KakaopayReadyResponse
import com.aftersweating.payment.dto.PaymentRequest
import com.fasterxml.jackson.databind.ObjectMapper
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
    var readyResponse: KakaopayReadyResponse? = null
    var approvalResponse: KakaopayApprovalResponse? = null
    fun requestRedirectURL(request: AttendanceRequest): String? {
        val rt = RestTemplate()
        val uri: URI = URI.create("https://kapi.kakao.com/v1/payment/ready")

        val header = HttpHeaders()
        header.set("Authorization", "KakaoAK {SERVICE_APP_ADMIN_KEY}")
        header.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val param = LinkedMultiValueMap<String, String>()
        param.add("cid", "TC0ONETIME")
        param.add("partner_order_id", request.attendanceId)
        param.add("partner_user_id", request.userId)
        param.add("item_name", request.attendanceName)
        param.add("quantity", request.quantity.toString())
        param.add("total_amount",request.amount.toString())
        param.add("tax_free_amount",request.amount.toString() )
        param.add("approval_url", "http://localhost:8080/")
        param.add("cancel_url", "http://localhost:8080/")
        param.add("fail_url","http://localhost:8080/" )

        try {
            readyResponse = rt.postForObject(
                uri,
                HttpEntity(param, header),
                KakaopayReadyResponse::class.java
            )
        } catch (e: Exception) {
            throw CustomException(ErrorCode.DATA_NOT_FOUND, (""))
        }
        if (readyResponse == null) throw CustomException(ErrorCode.DATA_NOT_FOUND, (""))
        return readyResponse?.next_redirect_pc_url;
    }

    fun requestApproval(pgToken: String){
        val rt = RestTemplate()
        val uri: URI = URI.create("https://kapi.kakao.com/v1/payment/approve")

        val header = HttpHeaders()
        header.set("Authorization", "KakaoAK {SERVICE_APP_ADMIN_KEY}")
        header.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val param = LinkedMultiValueMap<String, String>()
        param.add("cid", "TC0ONETIME")
        param.add("tid", readyResponse?.tid)
        param.add("partner_order_id", "")
        param.add("partner_user_id", "request.userId")
        param.add("pg_token", pgToken)

        try {
            approvalResponse = rt.postForObject(
                uri,
                HttpEntity(param, header),
                KakaopayApprovalResponse::class.java
            )
        } catch (e: Exception) {
            throw CustomException(ErrorCode.DATA_NOT_FOUND, (""))
        }
        if (readyResponse == null) throw CustomException(ErrorCode.DATA_NOT_FOUND, (""))
        /**
         * 1.payment 결제 정보 저장
         * 2.결제완료 이벤트 날려서 참여신청 상태 대기 -> 완료로 변경
         * */

    }
}