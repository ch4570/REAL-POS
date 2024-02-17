package com.payhere.recruit.homework.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class MemberJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    val memberId: Long? = null,

    @Column(name = "PHONE_NUMBER")
    val phoneNumber: String,

    @Column(name = "MEMBER_PASSWORD")
    val password: String
) : BaseTimeEntity()