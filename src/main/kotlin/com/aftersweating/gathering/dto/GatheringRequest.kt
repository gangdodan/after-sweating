package com.after.sweating.gathering.dto


class GatheringRequest(
    val location: AddressRequest,
    val date: String,
    val time : String,
    val capacity : Int,
    val title : String,
    val content : String
) {
    class AddressRequest(
        val xPoint: String,
        val yPoint: String,
        val roadAddress : String
    )


}
