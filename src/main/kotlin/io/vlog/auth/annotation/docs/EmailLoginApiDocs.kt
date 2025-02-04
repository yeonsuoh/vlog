package io.vlog.auth.annotation.docs

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.tags.Tag

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Tag(name = "이메일 로그인", description = "자체 서비스 이메일 로그인")
@Operation(
    summary = "사용자가 회원 가입한 이메일로 코드가 포함된 링크를 받아 클릭했을 때 호출되는 API",
    description =
    """
       - 요청 파라미터 : code (query string)
       - code는 이메일 로그인에 사용된 이후 삭제됩니다.
       - code의 유효성을 검증하므로 Swagger에서 테스트하기 전에 유효한 code의 생성이 필요합니다.
    """,
    parameters = [
        Parameter(
            name = "code",
            description = "인증 코드",
            required = true,
            examples = [
                ExampleObject(name = "code", value = "ZD1sq39nD6TdIdYRM87U")
            ]
        )
    ]
)
annotation class EmailLoginApiDocs