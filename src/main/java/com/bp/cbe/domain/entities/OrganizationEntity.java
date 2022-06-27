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
@Table(name = "organization")
public class OrganizationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN")
  @Column(name = "id_organization", nullable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "status", columnDefinition = "INT NOT NULL DEFAULT 1")
  private Integer status = 1;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
  private List<TribeEntity> tribes;
}
