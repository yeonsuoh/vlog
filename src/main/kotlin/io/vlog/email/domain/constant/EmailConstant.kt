package io.vlog.email.domain.constant

object EmailConstant {
    const val CODE_LENGTH = 20
    const val SERVER_URL = "http://localhost:8080" // todo profile separate
    const val REGISTER_URI = "/register"
    const val REGISTER_PARAM = "?code="
    const val REGISTER_EMAIL_TITLE = "Vlog 회원가입"

    fun getRegisterEmailContent(
        code: String
    ): String {
        return """
        <html>
            <body>
                <p>다음 링크로 회원가입을 진행하세요:</p>
                <a href="${SERVER_URL}${REGISTER_URI}${REGISTER_PARAM}$code">
                    ${SERVER_URL}${REGISTER_URI}${REGISTER_PARAM}$code
                </a>
                <p>이 링크는 24시간 동안 유효합니다.</p>
            </body>
        </html>
        """.trimIndent()
    }
}