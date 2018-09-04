package com.easyrun.commons.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDto {
	private String id;
	private String username;
	private String password;
	private String token;
	private String audience;
}
