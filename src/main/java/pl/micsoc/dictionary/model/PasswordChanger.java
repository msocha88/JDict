package pl.micsoc.dictionary.model;

import lombok.Data;

@Data
public class PasswordChanger {

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;
}
