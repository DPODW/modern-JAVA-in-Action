import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class TestAppleDto {
    private String appleName;

    private Color color;

    private int weight;

    public TestAppleDto(String appleName, Color color, int weight) {
        this.appleName = appleName;
        this.color = color;
        this.weight = weight;
    }
}
