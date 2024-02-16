package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.common.dto.request.LoginMemberCommand
import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.common.util.EncryptUtil
import com.payletter.recruit.homework.common.util.JwtUtil
import com.payletter.recruit.homework.repository.MemberRepository
import com.payletter.recruit.homework.service.LoginMemberUseCase
import com.payletter.recruit.homework.service.SaveJwtTokenUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LoginMemberService(
    private val jwtUtil: JwtUtil,
    private val encryptUtil: EncryptUtil,
    private val memberRepository: MemberRepository,
    private val saveJwtTokenUseCase: SaveJwtTokenUseCase
) : LoginMemberUseCase {

    override fun login(loginMemberCommand: LoginMemberCommand): String {
        val findMember =
            memberRepository.findByPhoneNumber(encryptUtil.encryptString(loginMemberCommand.phoneNumber))
                .orElseThrow { CustomException(NOT_PRESENT_MEMBER)}

        // 회원 비밀번호 일치여부 확인
        val isAbleToLogin =
            encryptUtil.matchPassword(loginMemberCommand.password, findMember.password)

        if (!isAbleToLogin) throw CustomException(INVALID_LOGIN_ID_OR_PASSWORD)

        val generatedToken = jwtUtil.generateToken(loginMemberCommand.phoneNumber)

        // Token DB 저장
        saveJwtTokenUseCase.saveJwtToken(generatedToken, findMember.memberId!!)

        return generatedToken
    }


}