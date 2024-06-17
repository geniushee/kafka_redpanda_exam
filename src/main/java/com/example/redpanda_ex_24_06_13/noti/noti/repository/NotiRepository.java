package com.example.redpanda_ex_24_06_13.noti.noti.repository;

import com.example.redpanda_ex_24_06_13.noti.noti.entity.Noti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotiRepository extends JpaRepository<Noti, Long> {
}
