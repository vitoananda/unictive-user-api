package com.unictive.unictiveUserApplication.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "mst_user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    val idUser: Int? = null,

    @Column(name = "name", columnDefinition = "varchar(100)")
    var name: String? = null,

    @field:Column(name = "email", columnDefinition = "varchar")
    val email : String? = null,

    @field:Column(name = "password", columnDefinition = "varchar")
    val password: String? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var hobbies: List<HobbyEntity> = mutableListOf(),

    @CreationTimestamp
    @Column(name = "dt_added", updatable = false, columnDefinition = "timestamp")
    val dtAdded : LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "dt_updated", columnDefinition = "timestamp")
    val dtUpdated : LocalDateTime? = null,
)
