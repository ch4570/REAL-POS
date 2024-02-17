package com.payhere.recruit.homework.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class JwtTokenJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID")
    val tokenId: Long? = null,

    @Column(name = "MEMBER_ID")
    val memberId: Long,

    @Column(name = "ACCESS_TOKEN", columnDefinition = "BLOB")
    val accessToken: String
) : BaseTimeEntity()