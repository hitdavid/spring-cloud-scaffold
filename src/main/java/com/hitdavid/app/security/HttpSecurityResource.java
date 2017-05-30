package com.hitdavid.app.security;

import com.hitdavid.app.dao.repository.ResourceRepository;
import com.hitdavid.app.dao.repository.RoleRepository;
import com.hitdavid.app.dao.repository.RoleResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * 定义受保护的http资源
 * 默认情况下，需要在配置文件中定义url与所需的权限，不能从数据库加载
 * @author Jonsy
 */
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

        return null;

//        Collection<ConfigAttribute> attributes=new HashSet<>();
//        FilterInvocation invocation=(FilterInvocation)object;//对于http资源来说，object是FilterInvocation
//
//        String requestUrl=invocation.getRequestUrl();
//
//        List<RoleResource> rr = roleResourceRepository.findAllByRoleId(role.getId());
//        List<Resource> resources resourceRepository.findAllByIdIn();
//
//            if(CollectionUtils.isEmpty(resources)){
//                return;
//            }
//            resources.stream().filter(resource -> !resource.isDisabled()).forEach(resource -> {
//                if(pathMatcher.match(resource.getUrl(),requestUrl)) {
//                    attributes.add(new SecurityConfig(role.getName()));
//                    return;
//                }
//            });
//
//        });
//
//        return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        List<Role> allHttpResources=roleRepository.list();
//        Collection<ConfigAttribute> attributes=new HashSet<>();
//        allHttpResources.stream().forEach(role -> {
//            attributes.add(new SecurityConfig(role.getName()));
//        });
//        return attributes;
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
