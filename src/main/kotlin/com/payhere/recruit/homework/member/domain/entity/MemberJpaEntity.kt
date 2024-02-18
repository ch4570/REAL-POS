package com.payhere.recruit.homework.member.domain.entity

import com.payhere.recruit.homework.common.entity.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "MEMBER")
class MemberJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    val memberId: Long? = null,

    @Column(name = "PHONE_NUMBER")
    val phoneNumber: String,

    @Column(name = "MEMBER_PASSWORD")
    val password: String
) : BaseTimeEntity()