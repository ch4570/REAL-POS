package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.domain.Member
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.repository.MemberRepository
import com.payhere.recruit.homework.service.LoadMemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LoadMemberService(
    private val memberRepository: MemberRepository
) : LoadMemberUseCase {

    override fun loadUserByPhoneNumber(phoneNumber: String) : Member {
        val findMember = memberRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow { CustomException(NOT_EXISTS_MEMBER) }

        return Member(findMember.phoneNumber)
    }

}