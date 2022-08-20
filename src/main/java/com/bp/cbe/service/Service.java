package com.bp.cbe.service;

import java.util.List;

/**
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 19/08/2022
 */
public interface Service<T, I> {
    T getById(I id);

    List<T> getAll();

    T create(T data);

    T update(I id, T data);

    void deleteById(I id);
}
