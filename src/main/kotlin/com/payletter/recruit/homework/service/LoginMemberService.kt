package com.payletter.recruit.homework.service

import com.payletter.recruit.homework.common.dto.request.LoginMemberCommand
import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.common.util.InformationEncrypter
import com.payletter.recruit.homework.common.util.JwtUtil
import com.payletter.recruit.homework.common.util.PasswordEncrypter
import com.payletter.recruit.homework.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class LoginMemberService(
    private val jwtUtil: JwtUtil,
    private val passwordEncrypter: PasswordEncrypter,
    private val informationEncrypter: InformationEncrypter,
    private val memberRepository: MemberRepository
) : LoginMemberUseCase {

    override fun login(loginMemberCommand: LoginMemberCommand): String {
        val findMember =
            memberRepository.findByPhoneNumber(informationEncrypter.encryptString(loginMemberCommand.phoneNumber))
                .orElseThrow { CustomException(NOT_PRESENT_MEMBER)}

        val isAbleToLogin =
            passwordEncrypter.matchPassword(loginMemberCommand.password, findMember.password)

        if (!isAbleToLogin) throw CustomException(INVALID_LOGIN_ID_OR_PASSWORD)

        return jwtUtil.generateToken(loginMemberCommand.phoneNumber)
    }


}