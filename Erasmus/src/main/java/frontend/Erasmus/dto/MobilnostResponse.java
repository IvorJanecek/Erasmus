package frontend.Erasmus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobilnostResponse {
    private Long id;
    private String mobilnostName;
    private String url;
    private String description;
    private String userName;
    private String natjecajName;

}