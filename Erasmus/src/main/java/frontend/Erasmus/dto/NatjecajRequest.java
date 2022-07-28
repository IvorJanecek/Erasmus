package frontend.Erasmus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NatjecajRequest {
    private Long natjecajId;
    private String mobilnostName;
    private String natjecajName;
    private String url;
    private String description;
}