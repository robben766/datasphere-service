/*
 * Copyright 2019, Huahuidata, Inc.
 * DataSphere is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 * http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 */

package com.datasphere.server.common.saml;

import org.springframework.security.saml.SAMLCredential;

import com.datasphere.server.user.User;

public class SAMLChinaMobileUserMapper extends SAMLUserMapper{

  @Override
  public User createUser(SAMLCredential samlCredential) {
    User mobileUser = new User();
    if(samlCredential != null){
    		mobileUser.setTel(getAttributeValue(samlCredential, "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/phonenumber"));
    		mobileUser.setFullName(getAttributeValue(samlCredential, "http://schemas.microsoft.com/2012/01/devicecontext/claims/displayname"));
    		mobileUser.setEmail(getAttributeValue(samlCredential, "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress"));
    }
    return mobileUser;
  }
}
