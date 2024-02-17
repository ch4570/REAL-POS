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

/**
 * Service class for creating members.
 *
 * @property memberRepository The MemberRepository instance for managing member entities.
 * @property passwordEncrypter The PasswordEncrypter instance for encrypting passwords.
 */
@Service
@Transactional
class CreateMemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncrypter: PasswordEncrypter
) : CreateMemberUseCase {

    /**
     * Creates a new member based on the provided command.
     *
     * @param command The command containing data for creating the member.
     * @return The ID of the created member.
     * @throws CustomException if a member with the same phone number already exists.
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