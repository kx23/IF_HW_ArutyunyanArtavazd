package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {
    private int id;
    private String name;
    private String type;
    private String dimension;
    private List<String> residents;
    private String url;
}