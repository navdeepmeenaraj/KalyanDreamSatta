package kalyan.dream.en.india.web_service.model

data class UserBankDetails(
    val error: Boolean,
    val gpay: String,
    val message: String,
    val paytm: String,
    val phonepe: String,
    val status: Boolean
)