package pfe.service.authentication.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pfe.service.authentication.Client.FileClient;
import pfe.service.authentication.Email.EmailService;
import pfe.service.authentication.Email.MailRequest;
import pfe.service.authentication.config.JwtService;
import pfe.service.authentication.token.Token;
import pfe.service.authentication.token.TokenRepository;
import pfe.service.authentication.token.TokenType;
import pfe.service.authentication.user.*;

import java.net.SocketException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private FileClient client;

    public AuthenticationResponse register(RegisterRequest request) {
        String ipAdress = "";
        try {
            ipAdress = LocationService.MyIpAdress();
        } catch (SocketException e1) {
            e1.printStackTrace();
        }

        // Activation Token
        String activationToken = UUID.randomUUID().toString();
        Address address = LocationService.CurrentLocation(ipAdress);
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .address(address.getCity())
                // CurrentDate
                        .createdAt(Utils.getCurrentDate())
                .activationToken(activationToken)
                .accountStatus("NOT_VERIFIED")
         .build();


      /*  MailRequest mailRequest = new MailRequest(user.getFirstname().toUpperCase() + " " + user.getLastname(),
                user.getEmail(), "Verification de votre compte", "Veuillez verifier votre compte",
                "Activer votre compte", "http://localhost:4200/activateAccount/" + activationToken);
        //mailRequest.setTLS(true);
        emailService.sendEmail(mailRequest);*/



        var savedUser= userRepository.save(user);


        var jwtToken= jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public void logout(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user != null) {
            revokeAllUserTokens(user);
        }
    }
    private void revokeAllUserTokens(User user){
        var validUserToken= tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserToken.isEmpty()){
            return;
        }
        validUserToken.forEach(t ->{
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }




    //
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user= userRepository.findByEmail(request.getEmail()).orElseThrow();
        user.setActivityScore(user.getActivityScore() + 1);
        userRepository.save(user);
        var jwtToken= jwtService.generateToken(user);
        revokeAllUserTokens(user);

        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
    private void saveUserToken(User user, String jwtToken) {
        var token= Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
   public Optional<User> findUserByEmail(String email) {

        return userRepository.findByEmail(email);

    }
    public Boolean activateAccount(String activationToken) {
        User user;
        user = userRepository.findByActivationToken(activationToken).orElse(null);
        if (!(user == null)) {
            if (Utils.compareDateByMinutes(user.getCreatedAt(), 60)) {
                user.setActivationToken(null);
                user.setAccountStatus("VERIFIED");
                userRepository.save(user);
                return true;
            }

            else {
                String newActivationToken = UUID.randomUUID().toString();
                user.setActivationToken(newActivationToken);
             /* MailRequest mailRequest = new MailRequest(user.getFirstname().toUpperCase() + " " + user.getLastname(),
                        user.getEmail(), "Verification de votre compte", "Veuillez verifier votre compte",
                        "Activer votre compte", "http://localhost:4200/login/activateAccount/" + newActivationToken);
                emailService.sendEmail(mailRequest);*/
                user.setCreatedAt(Utils.getCurrentDate());

            }
        }
        return false;
    }

    public List<User> getAllUsers() {

       // log.info("Fetching all users");
        return userRepository.findAll();
    }

/*
ici
*/
    public FullAuthResponse findSchoolsWithfiles(Integer authId) {
        var user = userRepository.findById(authId)

                .orElse(
                        User.builder()
                                .firstname("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        var files = client.fundallfileByAuth(authId);

        return FullAuthResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .files(files)
                .build();
    }
}
