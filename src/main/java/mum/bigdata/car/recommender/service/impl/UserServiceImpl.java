package mum.bigdata.car.recommender.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.bigdata.car.recommender.model.User;
import mum.bigdata.car.recommender.repository.UserRepository;
import mum.bigdata.car.recommender.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		
		User existedUser = userRepository.get(user.getId());
		if (existedUser == null) {
			userRepository.save(user);
		}

		return existedUser;
	}

	@Override
	public User getUser(String id) {
		return userRepository.get(id);
	}

}
