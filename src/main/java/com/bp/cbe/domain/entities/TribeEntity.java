package com.bp.cbe.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tribe")
public class TribeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN")
  @Column(name = "id_tribe", nullable = false, unique = true)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "status", columnDefinition = "INT NOT NULL DEFAULT 1")
  private Integer status = 1;

  @ManyToOne
  @JoinColumn(name = "ID_ORGANIZATION", nullable = false, updatable = false)
  private OrganizationEntity organization;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tribe")
  private List<RepositoryEntity> repositories;
}
