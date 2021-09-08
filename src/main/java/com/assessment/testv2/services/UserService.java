package com.assessment.testv2.services;

import com.assessment.testv2.model.ApplicationUser;
import com.assessment.testv2.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static String USR_NOT_FOUND = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USR_NOT_FOUND, email)));
    }

    public String signUpUser(ApplicationUser applicationUser){
        boolean userExists = userRepository.findByEmail(applicationUser.getEmail()).isPresent();

        if (userExists){
            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);
        userRepository.save(applicationUser);

//        TODO: Send confirmation token;
        return "It works";
    }
}
