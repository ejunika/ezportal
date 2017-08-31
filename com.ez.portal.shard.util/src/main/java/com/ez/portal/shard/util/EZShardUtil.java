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

import com.ez.portal.core.entity.HibernateProperty;
import com.ez.portal.core.entity.Shardable;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserSpace;
import com.ez.portal.core.util.EntryStatus;
import com.ez.portal.shard.session.factory.EZShardSessionFactory;

/**
 * @author azaz.akhtar
 *
 */
public class EZShardUtil {

	/**
	 * 
	 */
	private List<Class<? extends Shardable>> entityClasses;

	/**
	 * 
	 */
	private SessionFactory sessionFactory;

	/**
	 * 
	 */
	private ShardedConfiguration shardedConfiguration;

	/**
	 * 
	 */
	private ShardStrategyFactory shardStrategyFactory;

	/**
	 * 
	 */
	private EZShardSessionFactory ezShardSessionFactory;

	/**
	 * 
	 */
	private String shardKey;

	/**
	 * 
	 */
	private User activeUser;

	/**
	 * 
	 */
	private static final Map<Integer, Integer> VIRTUAL_SHARD = new HashMap<>();

	/**
	 * 
	 */
	public EZShardUtil() {
		super();
	}

	/**
	 * @return
	 */
	public List<Class<? extends Shardable>> getEntityClasses() {
		return entityClasses;
	}

	/**
	 * @param entityClasses
	 */
	public void setEntityClasses(List<Class<? extends Shardable>> entityClasses) {
		this.entityClasses = entityClasses;
	}

	/**
	 * @return
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param shardKeys
	 */
	public void initSessionFactory(String[] shardKeys) {
		sessionFactory = getSessionFactory(shardKeys);
	}

	/**
	 * @param shardKey
	 */
	public void initSessionFactory(String shardKey) {
		sessionFactory = getSessionFactory(shardKey);
	}

	/**
	 * Initialize {@link SessionFactory} with all shardKeys
	 */
	public void initSessionFactory() {
		sessionFactory = shardedConfiguration.buildShardedSessionFactory();
	}

	/**
	 * Initialize {@link SessionFactory} with specified Shards
	 * 
	 * @param shardKeys
	 */
	public void initSessionFactory(List<String> shardKeys) {
		sessionFactory = getSessionFactory(shardKeys);
	}

	/**
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return
	 */
	public EZShardSessionFactory getEzShardSessionFactory() {
		return ezShardSessionFactory;
	}

	/**
	 * @param ezShardSessionFactory
	 */
	public void setEzShardSessionFactory(EZShardSessionFactory ezShardSessionFactory) {
		this.ezShardSessionFactory = ezShardSessionFactory;
	}

	/**
	 * @return
	 */
	public String getShardKey() {
		return shardKey;
	}

	/**
	 * @return
	 */
	public User getActiveUser() {
		return activeUser;
	}

	/**
	 * @param activeUser
	 */
	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

	/**
	 * @param shardKey
	 */
	public void setShardKey(String shardKey) {
		this.shardKey = shardKey;
	}

	/**
	 * @param shardKeys
	 * @return
	 */
	public SessionFactory getSessionFactory(String[] shardKeys) {
		List<ShardId> shardIds = new ArrayList<>();
		ShardedSessionFactoryImpl shardedSessionFactory = (ShardedSessionFactoryImpl) shardedConfiguration
				.buildShardedSessionFactory();
		if (shardKeys != null) {
			for (String shardKey : shardKeys) {
				try {
					shardIds.add(new ShardId(Integer.parseInt(shardKey)));
				} catch (NumberFormatException numberFormatException) {
					numberFormatException.printStackTrace();
				}
			}
		}
		return shardedSessionFactory.getSessionFactory(shardIds, shardStrategyFactory);
	}

