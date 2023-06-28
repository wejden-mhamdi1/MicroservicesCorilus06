package pfe.service.authentication.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pfe.service.authentication.auth.File;

import java.util.List;


@FeignClient(name = "file", url = "${application.config.traitments-url}")
public interface FileClient {
    @GetMapping("/user/{user-id}")
    List<File> findAllFilesByUser(@PathVariable("user-id") Integer id);
}
