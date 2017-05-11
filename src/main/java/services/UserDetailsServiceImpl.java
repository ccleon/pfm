package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import daos.AuthorizationDao;
import daos.UserDao;
import entities.Role;
import entities.User;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Override
    public UserDetails loadUserByUsername(final String usernameOrTokenValue) throws UsernameNotFoundException {
        User user = userDao.findByTokenValue(usernameOrTokenValue);
        if (user != null) {
            List<Role> roleList = authorizationDao.findRoleByUser(user);
            return this.userBuilder(String.valueOf(user.getUsername()), new BCryptPasswordEncoder().encode(""), roleList);
        } else {
            try {
                user = userDao.findByUsername(usernameOrTokenValue);
            } catch (NumberFormatException nfe) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
            if (user != null) {
                return this.userBuilder(String.valueOf(user.getUsername()), user.getPassword(), Arrays.asList(Role.AUTHENTICATED));
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
        }
    }

    private org.springframework.security.core.userdetails.User userBuilder(String username, String password, List<Role> roles) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.roleName()));
        }
        return new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }
}