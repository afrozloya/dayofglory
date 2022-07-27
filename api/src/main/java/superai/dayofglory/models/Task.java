package superai.dayofglory.models;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import superai.dayofglory.models.PicAnnotation;

@Document(collection="tasks")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Task {
    @Id
    private String id;

    private String status;

    private String input;

    private PicAnnotation[] anotations;

    private int version = 0;

    private Date createdAt = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public PicAnnotation[] getAnotations() {
        return anotations;
    }

    public void setAnotations(PicAnnotation[] anotations) {
        this.anotations = anotations;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}