package pfe.service.authentication.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/*
 ici
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUserResponse {
    private String email;
    private String password;
    List<File> files;
}
