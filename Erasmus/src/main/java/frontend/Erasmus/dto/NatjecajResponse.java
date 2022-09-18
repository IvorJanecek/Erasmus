package frontend.Erasmus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NatjecajResponse {
    private Long id;
    private String name;
    private String url;
    private String description;
    private Date datumOd;
    private Date datumDo;
    private String Ustanova;

}
