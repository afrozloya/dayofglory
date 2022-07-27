package superai.dayofglory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskFileNotFoundException extends RuntimeException {
    public TaskFileNotFoundException(String message) {
        super(message);
    }

    public TaskFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
