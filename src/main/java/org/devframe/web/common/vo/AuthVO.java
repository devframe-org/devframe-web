package org.devframe.web.common.vo;

import lombok.Data;

@Data
public class AuthVO {

	private String userId;
	private String passwd;
	private String passwdModDt;
	private String passwdTemp;
	private String passwdTempDt;
	private String nickname;
	private String email;
	private int loginCnt;
	private String loginDt;

	private String grpId;
	private String grpNm;

	private String roleId;
	private String roleNm;

	private double rscSn;
	private String url;
	private int ordNo;

	private String regId;
	private String regDt;
	private String modId;
	private String modDt;

}
