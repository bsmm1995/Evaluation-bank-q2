package com.bp.cbe.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "metrics")
public class MetricsEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN")
  @Column(name = "id_metrics", nullable = false, unique = true)
  private Long id;

  @Column(name = "coverage", nullable = false)
  private Double coverage;

  @Column(name = "bugs", nullable = false)
  private Integer bugs;

  @Column(name = "vulnerabilities", nullable = false)
  private Integer vulnerabilities;

  @Column(name = "hotspot", nullable = false)
  private Integer hotspot;

  @Column(name = "code_smells", nullable = false)
  private Integer codeSmells;

  @Column(name = "status", columnDefinition = "INT NOT NULL DEFAULT 1")
  private Integer status = 1;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_repository")
  private RepositoryEntity repository;
}
