package com.ecom.repository;


import com.ecom.entity.EcomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<EcomUser, Long> {
        Optional<EcomUser> findByUsername(String username);

}
