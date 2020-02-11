package com.waracle.cakeservice.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration implements BeanClassLoaderAware {

    @Value("${spcloud.cassandra.contact.point}")
    private  String contactPoint;

    @Value("${spcloud.cassandra.port}")
    private  int port;

    @Value("${spcloud.cassandra.keyspace.name}")
    private  String keyspaceName;

    @Value("${spcloud.cassandra.username}")
    private  String username;

    @Value("${spcloud.cassandra.password}")
    private  String password;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    protected String getContactPoints() {
        return contactPoint;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected boolean getMetricsEnabled() {
        return false;
    }

    @Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean bean = super.cluster();
        bean.setUsername(username);
        bean.setPassword(password);

        return bean;
    }

    /*
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(getKeyspaceName())
                .ifNotExists().with(KeyspaceOption.DURABLE_WRITES, true)
                .withNetworkReplication(DataCenterReplication.of("foo", 1), DataCenterReplication.of("bar", 2));
        getTableCreation();
        return Arrays.asList(specification);
    }

    void getTableCreation() {
        CreateTableSpecification specification = CreateTableSpecification.createTable("cakes")
                .ifNotExists()
                .partitionKeyColumn("ID", DataType.text())
                .column("TITLE" , DataType.text())
                .column("DESCRIPTION", DataType.text())
                .column("IMAGE", DataType.text());
    }
     */

}

