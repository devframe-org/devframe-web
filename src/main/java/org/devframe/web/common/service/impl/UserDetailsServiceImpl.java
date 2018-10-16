package org.devframe.web.common.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.devframe.web.common.mapper.AuthMapper;
import org.devframe.web.common.vo.AuthUserDetails;
import org.devframe.web.common.vo.AuthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Service("userDetailsService2")
public class UserDetailsServiceImpl implements UserDetailsService {

	protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Autowired
	private AuthMapper authMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthVO authVO = authMapper.selectUser(username);

		if(authVO == null) {
			log.debug("Query returned no results for user '" + username + "'");

			throw new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[] { username }, "Username {0} not found"));
		}

		List<AuthVO> userRoleList = authMapper.selectUserRoleList(username);

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		for(AuthVO _authVO : userRoleList) {
			authorities.add(new SimpleGrantedAuthority(_authVO.getRoleId()));
		}

		AuthUserDetails authUserDetails = new AuthUserDetails();
		authUserDetails.setUserId(authVO.getUserId());
		authUserDetails.setPasswd(authVO.getPasswd());
		authUserDetails.setPasswdModDt(authVO.getPasswdModDt());
		authUserDetails.setPasswdTemp(authVO.getPasswdTemp());
		authUserDetails.setPasswdTempDt(authVO.getPasswdTempDt());
		authUserDetails.setNickname(authVO.getNickname());
		authUserDetails.setEmail(authVO.getEmail());
		authUserDetails.setLoginCnt(authVO.getLoginCnt());
		authUserDetails.setLoginDt(authVO.getLoginDt());
		authUserDetails.setRegId(authVO.getRegId());
		authUserDetails.setRegDt(authVO.getRegDt());
		authUserDetails.setModId(authVO.getModId());
		authUserDetails.setModDt(authVO.getModDt());
		authUserDetails.setAuthorities(authorities);

		return authUserDetails;
	}

}