package kalyan.dream.en.india.web_service.model

data class PasswordReset(
    val mobile: String,
    val password: String,
    val confirmPassword: String
)
