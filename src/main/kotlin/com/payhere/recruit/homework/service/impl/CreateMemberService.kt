package com.payhere.recruit.homework.service.impl

import com.payhere.recruit.homework.common.dto.request.CreateMemberCommand
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.common.util.PasswordEncrypter
import com.payhere.recruit.homework.domain.entity.MemberJpaEntity
import com.payhere.recruit.homework.repository.MemberRepository
import com.payhere.recruit.homework.service.CreateMemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateMemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncrypter: PasswordEncrypter
) : CreateMemberUseCase {

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