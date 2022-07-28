package frontend.Erasmus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NatjecajResponse {
    private Long id;
    private String natjecajName;
    private String url;
    private String description;
    private String userName;
    private String mobilnostName;

}
