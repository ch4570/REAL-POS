package com.payhere.recruit.homework.member.service.impl

import com.payhere.recruit.homework.member.domain.dto.request.LoginMemberCommand
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.common.util.PasswordEncrypter
import com.payhere.recruit.homework.member.repository.MemberRepository
import com.payhere.recruit.homework.member.service.LoginMemberUseCase
import com.payhere.recruit.homework.member.service.CreateJwtTokenUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 회원 로그인을 처리하기 위한 서비스 클래스입니다.
 *
 * @property passwordEncrypter 비밀번호를 암호화하기 위한 PasswordEncrypter 인스턴스입니다.
 * @property memberRepository 회원 엔티티를 관리하기 위한 MemberRepository 인스턴스입니다.
 * @property createJwtTokenUseCase JWT 토큰을 생성하기 위한 CreateJwtTokenUseCase 인스턴스입니다.
 */
@Service
@Transactional
class LoginMemberService(
    private val passwordEncrypter: PasswordEncrypter,
    private val memberRepository: MemberRepository,
    private val createJwtTokenUseCase: CreateJwtTokenUseCase
) : LoginMemberUseCase {

    /**
     * 제공된 로그인 명령을 기반으로 회원을 로그인합니다.
     *
     * @param loginMemberCommand 전화번호와 비밀번호를 포함하는 로그인 명령입니다.
     * @return 성공적인 로그인 시 생성된 JWT 토큰입니다.
     * @throws CustomException 제공된 자격 증명이 잘못되었거나 회원이 존재하지 않는 경우 발생합니다.
     */
    override fun login(loginMemberCommand: LoginMemberCommand): String {
        val findMember =
            memberRepository.findByPhoneNumber(loginMemberCommand.phoneNumber)
                .orElseThrow { CustomException(NOT_EXISTS_MEMBER)}

        // 회원 비밀번호 일치여부 확인
        val isAbleToLogin =
            passwordEncrypter.matchPassword(loginMemberCommand.password, findMember.password)

        if (!isAbleToLogin) throw CustomException(INVALID_LOGIN_ID_OR_PASSWORD)

        return createJwtTokenUseCase.createJwtToken(findMember.phoneNumber, findMember.memberId!!)
    }
}
