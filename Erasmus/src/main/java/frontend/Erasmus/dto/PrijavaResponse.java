package frontend.Erasmus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrijavaResponse {
    private String username;
    private Long natjecajId;
    private String prijavaName;
}
