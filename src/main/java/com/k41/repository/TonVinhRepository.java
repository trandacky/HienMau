package com.k41.repository;

import com.k41.entity.NguoiDung;
import com.k41.entity.TonVinh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TonVinhRepository extends JpaRepository<TonVinh, Long> {
    @Query(value = "SELECT t from TonVinh t WHERE year(t.ngayTonVinh) = :year ")
    Page<TonVinh> findAllByYear(int year, Pageable paging);
}
