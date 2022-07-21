package com.emard.springsecurity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emard.springsecurity.domain.Notice;
import com.emard.springsecurity.repo.NoticeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class NoticesController {
	
	private final NoticeRepository noticeRepository;
	
	@GetMapping("/notices")
	public List<Notice> getNotices() {
		List<Notice> notices = noticeRepository.findAllActiveNotices();
		if (notices != null ) {
			return notices;
		}else {
			return null;
		}
	}

}