package com.hitdavid.app.security;

import com.hitdavid.app.constant.STATUS;
import com.hitdavid.app.dao.model.Resource;
import com.hitdavid.app.dao.model.Role;
import com.hitdavid.app.dao.model.RoleResource;
import com.hitdavid.app.dao.repository.ResourceRepository;
import com.hitdavid.app.dao.repository.RoleRepository;
import com.hitdavid.app.dao.repository.RoleResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Component
public class HttpSecurityResource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected ResourceRepository resourceRepository;

    @Autowired
    protected RoleResourceRepository roleResourceRepository;

    private AntPathMatcher pathMatcher=new AntPathMatcher();

    //访问某个资源object需要什么角色
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        Collection<ConfigAttribute> attributes=new HashSet<>();
        FilterInvocation invocation=(FilterInvocation)object;//对于http资源来说，object是FilterInvocation

        String requestUrl = invocation.getRequestUrl();

        List<Resource> resources = resourceRepository.findAll();

        if(CollectionUtils.isEmpty(resources)){
            return null;
        }

        resources.stream().filter(resource -> resource.getStatus() < STATUS.BLOCKED_STATUS_GROUP.getValue()).forEach(resource -> {
            if(pathMatcher.match(resource.getUrl(), requestUrl)) {

                List<RoleResource> rr = roleResourceRepository.findAllByResourceId(resource.getId());
                rr.stream().forEach( role_resource -> {
                    Role role = roleRepository.findById(role_resource.getRoleId());
                    attributes.add(new SecurityConfig(role.getName()));
                });
            }
        });

        return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        Collection<ConfigAttribute> attributes=new HashSet<>();
        List<Resource> resources = resourceRepository.findAll();

        if(CollectionUtils.isEmpty(resources)){
            return null;
        }

        resources.stream().filter(resource -> resource.getStatus() < STATUS.BLOCKED_STATUS_GROUP.getValue()).forEach(resource -> {

            List<RoleResource> rr = roleResourceRepository.findAllByResourceId(resource.getId());
            rr.stream().forEach( role_resource -> {
                Role role = roleRepository.findById(role_resource.getRoleId());
                attributes.add(new SecurityConfig(role.getName()));
            });

        });

        return attributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
