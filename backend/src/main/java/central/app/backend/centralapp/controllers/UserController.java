package central.app.backend.centralapp.controllers;

import central.app.backend.centralapp.errors.ErrorResponse;
import central.app.backend.centralapp.exceptions.IncorrectPasswordException;
import central.app.backend.centralapp.exceptions.UserNotExistException;
import central.app.backend.centralapp.forms.LoginForm;
import central.app.backend.centralapp.forms.LoginResponse;
import central.app.backend.centralapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginForm loginForm) {
        return ResponseEntity.ok().body(new LoginResponse(userService.login(loginForm)));
    }

    @ExceptionHandler({UserNotExistException.class})
    public ResponseEntity<ErrorResponse> notFound(UserNotExistException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("The user was not found", HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IncorrectPasswordException.class})
    public ResponseEntity<ErrorResponse> notFound(IncorrectPasswordException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Your password is invalid", HttpStatus.UNAUTHORIZED.value(), ex.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ErrorResponse> notFound(UsernameNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Username not found.", HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}