	/**
	 * @param shardKeys
	 * @return
	 */
	public SessionFactory getSessionFactory(List<String> shardKeys) {
		List<ShardId> shardIds = new ArrayList<>();
		ShardedSessionFactoryImpl shardedSessionFactory = (ShardedSessionFactoryImpl) shardedConfiguration
				.buildShardedSessionFactory();
		if (shardKeys != null) {
			for (String shardKey : shardKeys) {
				try {
					shardIds.add(new ShardId(Integer.parseInt(shardKey)));
				} catch (NumberFormatException numberFormatException) {
					numberFormatException.printStackTrace();
				}
			}
		}
		return shardedSessionFactory.getSessionFactory(shardIds, shardStrategyFactory);
	}

	/**
	 * @param shardKey
	 * @return
	 */
	public SessionFactory getSessionFactory(String shardKey) {
		List<ShardId> shardIds = new ArrayList<>();
		ShardedSessionFactoryImpl shardedSessionFactory = (ShardedSessionFactoryImpl) shardedConfiguration
				.buildShardedSessionFactory();
		if (shardKey != null) {
			shardIds.add(new ShardId(Integer.parseInt(shardKey)));
		}
		return shardedSessionFactory.getSessionFactory(shardIds, shardStrategyFactory);
	}

	/**
	 * @param spaces
	 * @return
	 * @throws Exception
	 */
	public EZShardUtil configureNew(List<UserSpace> spaces) throws Exception {
		Configuration ptConfig = null;
		List<ShardConfiguration> shardConfigurations = null;
		if (!spaces.isEmpty()) {
			Integer i = 1;
			Boolean ptConfigFound = false;
			shardConfigurations = new ArrayList<>();
			for (UserSpace space : spaces) {
				if (space.getEntryStatus() == EntryStatus.ACTIVE_ENTRY) {
					if (!ptConfigFound) {
						ptConfig = getConfiguration(space.getHibernateProperties());
						ptConfigFound = true;
					}
					VIRTUAL_SHARD.put(i, space.getUserSpaceId().intValue());
					shardConfigurations.add(getShardConfiguration(getConfiguration(space.getHibernateProperties())));
				}
				i++;
			}
			shardStrategyFactory = buildShardStrategyFactory();
			shardedConfiguration = new ShardedConfiguration(ptConfig, shardConfigurations, shardStrategyFactory,
					VIRTUAL_SHARD);
			shardedConfiguration.buildShardedSessionFactory();
		} else {
			throw new Exception("UserSpaceNotFoundException");
		}
		return this;
	}

	/**
	 * @param spaces
	 * @return
	 * @throws Exception
	 */
	public EZShardUtil configure(List<UserSpace> spaces) throws Exception {
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
			shardedConfiguration = new ShardedConfiguration(ptConfig, shardConfigurations, shardStrategyFactory,
					VIRTUAL_SHARD);
			shardedConfiguration.buildShardedSessionFactory();
		}
		return this;
	}

	/**
	 * @param hibernateProperties
	 * @return
	 */
	public Configuration getConfiguration(List<HibernateProperty> hibernateProperties) {
		Configuration configuration = new Configuration();
		for (HibernateProperty hibernateProperty : hibernateProperties) {
			configuration.setProperty(hibernateProperty.getPropertyName(), hibernateProperty.getPropertyValue());
		}
		for (Class<? extends Serializable> entityClass : entityClasses) {
			configuration.addAnnotatedClass(entityClass);
		}
		return configuration;
	}

	/**
	 * @param configuration
	 * @return
	 */
	public ShardConfiguration getShardConfiguration(Configuration configuration) {
		return new ConfigurationToShardConfigurationAdapter(configuration);
	}

	/**
	 * @param entityClass
	 * @return
	 */
	public EZShardUtil addEntityClass(Class<? extends Shardable> entityClass) {
		this.entityClasses.add(entityClass);
		return this;
	}

	/**
	 * @return
	 */
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

	/**
	 * @param spaces
	 * @throws Exception
	 */
	public void buildShard(List<UserSpace> spaces) throws Exception {
		if (spaces != null && !spaces.isEmpty()) {
			configure(spaces).initSessionFactory();
		} else {
			throw new Exception("UserSpaceNotFoundException");
		}
	}

}
