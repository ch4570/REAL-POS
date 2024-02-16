package com.payletter.recruit.homework.service

import com.payletter.recruit.homework.common.dto.request.CreateMemberCommand
import com.payletter.recruit.homework.common.util.InformationEncrypter
import com.payletter.recruit.homework.common.util.PasswordEncrypter
import com.payletter.recruit.homework.domain.entity.MemberJpaEntity
import com.payletter.recruit.homework.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class CreateMemberService(
    private val memberRepository: MemberRepository,
    private val informationEncrypter: InformationEncrypter,
    private val passwordEncrypter: PasswordEncrypter
) : CreateMemberUseCase {

    override fun createUser(userCommand: CreateMemberCommand): Long {
        val member = MemberJpaEntity(
            phoneNumber = informationEncrypter.encryptString(userCommand.phoneNumber),
            password = passwordEncrypter.encodeString(userCommand.password)
        )

        memberRepository.save(member)
        return member.memberId!!
    }


}