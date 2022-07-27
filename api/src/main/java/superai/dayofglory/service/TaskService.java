package superai.dayofglory.service;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import superai.dayofglory.dto.TaskRequestDTO;
import superai.dayofglory.exception.FileStorageException;
import superai.dayofglory.exception.TaskFileNotFoundException;
import superai.dayofglory.misc.TaskStatus;
import superai.dayofglory.models.Task;
import superai.dayofglory.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import superai.dayofglory.repositories.TaskRepository;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final Path fileStorageLocation;

    @Autowired
    TaskRepository taskRepository;

    String uploadFolder;

    @Autowired
    public TaskService(FileStorageProperties fileStorageProperties) {
        uploadFolder = fileStorageProperties.getUploadDir();
        this.fileStorageLocation = Paths.get(uploadFolder)
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public List<Task> getAllTasks() {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return taskRepository.findAll(sortByCreatedAtDesc);
    }

    public Task createTask(MultipartFile file) {
        String fileName = storeFile(file);
        Task task = new Task();
        final String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        task.setInput(baseUrl + "/"+ uploadFolder +"/"+ fileName);
        task.setStatus(TaskStatus.ACTIVE.name());
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> updateTask(String id, Task task) {
        Task taskData = taskRepository.findById(id).orElse(null);
        if(taskData!=null){
            taskData.setAnotations(task.getAnotations());
            taskData.setStatus(TaskStatus.READY.name());
            taskData.setVersion(taskData.getVersion()+1);
            taskData = taskRepository.save(taskData);
        }
        return Optional.of(taskData);
    }

    public boolean deleteTask(String id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.deleteById(id);
                    return true;
                }).orElse(false);
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
