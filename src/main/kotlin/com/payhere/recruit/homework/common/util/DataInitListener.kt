package com.payhere.recruit.homework.common.util
import com.payhere.recruit.homework.member.domain.dto.request.CreateMemberCommand
import com.payhere.recruit.homework.member.service.CreateMemberUseCase
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 응용 프로그램 시작 시 데이터를 초기화하는 구성 요소 클래스입니다.
 *
 * @property createMemberUseCase 회원을 저장해주는 CreateMemberUseCase 인스턴스입니다.
 */
@Component
@Transactional
class DataInitListener(
    private val createMemberUseCase: CreateMemberUseCase
) {

    /**
     * 애플리케이션 시작 시 데이터를 초기화합니다.
     */
    @EventListener(ApplicationReadyEvent::class)
    fun initData() {
        val createMemberCommand = CreateMemberCommand(
            phoneNumber = "010-7777-7777",
            password = "payhere12345"
        )

        createMemberUseCase.createUser(createMemberCommand)
    }
}
