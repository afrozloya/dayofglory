package superai.dayofglory.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import superai.dayofglory.models.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

}