package com.intersect.poc.springcloudsub.services;


import com.intersect.poc.springcloudsub.model.User;
import com.intersect.poc.springcloudsub.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User getUserById(Long userId) {
        return userRepository.getUserByUserId(userId);
    }
}
