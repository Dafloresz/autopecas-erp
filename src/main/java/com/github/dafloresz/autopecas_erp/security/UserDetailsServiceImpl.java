package com.github.dafloresz.autopecas_erp.security;

import com.github.dafloresz.autopecas_erp.user.User;
import com.github.dafloresz.autopecas_erp.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user;
        
        if(username == null || username.isEmpty()) {
            throw new IllegalArgumentException("The username cannot be null or empty");
        }
        
        if(username.contains("@")){
            user = userRepository.findByEmail(username);

        } else if (username.matches("\\d+") && username.length() == 11) {
            user = userRepository.findByCpf(username);

        } else  {
            user = userRepository.findByUsername(username);
        }

        return user.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("This credentials are not valid"));

    }
}
