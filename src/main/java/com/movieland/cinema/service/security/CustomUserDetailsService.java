//package com.movieland.cinema.service;

import com.movieland.cinema.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Transactional(readOnly=true)
//    @Override
//    public UserDetails loadUserByUsername(final String email)
//            throws UsernameNotFoundException {
//
//        com.movieland.cinema.domain.User user = userRepository.findByEmail(email);
//        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
//
//        return buildUserForAuthentication(user, authorities);
//
//    }
//}