package com.wj.resttasks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wj.resttasks.models.Task;
import com.wj.resttasks.models.User;
import com.wj.resttasks.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceDetailsImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceDetailsImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceDetailsImpl userServiceDetailsImpl;

    /**
     * Method under test: {@link UserServiceDetailsImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setTasks(new HashSet<>());
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsername((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = userServiceDetailsImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByUsername((String) any());
    }



    /**
     * Method under test: {@link UserServiceDetailsImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername5() throws UsernameNotFoundException {
        when(userRepository.findByUsername((String) any())).thenReturn(null);
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn("Role");
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRole((String) any());
        doNothing().when(user).setTasks((Set<Task>) any());
        doNothing().when(user).setUsername((String) any());
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setTasks(new HashSet<>());
        user.setUsername("janedoe");
        assertThrows(UsernameNotFoundException.class, () -> userServiceDetailsImpl.loadUserByUsername(""));
        verify(userRepository).findByUsername((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRole((String) any());
        verify(user).setTasks((Set<Task>) any());
        verify(user).setUsername((String) any());
    }

}

