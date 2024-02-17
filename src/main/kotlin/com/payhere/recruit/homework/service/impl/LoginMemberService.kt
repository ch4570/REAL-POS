package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.dto.request.LoginMemberCommand
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.common.util.PasswordEncrypter
import com.payhere.recruit.homework.repository.MemberRepository
import com.payhere.recruit.homework.service.LoginMemberUseCase
import com.payhere.recruit.homework.service.CreateJwtTokenUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LoginMemberService(
    private val passwordEncrypter: PasswordEncrypter,
    private val memberRepository: MemberRepository,
    private val saveJwtTokenUseCase: CreateJwtTokenUseCase
) : LoginMemberUseCase {

    override fun login(loginMemberCommand: LoginMemberCommand): String {
        val findMember =
            memberRepository.findByPhoneNumber(loginMemberCommand.phoneNumber)
                .orElseThrow { CustomException(NOT_EXISTS_MEMBER)}

        // 회원 비밀번호 일치여부 확인
        val isAbleToLogin =
            passwordEncrypter.matchPassword(loginMemberCommand.password, findMember.password)

        if (!isAbleToLogin) throw CustomException(INVALID_LOGIN_ID_OR_PASSWORD)

        // Token DB 저장
        return saveJwtTokenUseCase.createJwtToken(findMember.phoneNumber, findMember.memberId!!)
    }


}