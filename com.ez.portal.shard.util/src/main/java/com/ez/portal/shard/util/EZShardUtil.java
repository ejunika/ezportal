package com.ez.portal.shard.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.shards.ShardId;
import org.hibernate.shards.ShardedConfiguration;
import org.hibernate.shards.cfg.ConfigurationToShardConfigurationAdapter;
import org.hibernate.shards.cfg.ShardConfiguration;
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

public class EZShardUtil {

    private List<Class<? extends Shardable>> entityClasses;

    private SessionFactory sessionFactory;

    private ShardedConfiguration shardedConfiguration;

    private ShardStrategyFactory shardStrategyFactory;
    
    private static final Map<Integer, Integer> VIRTUAL_SHARD = new HashMap<>();

    public EZShardUtil() {
        super();
        entityClasses = new ArrayList<>();
    }
    
    public List<Class<? extends Shardable>> getEntityClasses() {
        return entityClasses;
    }

    public void setEntityClasses(List<Class<? extends Shardable>> entityClasses) {
        this.entityClasses = entityClasses;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            configure();
            sessionFactory = shardedConfiguration.buildShardedSessionFactory();
        }
        return sessionFactory;
    }
    
    public void initSessionFactory(String[] shardKeys) {
        configure();
        sessionFactory = getSessionFactory(shardKeys);
    }
    
    public void initSessionFactory(List<String> shardKeys) {
        configure();
        sessionFactory = getSessionFactory(shardKeys);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory(String[] shardKeys) {
        List<ShardId> shardIds = new ArrayList<>();
        ShardedSessionFactoryImpl shardedSessionFactory = (ShardedSessionFactoryImpl) shardedConfiguration
                .buildShardedSessionFactory();
        if (shardKeys != null) {
            for (String shardKey : shardKeys) {
                shardIds.add(new ShardId(Integer.parseInt(shardKey)));
            }
        }
        return shardedSessionFactory.getSessionFactory(shardIds, shardStrategyFactory);
    }

    public SessionFactory getSessionFactory(List<String> shardKeys) {
        List<ShardId> shardIds = new ArrayList<>();
        ShardedSessionFactoryImpl shardedSessionFactory = (ShardedSessionFactoryImpl) shardedConfiguration
                .buildShardedSessionFactory();
        if (shardKeys != null) {
            for (String shardKey : shardKeys) {
                shardIds.add(new ShardId(Integer.parseInt(shardKey)));
            }
        }
        return shardedSessionFactory.getSessionFactory(shardIds, shardStrategyFactory);
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

    public EZShardUtil configure() {
        if (shardStrategyFactory == null || shardedConfiguration == null) {
            Configuration ptConfig = getConfiguration(0);
            List<ShardConfiguration> shardConfigurations = new ArrayList<>();
            for (Integer i = 0; i < 5; i++) {
                VIRTUAL_SHARD.put(i, i);
                shardConfigurations.add(getShardConfiguration(getConfiguration(i)));
            }
            shardStrategyFactory = buildShardStrategyFactory();
            shardedConfiguration = new ShardedConfiguration(ptConfig, shardConfigurations, shardStrategyFactory, VIRTUAL_SHARD);
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

}
