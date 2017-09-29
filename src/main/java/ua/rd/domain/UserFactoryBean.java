package ua.rd.domain;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by Olha_Chuchuk on 9/20/2017.
 */
public class UserFactoryBean implements FactoryBean<User>{
    @Override
    public User getObject() throws Exception {
        return new User(null);
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
