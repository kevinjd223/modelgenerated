/* TenantDAOImpl.java
* Generated Data Access Object Implementation
*/

package com.modelgenerated.authentication.impl.gen;

import com.modelgenerated.audit.Audit;
import com.modelgenerated.audit.AuditUtil;
import com.modelgenerated.authentication.Tenant;
import com.modelgenerated.authentication.TenantList;
import com.modelgenerated.authentication.dao.TenantDAO;
import com.modelgenerated.foundation.dataaccess.AbstractDataAccessObject;
import com.modelgenerated.foundation.dataaccess.DataAccessException;
import com.modelgenerated.foundation.dataaccess.DataAccessExceptionDuplicate;
import com.modelgenerated.foundation.dataaccess.DataAccessLocator;
import com.modelgenerated.foundation.dataaccess.JDBCUtil;
import com.modelgenerated.foundation.dataaccess.JitLoadingSwitchVisitor;
import com.modelgenerated.foundation.dataaccess.ObjectNotLoadedException;
import com.modelgenerated.foundation.dataaccess.ResultSetWrapper;
import com.modelgenerated.foundation.dataaccess.SearchCriteria;
import com.modelgenerated.foundation.dataaccess.SearchCriteriaBase;
import com.modelgenerated.foundation.dataaccess.SubObjectHelper;
import com.modelgenerated.foundation.dataaccess.TransactionContext;
import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.dataaccess.ValueObjectList;
import com.modelgenerated.foundation.factory.Factory;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.foundation.identity.IdentityBuilder;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.Assert;
import com.modelgenerated.util.StringUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class TenantDAOImpl extends AbstractDataAccessObject implements TenantDAO {
    String sqlSelect;
    String sqlSelectWithoutJoin;
    String sqlInsert;
    String sqlUpdate;

    public TenantDAOImpl() {
        Logger.debug(this, "XXXXXXXXXXXXXXXXXXXXXX Constructor: TenantDAOImpl");
        StringBuilder sql = new StringBuilder();
        sql.append("obj.id,");
        sql.append("obj.TenantName ");
        sqlSelect = sql.toString();

        sql = new StringBuilder();
        sql.append("obj.id,");
        sql.append("obj.TenantName ");
        sqlSelectWithoutJoin = sql.toString();

        sql = new StringBuilder();
        sql.append("insert Tenant (id,");
        sql.append("TenantName)");
        sql.append("values (?, ?)");
        sqlInsert = sql.toString();

        sql = new StringBuilder();
        sql.append("update Tenant set ");
        sql.append("TenantName = ? ");
        sql.append("where id = ?");
        sqlUpdate = sql.toString();

    }
    public ValueObject newValueObject() {
        Tenant tenant = (Tenant)Factory.createObject(Tenant.class);
        Identity id = IdentityBuilder.createIdentity();
        tenant.setId(id);
        return tenant;
    }

    public ValueObjectList newListObject() {
        TenantList tenantList = (TenantList)Factory.createObject(TenantList.class);
        return tenantList;
    }

    public ValueObject find(TransactionContext transactionContext, Identity id) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(id != null, "id != null");
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        return find(transactionContext,id, loadedObjects);
    }

    public ValueObject find(TransactionContext transactionContext, Identity id, Map<Identity,ValueObject> loadedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(id != null, "id != null");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("select ");
            sql.append(sqlSelect);
            sql.append("from Tenant as obj ");
            sql.append("where obj.id = ? ");
            Logger.debug(this, "select from Tenant");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            statement.setBytes(1, id.getByteValue());
            resultSet = statement.executeQuery();
            TenantList tenantList = getTenantListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(tenantList.size() <= 1, "tenantList.size() <= 1");
            Tenant tenant = null;
            if (tenantList.size() == 1) {
                tenant = (Tenant)tenantList.get(0);
                loadedObjects.put(tenant.getId(), tenant );
                loadChildren(transactionContext, tenant, loadedObjects);
            }

            return tenant;
        } catch (SQLException e) {
            throw new DataAccessException ("Error finding record ", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.error(this, "Error in finally statement");
                Logger.error(this, e);
            } 
        } 
    }
    public ValueObject findForUpdate(TransactionContext transactionContext, Identity id) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(id != null, "id != null");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("select ");
            sql.append(sqlSelectWithoutJoin);
            sql.append("from Tenant as obj ");
            sql.append("where obj.id = ? ");
            Logger.debug(this, "select without joins from Tenant");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            statement.setBytes(1, id.getByteValue());
            resultSet = statement.executeQuery();
            Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
            TenantList tenantList = getTenantListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(tenantList.size() <= 1, "tenantList.size() <= 1");
            Tenant tenant = null;
            if (tenantList.size() == 1) {
                tenant = (Tenant)tenantList.get(0);
            }

            return tenant;
        } catch (SQLException e) {
            throw new DataAccessException ("Error finding record ", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.error(this, "Error in finally statement");
                Logger.error(this, e);
            } 
        }
    }
    public TenantList getTenantListFromResultSet(TransactionContext transactionContext, ResultSet resultSet, Map<Identity,ValueObject> loadedObjects) {
        try {
            TenantList tenantList = (TenantList)Factory.createObject(TenantList.class);

            while (resultSet.next()) {
                Identity returnedId = IdentityBuilder.createIdentity(resultSet.getBytes(1));
                Tenant tenant = (Tenant)loadedObjects.get(returnedId);
                if (tenant == null) {
                    tenant = (Tenant)Factory.createObject(Tenant.class);
                    tenant.setUserContext(transactionContext.getUserContext());

                    tenant.setId(returnedId);
                    tenant.setTenantName(resultSet.getString(2));

                    tenant.setIsNew(false);
                    tenant.setIsDirty(false);
                    loadedObjects.put(returnedId, tenant);
                }
                tenantList.add(tenant);
            }
            tenantList.setIsFullyLoaded(true);
            return tenantList;
        } catch (SQLException e) {
            throw new DataAccessException ("Error finding record ", e);
        }
    }

    public void preSaveNew(TransactionContext transactionContext, ValueObject valueObject) {
    }

    public void preSaveExisting(TransactionContext transactionContext, ValueObject valueObject) {
    }

    public void saveNew(TransactionContext transactionContext, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Tenant tenant = (Tenant)valueObject;
        PreparedStatement statement = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            Logger.debug(this, "sql: " + sqlInsert);
            statement = connection.prepareStatement(sqlInsert);

            JDBCUtil.setStatement(statement, 1, tenant.getId(), false);
            JDBCUtil.setStatement(statement, 2, tenant.getTenantName(), true, "tenant.TenantName", 50);
            statement.execute();

        } catch (SQLException e) {
            // Mysql uses 1062 and SqlServer uses 2601 for duplicates
            if (e.getErrorCode() == 1062 || e.getErrorCode() == 2601) {
                throw new DataAccessExceptionDuplicate("Error saving new record", e);
            } else {
                throw new DataAccessException("Error saving new record", e);
            } 
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.error(this, "Error in finally statement");
                Logger.error(this, e);
            } 
        }
    }

    public void saveNewAuditData(TransactionContext transactionContext, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");

        Tenant tenant = (Tenant)valueObject;

        AuditUtil auditUtil = new AuditUtil();
        Audit audit = auditUtil.createAuditRecord(transactionContext, "add");

        String tenantName = tenant.getTenantName();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Tenant", "TenantName", null, tenantName);
    }

    public void saveExisting(TransactionContext transactionContext, ValueObject existingValueObject, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        if (valueObject.getIsDirty()) {
            Tenant tenant = (Tenant)valueObject;
            PreparedStatement statement = null;
            try {
                Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
                Assert.check(connection != null, "connection != null");

            Logger.debug(this, "sql: " + sqlUpdate);
            statement = connection.prepareStatement(sqlUpdate);

            JDBCUtil.setStatement(statement, 1, tenant.getTenantName(), true);
            // where clause
            JDBCUtil.setStatement(statement, 2, tenant.getId(), false);

            statement.execute();
            } catch (SQLException e) {
                // Mysql uses 1062 and SqlServer uses 2601 for duplicates
                if (e.getErrorCode() == 1062 || e.getErrorCode() == 2601) {
                    throw new DataAccessExceptionDuplicate("Error saving existing record", e);
                } else {
                    throw new DataAccessException("Error saving existing  record", e);
                } 
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    Logger.error(this, "Error in finally statement");
                    Logger.error(this, e);
                } 
            }
        }
    }

    public void saveExistingAuditData(TransactionContext transactionContext, ValueObject existingValueObject, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");

        if (valueObject.getIsDirty()) {
            Tenant newTenant = (Tenant)valueObject;
            Tenant oldTenant = (Tenant)existingValueObject;

            AuditUtil auditUtil = new AuditUtil();
            Audit audit = auditUtil.createAuditRecord(transactionContext, "update");

            String oldTenantName = oldTenant.getTenantName();
            String newTenantName = newTenant.getTenantName();
            if (auditUtil.compareValuesDiffer(newTenantName, oldTenantName)) {
                auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Tenant", "TenantName", oldTenantName, newTenantName);
            }

        }
    }

    public void saveDelete(TransactionContext transactionContext, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Tenant tenant = (Tenant)valueObject;
        PreparedStatement statement = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("delete from Tenant");
            sql.append(" where id = ?");
            Logger.debug(this, "sql: " + sql.toString());
            statement = connection.prepareStatement(sql.toString());

            // where clause
            JDBCUtil.setStatement(statement, 1, tenant.getId(), false);

            statement.execute();
        } catch (SQLException e) {
            throw new DataAccessException("Error deleting record", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.error(this, "Error in finally statement");
                Logger.error(this, e);
            } 
        }
    }

    public void saveDeleteAuditData(TransactionContext transactionContext, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");

        Tenant tenant = (Tenant)valueObject;

        AuditUtil auditUtil = new AuditUtil();
        Audit audit = auditUtil.createAuditRecord(transactionContext, "delete");

        String tenantName = tenant.getTenantName();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Tenant", "TenantName", tenantName, null);

    }

    public Tenant findByName(UserContext userContext, String tenantName) {
        Assert.check(userContext != null, "userContext != null");
        Assert.check(tenantName != null, "tenantName != null");
        TransactionContext transactionContext = new TransactionContext(userContext);
        try {
            return findByName(transactionContext, tenantName);
        } finally {
            transactionContext.close();
        }
    }
    public Tenant findByName(TransactionContext transactionContext, String tenantName) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(tenantName != null, "tenantName != null");
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        return findByName(transactionContext,tenantName, loadedObjects);
    }

    public Tenant findByName(TransactionContext transactionContext, String tenantName, Map<Identity,ValueObject> loadedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(tenantName != null, "tenantName != null");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("select ");
            sql.append(sqlSelect);
            sql.append("from Tenant as obj ");
            sql.append("where obj.TenantName = ? ");
            Logger.debug(this, "select from Tenant");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            statement.setString(1, tenantName);
            resultSet = statement.executeQuery();
            TenantList tenantList = getTenantListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(tenantList.size() <= 1, "tenantList.size() <= 1");
            Tenant tenant = null;
            if (tenantList.size() == 1) {
                tenant = (Tenant)tenantList.get(0);
                loadedObjects.put(tenant.getId(), tenant );
                loadChildren(transactionContext, tenant, loadedObjects);
            }

            return tenant;
        } catch (SQLException e) {
            throw new DataAccessException ("Error finding record ", e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Logger.error(this, "Error in finally statement");
                Logger.error(this, e);
            } 
        } 
    }

    public TenantList search(UserContext userContext, SearchCriteria searchCriteria, boolean deepCopy) {
        Assert.check(userContext != null, "userContext != null");
        TransactionContext transactionContext = new TransactionContext(userContext);
        try {
            return search(transactionContext, searchCriteria, deepCopy);
        } finally {
            transactionContext.close();
        }
    }

    public TenantList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        return search(transactionContext, searchCriteria, deepCopy, loadedObjects);
    }

    public TenantList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy, Map<Identity,ValueObject> loadedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        if (searchCriteria == null) {
            searchCriteria = new SearchCriteriaBase();
        }
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("select ");
            sql.append(getVendorSpecificRowLimitPrefix(connection, searchCriteria.getTopAmount()));
            sql.append(sqlSelect);
            sql.append("from Tenant as obj ");
            searchCriteria.setPreviousAlias("obj");
            sql.append(searchCriteria.getFromClause());
            String whereClause = searchCriteria.getWhereClause();
            if (!StringUtil.isEmpty(whereClause)) { 
                sql.append("where ");
                sql.append(whereClause);
            }
            sql.append(searchCriteria.getOrderBy());
            sql.append(getVendorSpecificRowLimitPostfix(connection, searchCriteria.getTopAmount()));
            Logger.debug(this, "select from Tenant");
            Logger.debug(this, sql);
            PreparedStatement statement = connection.prepareStatement(sql.toString());

            searchCriteria.setParameters(statement, 1);
            ResultSet resultSet = statement.executeQuery();

            TenantList tenantList = getTenantListFromResultSet(transactionContext, resultSet, loadedObjects);
            if (deepCopy) {
                for (Tenant tenant : tenantList) {
                    loadChildren(transactionContext, tenant, loadedObjects);
                }
            }
            return tenantList;
        } catch (SQLException e) {
            throw new DataAccessException ("Error finding record ", e);
        }
    }

    public void loadChildren(TransactionContext transactionContext, ValueObject valueObject, Map<Identity,ValueObject> loadedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Tenant tenant = (Tenant)valueObject;

        /*
        */
    }

    public void saveChildren(TransactionContext transactionContext, ValueObject valueObject, Map<Identity,ValueObject> savedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Tenant tenant = (Tenant)valueObject;


    }
}
