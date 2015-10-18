package mum.bigdata.car.recommender.service.impl;

import org.springframework.stereotype.Service;

import mum.bigdata.car.recommender.model.User;
import mum.bigdata.car.recommender.repository.UserRepository;
import mum.bigdata.car.recommender.repository.impl.UserRepositoryImpl;
import mum.bigdata.car.recommender.service.UserService;

@Service
public class UserServiceImpl implements UserService<User> {

	private UserRepository<User> userRepository;

	public UserServiceImpl() {
		userRepository = new UserRepositoryImpl();
	}

	@Override
	public User saveUser(User t) {
		return userRepository.save(t);
	}

	@Override
	public User getUser(String id) {
		return userRepository.get(id);
	}

}
