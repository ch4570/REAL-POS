package com.payhere.recruit.homework.member.service.impl

import com.payhere.recruit.homework.member.domain.Member
import com.payhere.recruit.homework.common.exception.CustomException
import com.payhere.recruit.homework.common.exception.ErrorCode.*
import com.payhere.recruit.homework.member.repository.MemberRepository
import com.payhere.recruit.homework.member.service.LoadMemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 회원을 로드하기 위한 서비스 클래스입니다.
 *
 * @property memberRepository 회원 엔티티를 관리하기 위한 MemberRepository 인스턴스입니다.
 */
@Service
@Transactional(readOnly = true)
class LoadMemberService(
    private val memberRepository: MemberRepository
) : LoadMemberUseCase {

    /**
     * 휴대폰 번호에 해당하는 회원을 로드합니다.
     *
     * @param phoneNumber 로드할 회원의 휴대폰 번호입니다.
     * @return 로드된 회원입니다.
     * @throws CustomException 주어진 휴대폰 번호를 가진 회원을 찾을 수 없는 경우 발생합니다.
     */
    override fun loadUserByPhoneNumber(phoneNumber: String): Member {
        val findMember = memberRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow { CustomException(NOT_EXISTS_MEMBER) }

        return Member(findMember.phoneNumber)
    }
}
