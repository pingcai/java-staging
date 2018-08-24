package me.pingcai.dao.spring;

import me.pingcai.dao.enums.DbEnum;
import me.pingcai.dao.handler.DbEnumTypeHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * @author huangpingcai
 * @since 2018/8/25 01:11
 */
public class SqlSessionFactoryPostProcessor implements BeanPostProcessor {

    private String enumPackages;

    private String typeHandlerPackages;

    private final static String COMMA = ",";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SqlSessionFactory) {
            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) bean;
            registerEnumTypeHandler(sqlSessionFactory);
        }
        return bean;
    }

    /**
     * 注册枚举类型的自定义TypeHandler
     * @param sqlSessionFactory
     */
    private void registerEnumTypeHandler(SqlSessionFactory sqlSessionFactory) {
        if (StringUtils.isBlank(enumPackages)) {
            return;
        }
        for (String enumPackage : enumPackages.split(COMMA)) {
            Set<Class<? extends Class<Enum>>> clazzSet = scanSpecifiedClass(Enum.class, enumPackage);
            for (Class<? extends Class<Enum>> clazz : clazzSet) {
                if (    isValidEnum(clazz)
                        && DbEnum.class.isAssignableFrom(clazz)
                        && !DbEnum.class.equals(clazz)
                        && !sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().hasTypeHandler(DbEnumTypeHandler.class)
                ) {
                        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().register(clazz, new DbEnumTypeHandler(clazz));
                }
            }
        }
    }

    /**
     * 返回包扫描结果
     * @param clazz
     * @param _package
     * @param <T>
     * @return
     */
    private <T> Set<Class<? extends Class<T>>> scanSpecifiedClass(Class<T> clazz, String _package) {
        ResolverUtil<Class<T>> resolverUtil = new ResolverUtil<>();
        resolverUtil.find(new ResolverUtil.IsA(clazz), _package);
        Set<Class<? extends Class<T>>> clazzSet = resolverUtil.getClasses();
        return clazzSet;
    }

    /**
     * 过滤 匿名类 接口 抽象类
     * @param clazz
     * @return
     */
    private boolean isValidEnum(Class clazz) {
        return !clazz.isAnonymousClass() && !clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers());
    }

    public void setEnumPackages(String enumPackages) {
        this.enumPackages = enumPackages;
    }

    public void setTypeHandlerPackages(String typeHandlerPackages) {
        this.typeHandlerPackages = typeHandlerPackages;
    }
}
