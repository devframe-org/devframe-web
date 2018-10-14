package org.devframe.web.common.vo;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class AuthUserDetails implements UserDetails {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private String userId;
	private String passwd;
	private String passwdModDt;
	private String passwdTemp;
	private String passwdTempDt;
	private String nickname;
	private String email;
	private int loginCnt;
	private String loginDt;
	private String regId;
	private String regDt;
	private String modId;
	private String modDt;

	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;

	private Set<GrantedAuthority> authorities;

	@Override
	public String getPassword() {
		return passwd;
	}

	@Override
	public String getUsername() {
		return userId;
	}

}