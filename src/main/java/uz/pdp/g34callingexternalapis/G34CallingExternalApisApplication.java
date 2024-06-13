package uz.pdp.g34callingexternalapis;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import uz.pdp.g34callingexternalapis.domain.Post;
import uz.pdp.g34callingexternalapis.repo.PostRepository;

@SpringBootApplication
public class G34CallingExternalApisApplication {

    public static void main(String[] args) {
        SpringApplication.run(G34CallingExternalApisApplication.class, args);
    }


    public CommandLineRunner commandLineRunner(PostRepository postRepository) {
        return args -> {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Post[]> response = restTemplate.exchange(
                    "https://jsonplaceholder.typicode.com/posts",
                    HttpMethod.GET,
                    null,
                    Post[].class
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                Post[] posts = response.getBody();
                postRepository.saveAll(List.of(posts));
            }
        };
    }

}
