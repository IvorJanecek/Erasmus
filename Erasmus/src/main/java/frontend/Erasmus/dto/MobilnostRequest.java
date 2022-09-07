package frontend.Erasmus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MobilnostRequest {
    private Long mobilnostId;
    private String mobilnostName;
    private String description;
    private Integer numberOfNatjecajs;
    private String natjecajName;
    private String user;
}
