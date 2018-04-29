package com.balionis.spring5.restful.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;

@Component
class DomainAwarePermissionEvaluator implements PermissionEvaluator {

    private static final Log logger = LogFactory.getLog(DomainAwarePermissionEvaluator.class);

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        logger.debug("hasPermission: permission=" + permission
                + ", user=" + authentication.getName()
                + ", targetDomainObject=" + targetDomainObject);

        // for @RequestMapping("/echo") with @PreAuthorize("hasPermission(#message, 'place-message')")
        if ("place-message".equals(permission)) {
            if ("SHUTDOWN".equals(targetDomainObject)) {
                return hasRole("ROLE_ADMIN", authentication);
            }
        }

        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return hasPermission(authentication, new DomainObjectReference(targetId, targetType), permission);
    }

    private boolean hasRole(String role, Authentication auth) {

        if (auth == null || auth.getPrincipal() == null) {
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        if (CollectionUtils.isEmpty(authorities)) {
            return false;
        }

        return authorities.stream().filter(ga -> role.equals(ga.getAuthority())).findAny().isPresent();
    }

    static class DomainObjectReference {
        private final Serializable targetId;
        private final String targetType;

        public DomainObjectReference(Serializable targetId, String targetType) {
            this.targetId = targetId;
            this.targetType = targetType;
        }
    }
}