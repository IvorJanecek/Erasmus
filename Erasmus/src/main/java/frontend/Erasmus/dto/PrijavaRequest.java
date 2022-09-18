package frontend.Erasmus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrijavaRequest {
    private Long prijavaId;
    private Long natjecajId;
    private String prijavaName;
    private String opis;
    private String user;
    private String natjecajName;
}
