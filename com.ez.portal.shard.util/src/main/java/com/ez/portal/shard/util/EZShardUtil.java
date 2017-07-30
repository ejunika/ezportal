package com.ez.portal.shard.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.shards.ShardId;
import org.hibernate.shards.ShardedConfiguration;
import org.hibernate.shards.cfg.ConfigurationToShardConfigurationAdapter;
import org.hibernate.shards.cfg.ShardConfiguration;
import org.hibernate.shards.session.ShardedSessionFactory;
import org.hibernate.shards.session.ShardedSessionFactoryImpl;
import org.hibernate.shards.strategy.ShardStrategy;
import org.hibernate.shards.strategy.ShardStrategyFactory;
import org.hibernate.shards.strategy.ShardStrategyImpl;
import org.hibernate.shards.strategy.access.SequentialShardAccessStrategy;
import org.hibernate.shards.strategy.access.ShardAccessStrategy;
import org.hibernate.shards.strategy.resolution.AllShardsShardResolutionStrategy;
import org.hibernate.shards.strategy.resolution.ShardResolutionStrategy;
import org.hibernate.shards.strategy.selection.ShardSelectionStrategy;

import com.ez.portal.shard.Shardable;
import com.ez.portal.shard.entity.HibernateProperty;
import com.ez.portal.shard.entity.UserSpace;
import com.ez.portal.shard.session.factory.EZShardSessionFactory;

public class EZShardUtil {

    private List<Class<? extends Shardable>> entityClasses;

    private SessionFactory sessionFactory;

    private ShardedConfiguration shardedConfiguration;
    
    private ShardedSessionFactory shardedSessionFactory;

    private ShardStrategyFactory shardStrategyFactory;
    
    private EZShardSessionFactory ezShardSessionFactory;
    
    private String shardKey;
    
    private static final Map<Integer, Integer> VIRTUAL_SHARD = new HashMap<>();

    public EZShardUtil() {
        super();
    }
    
    public List<Class<? extends Shardable>> getEntityClasses() {
        return entityClasses;
    }

    public void setEntityClasses(List<Class<? extends Shardable>> entityClasses) {
        this.entityClasses = entityClasses;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void initSessionFactory(String[] shardKeys) {
        sessionFactory = getSessionFactory(shardKeys);
    }
    
    public void initSessionFactory() {
        sessionFactory = shardedSessionFactory;
    }
    
    public void initSessionFactory(List<String> shardKeys) {
        sessionFactory = getSessionFactory(shardKeys);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory(String[] shardKeys) {
        List<ShardId> shardIds = new ArrayList<>();
        ShardedSessionFactoryImpl shardedSessionFactoryImpl = (ShardedSessionFactoryImpl) shardedSessionFactory;
        if (shardKeys != null) {
            for (String shardKey : shardKeys) {
                shardIds.add(new ShardId(Integer.parseInt(shardKey)));
            }
        }
        return shardedSessionFactoryImpl.getSessionFactory(shardIds, shardStrategyFactory);
    }

    public SessionFactory getSessionFactory(List<String> shardKeys) {
        List<ShardId> shardIds = new ArrayList<>();
        ShardedSessionFactoryImpl shardedSessionFactoryImpl = (ShardedSessionFactoryImpl) shardedSessionFactory;
        if (shardKeys != null) {
            for (String shardKey : shardKeys) {
                try {
                    shardIds.add(new ShardId(Integer.parseInt(shardKey)));
                } catch (NumberFormatException numberFormatException) {
                    numberFormatException.printStackTrace();
                }
            }
        }
        return shardedSessionFactoryImpl.getSessionFactory(shardIds, shardStrategyFactory);
    }

    public SessionFactory getSessionFactory(String shardKey) {
        List<ShardId> shardIds = new ArrayList<>();
        ShardedSessionFactoryImpl shardedSessionFactory = (ShardedSessionFactoryImpl) shardedConfiguration
                .buildShardedSessionFactory();
        if (shardKey != null) {
            shardIds.add(new ShardId(Integer.parseInt(shardKey)));
        }
        return shardedSessionFactory.getSessionFactory(shardIds, shardStrategyFactory);
    }
    
    public EZShardUtil configure(List<UserSpace> spaces) {
        if (shardStrategyFactory == null || shardedConfiguration == null) {
            Configuration ptConfig = getConfiguration(spaces.get(0).getHibernateProperties());
            List<ShardConfiguration> shardConfigurations = new ArrayList<>();
            Integer i = 1;
            for (UserSpace space : spaces) {
                VIRTUAL_SHARD.put(i, space.getUserSpaceId().intValue());
                shardConfigurations.add(getShardConfiguration(getConfiguration(space.getHibernateProperties())));
                i++;
            }
            shardStrategyFactory = buildShardStrategyFactory();
            shardedConfiguration = new ShardedConfiguration(ptConfig, shardConfigurations, shardStrategyFactory, VIRTUAL_SHARD);
            setShardedSessionFactory(shardedConfiguration.buildShardedSessionFactory());
        }
        return this;
    }

    public Configuration getConfiguration(Integer shardId) {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.session_factory_name", "HibernateSessionFactory" + shardId)
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url",
                        "jdbc:mysql://localhost:3306/PORTAL_" + shardId + "?createDatabaseIfNotExist=true")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "Admin")
                .setProperty("hibernate.connection.shard_id", "" + shardId)
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.shard.enable_cross_shard_relationship_checks", "true");

        for (Class<? extends Serializable> entityClass : entityClasses) {
            configuration.addAnnotatedClass(entityClass);
        }

        return configuration;
    }
    
