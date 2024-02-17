package com.payhere.recruit.homework.member.service.impl

import com.payhere.recruit.homework.member.domain.dto.request.CreateMemberCommand
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.common.util.PasswordEncrypter
import com.payhere.recruit.homework.member.domain.entity.MemberJpaEntity
import com.payhere.recruit.homework.member.repository.MemberRepository
import com.payhere.recruit.homework.member.service.CreateMemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 회원을 생성하기 위한 서비스 클래스입니다.
 *
 * @property memberRepository 회원 엔티티를 관리하기 위한 MemberRepository 인스턴스입니다.
 * @property passwordEncrypter 비밀번호를 암호화하기 위한 PasswordEncrypter 인스턴스입니다.
 */
@Service
@Transactional
class CreateMemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncrypter: PasswordEncrypter
) : CreateMemberUseCase {

    /**
     * 제공된 명령을 기반으로 새 회원을 생성합니다.
     *
     * @param command 회원 생성을 위한 데이터가 포함된 명령입니다.
     * @return 생성된 회원의 ID입니다.
     * @throws CustomException 동일한 휴대폰 번호를 가진 회원이 이미 존재하는 경우 발생합니다.
     */
    override fun createUser(command: CreateMemberCommand): Long {

        val isPresent = memberRepository.existsByPhoneNumber(command.phoneNumber)
        if (isPresent) throw CustomException(IS_ALREADY_EXISTS_MEMBER)

        val member = MemberJpaEntity(
            phoneNumber = command.phoneNumber,
            password = passwordEncrypter.encodeString(command.password)
        )

        memberRepository.save(member)
        return member.memberId!!
    }
}
