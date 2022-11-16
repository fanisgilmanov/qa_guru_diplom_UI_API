package web_api.models;

import lombok.Data;

@Data
public class RequestModels {
    private String back;
    private String email;
    private String password;
    private String submitLogin;
    private String idGender;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String psgdpr;
    private String submitCreate;
}
