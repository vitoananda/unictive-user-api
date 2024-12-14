package com.unictive.unictiveUserApplication.domain.entity
import jakarta.persistence.*

@Entity
@Table(name = "mst_hobby")
data class HobbyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    val idHobby: Int? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserEntity? = null,

    @JoinColumn(name = "name")
    val name: String? = null
)
