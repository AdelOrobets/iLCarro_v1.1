package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class to represent the user
 * Uses Lombok to autogenerate getters, setters, constructors and other methods
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLombok {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
