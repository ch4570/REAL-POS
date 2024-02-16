package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.domain.Member
import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.common.util.InformationEncrypter
import com.payletter.recruit.homework.repository.MemberRepository
import com.payletter.recruit.homework.service.LoadMemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LoadMemberService(
    private val memberRepository: MemberRepository,
    private val informationEncrypter: InformationEncrypter
) : LoadMemberUseCase {

    override fun loadUserByPhoneNumber(phoneNumber: String) : Member {
        val findMember = memberRepository.findByPhoneNumber(informationEncrypter.encryptString(phoneNumber))
            .orElseThrow { CustomException(NOT_PRESENT_MEMBER) }

        return Member(informationEncrypter.decryptString(findMember.phoneNumber))
    }

}