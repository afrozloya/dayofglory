package superai.dayofglory.dto;

import org.springframework.web.multipart.MultipartFile;

public class TaskRequestDTO {
    //currently there is only one field but it will have other fields later
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
