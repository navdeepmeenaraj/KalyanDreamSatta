package kalyan.dream.en.india.web_service.model

data class OtpRequestModel(
    val msg: String,
    val otp: Int,
    val status: Boolean

)