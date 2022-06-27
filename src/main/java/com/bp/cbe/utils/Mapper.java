package com.bp.cbe.utils;

import org.modelmapper.ModelMapper;

/**
 * Utility to map entities and data
 *
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public class Mapper {
  private Mapper() {
    throw new IllegalStateException("Utility class");
  }

  public static ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