    public Configuration getConfiguration(List<HibernateProperty> hibernateProperties) {
        Configuration configuration = new Configuration();;
        for (HibernateProperty hibernateProperty : hibernateProperties) {
            configuration.setProperty(hibernateProperty.getPropertyName(), hibernateProperty.getPropertyValue());
        }
        for (Class<? extends Serializable> entityClass : entityClasses) {
            configuration.addAnnotatedClass(entityClass);
        }
        return configuration;
    }

    public ShardConfiguration getShardConfiguration(Configuration configuration) {
        return new ConfigurationToShardConfigurationAdapter(configuration);
    }

    public EZShardUtil addEntityClass(Class<? extends Shardable> entityClass) {
        this.entityClasses.add(entityClass);
        return this;
    }

    public ShardStrategyFactory buildShardStrategyFactory() {
        return new ShardStrategyFactory() {
            @Override
            public ShardStrategy newShardStrategy(List<ShardId> shardIds) {
                ShardSelectionStrategy shardSelectionStrategy = new EZShardSelectionStrategy();
                ShardResolutionStrategy shardResolutionStrategy = new AllShardsShardResolutionStrategy(shardIds);
                ShardAccessStrategy shardAccessStrategy = new SequentialShardAccessStrategy();
                return new ShardStrategyImpl(shardSelectionStrategy, shardResolutionStrategy, shardAccessStrategy);
            }
        };
    }
    
    public void buildShard(List<UserSpace> spaces) {
        if (spaces != null) {
            configure(spaces).getSessionFactory();
        }
    }

    public EZShardSessionFactory getEzShardSessionFactory() {
        return ezShardSessionFactory;
    }

    public void setEzShardSessionFactory(EZShardSessionFactory ezShardSessionFactory) {
        this.ezShardSessionFactory = ezShardSessionFactory;
    }

    public ShardedSessionFactory getShardedSessionFactory() {
        return shardedSessionFactory;
    }

    public void setShardedSessionFactory(ShardedSessionFactory shardedSessionFactory) {
        this.shardedSessionFactory = shardedSessionFactory;
    }
    
    public String getShardKey() {
        return shardKey;
    }

    public void setShardKey(String shardKey) {
        this.shardKey = shardKey;
    }
        
}
