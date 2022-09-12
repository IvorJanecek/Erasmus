package frontend.Erasmus.controller;

import frontend.Erasmus.dto.*;
import frontend.Erasmus.exception.ErasmusException;
import frontend.Erasmus.model.PasswordModel;
import frontend.Erasmus.model.User;
import frontend.Erasmus.repository.UserRepository;
import frontend.Erasmus.service.AuthService;
import frontend.Erasmus.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User registration successful", OK);

    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully ", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    //dohvati sve usere

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    //dohvati usera po id-u
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Long userId)
            throws ErasmusException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErasmusException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }


    //obrisi usera
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long userId)
            throws ErasmusException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErasmusException("User not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //uredi usera
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long userId,
                                                   @Valid @RequestBody User userDetails) throws ErasmusException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ErasmusException("User not found for this id :: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setRole(userDetails.getRole());
        final User promjenjenUser = userRepository.save(user);
        return ResponseEntity.ok(promjenjenUser);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
        User user = authService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if(user!=null) {
            String token = UUID.randomUUID().toString();
            authService.createPasswordResetTokenForUser(user,token);
            url = passwordResetTokenMail(user,applicationUrl(request), token);
        }
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,
                               @RequestBody PasswordModel passwordModel) {
        String result = authService.validatePasswordResetToken(token);
        if(!result.equalsIgnoreCase("valid")) {
            return "Invalid Token";
        }
        Optional<User> user = authService.getUserByPasswordResetToken(token);
        if(( user).isPresent()) {
            authService.changePassword(user.get(), passwordModel.getNewPassword());
            return "Password Reset Successfully";
        } else {
            return "Invalid Token";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel){
        User user = authService.findUserByEmail(passwordModel.getEmail());
        if(!authService.checkIfValidOldPassword(user,passwordModel.getOldPassword())) {
            return "Invalid Old Password";
        }
        //Save New Password
        authService.changePassword(user,passwordModel.getNewPassword());
        return "Password Changed Successfully";
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url =
                applicationUrl
                        + "/savePassword?token="
                        + token;

        //sendVerificationEmail()
        log.info("Click the link to Reset your Password: {}",
                url);
        return url;
    }

    private String applicationUrl(HttpServletRequest request) {
        return "https://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }


}
