package com.intersect.poc.springcloudsub.repo;

import com.intersect.poc.springcloudsub.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    User getUserByUserId(Long id);
}
