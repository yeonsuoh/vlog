package io.vlog.email.domain.constant

object EmailConstant {
    const val CODE_LENGTH = 20
    const val SERVER_URL = "http://localhost:8080" // todo profile separate

    // register
    const val REGISTER_URI = "/register"
    const val REGISTER_PARAM = "?code="
    const val REGISTER_EMAIL_TITLE = "Vlog 회원가입"

    // login
    const val LOGIN_URI = "/email-login"
    const val LOGIN_PARAM = "?code="
    const val LOGIN_EMAIL_TITLE = "Vlog 로그인"

    fun getRegisterEmailContent(
        code: String
    ): String {
        return """
<html>
    <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;">
        <div style="width: 100%; max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
            <div style="font-size: 24px; font-weight: bold; color: #333333; margin-bottom: 20px;">
                회원가입을 계속하시려면 하단의 링크를 클릭하세요.
            </div>
            <div style="font-size: 16px; color: #555555; line-height: 1.5; margin-bottom: 20px;">
                만약 실수로 요청하셨거나, 본인이 요청하지 않았다면 이 메일을 무시하세요.
                <br><br>
                <a href="${SERVER_URL}${REGISTER_URI}${REGISTER_PARAM}${code}" style="display: inline-block; background-color: #007bff; color: #ffffff; padding: 12px 20px; font-size: 16px; text-decoration: none; border-radius: 5px; margin-top: 20px;">계속하기</a>
                <br><br>
                위 버튼을 클릭하시거나, 아래 링크를 열으세요:
                <br>
                <a href="${SERVER_URL}${REGISTER_URI}${REGISTER_PARAM}${code}" style="color: #007bff; text-decoration: none;">
                    ${SERVER_URL}${REGISTER_URI}${REGISTER_PARAM}${code}
                </a>
                <br><br>
                이 링크는 24시간 동안 유효합니다.
            </div>
            <div style="font-size: 14px; color: #777777; text-align: center; margin-top: 30px;">
                감사합니다.<br>
            </div>
        </div>
    </body>
</html>

        """.trimIndent()
    }

    fun getLoginEmailContent(
        code: String
    ): String {
        return """
<html>
    <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;">
        <div style="width: 100%; max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
            <div style="font-size: 24px; font-weight: bold; color: #333333; margin-bottom: 20px;">
                로그인을 계속하시려면 하단의 링크를 클릭하세요.
            </div>
            <div style="font-size: 16px; color: #555555; line-height: 1.5; margin-bottom: 20px;">
                만약 실수로 요청하셨거나, 본인이 요청하지 않았다면 이 메일을 무시하세요.
                <br><br>
                <a href="${SERVER_URL}${LOGIN_URI}${LOGIN_PARAM}${code}" style="display: inline-block; background-color: #007bff; color: #ffffff; padding: 12px 20px; font-size: 16px; text-decoration: none; border-radius: 5px; margin-top: 20px;">계속하기</a>
                <br><br>
                위 버튼을 클릭하시거나, 아래 링크를 열으세요:
                <br>
                <a href="${SERVER_URL}${LOGIN_URI}${LOGIN_PARAM}${code}" style="color: #007bff; text-decoration: none;">
                    ${SERVER_URL}${LOGIN_URI}${LOGIN_PARAM}${code}
                </a>
                <br><br>
                이 링크는 24시간 동안 유효합니다.
            </div>
            <div style="font-size: 14px; color: #777777; text-align: center; margin-top: 30px;">
                감사합니다.<br>
            </div>
        </div>
    </body>
</html>

        """.trimIndent()
    }
}