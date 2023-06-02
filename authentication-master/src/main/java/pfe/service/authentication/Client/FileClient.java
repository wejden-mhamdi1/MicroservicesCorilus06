package pfe.service.authentication.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pfe.service.authentication.auth.File;

import java.util.List;
/*
ici
*/
@FeignClient(name = "file-service", url = "${application.config.files-url}")
public interface FileClient {
    @GetMapping("/auth/{id}")
    List<File> fundallfileByAuth(@PathVariable("id") Integer id);
}
