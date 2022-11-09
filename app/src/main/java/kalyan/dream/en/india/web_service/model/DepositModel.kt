package kalyan.dream.en.india.web_service.model

data class DepositModel(
    val user_id: String,
    val amount: String,
    val transaction_id: String,
    val payment_app: String
)
