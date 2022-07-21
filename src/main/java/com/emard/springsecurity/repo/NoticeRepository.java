package com.emard.springsecurity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emard.springsecurity.domain.Notice;


@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {
	
	@Query(value = "from Notice n where CURRENT_DATE BETWEEN noticBegDt AND noticEndDt")
	List<Notice> findAllActiveNotices();
}
