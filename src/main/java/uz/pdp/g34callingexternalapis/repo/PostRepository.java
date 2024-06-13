package uz.pdp.g34callingexternalapis.repo;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.g34callingexternalapis.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    Optional<Post> findByGenId(Long id);
}
