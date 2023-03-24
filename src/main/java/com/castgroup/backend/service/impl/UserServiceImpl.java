package com.castgroup.backend.service.impl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.castgroup.backend.dto.UserDto;
import com.castgroup.backend.model.UserModel;
import com.castgroup.backend.repository.UserRepository;
import com.castgroup.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	private static final Map<Long, LocalDateTime> SESSIONS = new ConcurrentHashMap<>();

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto login(UserDto userDto) {
		logger.info("loggin2 passei ");
		// Armazena o usuário atual e o tempo de expiração da sessão
		Optional<UserModel> opUserModel = userRepository.findByName(userDto.getName());
		if (opUserModel.isPresent()) {
			SESSIONS.put(opUserModel.get().getId(), LocalDateTime.now());
			return UserDto.of(opUserModel.get());
		}

		UserModel user = userRepository.save(userDto.toEntity());
		SESSIONS.put(user.getId(), LocalDateTime.now());
		return UserDto.of(user);
	}

	@Override
	public ResponseEntity<String> logoff() {
		SESSIONS.clear();

		return ResponseEntity.ok("Logout bem sucedido!");
	}

	@Scheduled(fixedDelay = 60_000)
	public void cleanupSessions() {
		SESSIONS.entrySet().removeIf(entry -> entry.getValue().plusSeconds(60).isBefore(LocalDateTime.now()));
	}

}
