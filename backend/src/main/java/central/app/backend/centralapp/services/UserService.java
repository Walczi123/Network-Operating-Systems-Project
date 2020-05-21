package central.app.backend.centralapp.services;

import central.app.backend.centralapp.exceptions.IncorrectPasswordException;
import central.app.backend.centralapp.exceptions.UserNotExistException;
import central.app.backend.centralapp.forms.LoginForm;
import central.app.backend.centralapp.models.User;
import central.app.backend.centralapp.repositories.UserRepository;
import central.app.backend.centralapp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService{
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(LoginForm loginForm) {
        UserDetails userDetails = securityUserDetailsService.loadUserByUsername(loginForm.getUsername());
        if (!userDetails.getPassword().equals(loginForm.getPassword()))
            throw new IncorrectPasswordException(userDetails.getPassword() + " " + loginForm.getPassword());
        return jwtUtil.generateToken(userDetails);
    }

    public String getUsername(int id){
        User u =  userRepository.findById(id);
        if (u == null)
            return "No user in database";
        return u.getFirstName() + " " + u.getLastName();
    }

    public User getUserByToken(String token){
        String username = jwtUtil.extractUsername(token);
        return userRepository.findByLogin(username);
    }

    public String getRoleByToken(String token){
        User currentUser = getUserByToken(token);
        return currentUser.getRole();
    }

}