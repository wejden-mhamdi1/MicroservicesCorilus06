package pfe.service.authentication.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfe.service.authentication.config.JwtService;
import pfe.service.authentication.user.User;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final  AuthenticationService service;
    private final JwtService jwtService;
    @GetMapping("/auth/logout")
    public ResponseEntity<String> logout(@RequestParam("userEmail") String userEmail) {
        service.logout(userEmail);

        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
    @GetMapping("/getall")

    public List<User> getAll() {
        return service.getAllUsers();
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));

    }
    @GetMapping("/user-role")
    public String getUserRole(@RequestHeader("Authorization") String token) {


        String userRole = jwtService.extractUserRole(token);
        jwtService.printTokenClaims(token);
        return userRole;

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));

    }
    /*
    ici

    @GetMapping("/with-file/{auth-id}")
    public ResponseEntity<FullAuthResponse> findAllSchools(
            @PathVariable("auth-id") Integer authId
    ) {
        return ResponseEntity.ok(service.findSchoolsWithfiles(authId));
    }
*/
    @GetMapping("/with-files/{user-id}")
    public ResponseEntity<FullUserResponse> findAllUsers(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findUsersWithFiles(userId));
    }
}
