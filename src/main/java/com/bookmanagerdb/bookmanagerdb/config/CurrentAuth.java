package com.bookmanagerdb.bookmanagerdb.config;

import com.bookmanagerdb.bookmanagerdb.controller.constants.RoleConstants;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@RequestScope
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentAuth {
    private User user;
    //存入用户使用的Cookie

    public User getUser() {
        if (user == null) {
            var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            user = (User) request.getAttribute("user");
        }
        return user;
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public boolean isAdmin() {
        return RoleConstants.ROLE_ADMIN.equals(getUser().getType());
    }

}
