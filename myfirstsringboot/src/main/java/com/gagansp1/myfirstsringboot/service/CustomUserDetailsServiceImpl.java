package com.gagansp1.myfirstsringboot.service;

import com.gagansp1.myfirstsringboot.entity.User;
import com.gagansp1.myfirstsringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {

     // his means you don't have to manually create an instance of User Repository or manage its lifecycle.
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUserName(username);
        if( user!=null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray( new String[0]))
                    .build();

            return userDetails;
        }

        throw new UsernameNotFoundException("User not found with username "+username);

    }
}
