package frontend.Erasmus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NatjecajRequest {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfMobilnosts;
    private Date datumOd;
    private Date datumDo;
    private String ustanova;
}