package com.payletter.recruit.homework.service.impl

import com.payletter.recruit.homework.common.dto.request.CreateMemberCommand
import com.payletter.recruit.homework.common.exception.CustomException
import com.payletter.recruit.homework.common.exception.ErrorCode.*
import com.payletter.recruit.homework.common.util.InformationEncrypter
import com.payletter.recruit.homework.common.util.PasswordEncrypter
import com.payletter.recruit.homework.domain.entity.MemberJpaEntity
import com.payletter.recruit.homework.repository.MemberRepository
import com.payletter.recruit.homework.service.CreateMemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateMemberService(
    private val memberRepository: MemberRepository,
    private val informationEncrypter: InformationEncrypter,
    private val passwordEncrypter: PasswordEncrypter
) : CreateMemberUseCase {

    override fun createUser(userCommand: CreateMemberCommand): Long {

        val isPresent = memberRepository.existsByPhoneNumber(informationEncrypter.encryptString(userCommand.phoneNumber))
        if (isPresent) throw CustomException(IS_ALREADY_PRESENT_MEMBER)

        val member = MemberJpaEntity(
            phoneNumber = informationEncrypter.encryptString(userCommand.phoneNumber),
            password = passwordEncrypter.encodeString(userCommand.password)
        )

        memberRepository.save(member)
        return member.memberId!!
    }
}