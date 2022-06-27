package com.bp.cbe.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "repository")
public class RepositoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN")
  @Column(name = "id_repository", nullable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "state", nullable = false, length = 1)
  private String state;

  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;

  @Column(name = "status", columnDefinition = "INT NOT NULL DEFAULT 1")
  private Integer status = 1;

  @ManyToOne
  @JoinColumn(name = "ID_TRIBE", nullable = false, updatable = false)
  private TribeEntity tribe;
}
