/* AuditDAOImpl.java
* Generated Data Access Object Implementation
*/

package com.modelgenerated.audit.impl.gen;

import com.modelgenerated.audit.Audit;
import com.modelgenerated.audit.AuditDetail;
import com.modelgenerated.audit.AuditDetailList;
import com.modelgenerated.audit.AuditList;
import com.modelgenerated.audit.AuditUtil;
import com.modelgenerated.audit.dao.AuditDAO;
import com.modelgenerated.audit.dao.AuditDetailDAO;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class AuditDAOImpl extends AbstractDataAccessObject implements AuditDAO {
    String sqlSelect;
    String sqlSelectWithoutJoin;
    String sqlInsert;
    String sqlUpdate;

    public AuditDAOImpl() {
        Logger.debug(this, "XXXXXXXXXXXXXXXXXXXXXX Constructor: AuditDAOImpl");
        StringBuilder sql = new StringBuilder();
        sql.append("a.id,");
        sql.append("a.SystemUser,");
        sql.append("a.EventType,");
        sql.append("a.EventDate ");
        sqlSelect = sql.toString();

        sql = new StringBuilder();
        sql.append("a.id,");
        sql.append("a.SystemUser,");
        sql.append("a.EventType,");
        sql.append("a.EventDate ");
        sqlSelectWithoutJoin = sql.toString();

        sql = new StringBuilder();
        sql.append("insert Audit (id,");
        sql.append("tid,");
        sql.append("SystemUser,");
        sql.append("EventType,");
        sql.append("EventDate)");
        sql.append("values (?, ?, ?, ?, ?)");
        sqlInsert = sql.toString();

        sql = new StringBuilder();
        sql.append("update Audit set ");
        sql.append("SystemUser = ?,");
        sql.append("EventType = ?,");
        sql.append("EventDate = ? ");
        sql.append("where tid = ? ");
        sql.append("and id = ?");
        sqlUpdate = sql.toString();

    }
    public ValueObject newValueObject() {
        Audit audit = (Audit)Factory.createObject(Audit.class);
        Identity id = IdentityBuilder.createIdentity();
        audit.setId(id);
        return audit;
    }

    public ValueObjectList newListObject() {
        AuditList auditList = (AuditList)Factory.createObject(AuditList.class);
        return auditList;
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
            sql.append("from Audit as a ");
            // objectTableAlias: a
            // columnName: id
            // realname: id
            sql.append("where a.tid = ? ");
            sql.append("and a.id = ? ");
            Logger.debug(this, "select from Audit");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            JDBCUtil.setStatement(statement, 1, transactionContext.getUserContext().getTenantId(), false);
            statement.setBytes(2, id.getByteValue());
            resultSet = statement.executeQuery();
            AuditList auditList = getAuditListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(auditList.size() <= 1, "auditList.size() <= 1");
            Audit audit = null;
            if (auditList.size() == 1) {
                audit = (Audit)auditList.get(0);
                loadedObjects.put(audit.getId(), audit );
                loadChildren(transactionContext, audit, loadedObjects);
            }

            return audit;
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
            sql.append("from Audit as a ");
            sql.append("where a.tid = ? ");
            sql.append("and a.id = ? ");
            Logger.debug(this, "select without joins from Audit");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            statement.setBytes(1, transactionContext.getUserContext().getTenantId().getByteValue());
            statement.setBytes(2, id.getByteValue());
            resultSet = statement.executeQuery();
            Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
            AuditList auditList = getAuditListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(auditList.size() <= 1, "auditList.size() <= 1");
            Audit audit = null;
            if (auditList.size() == 1) {
                audit = (Audit)auditList.get(0);
            }

            return audit;
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
    public AuditList getAuditListFromResultSet(TransactionContext transactionContext, ResultSet resultSet, Map<Identity,ValueObject> loadedObjects) {
        try {
            AuditList auditList = (AuditList)Factory.createObject(AuditList.class);

            while (resultSet.next()) {
                Identity returnedId = IdentityBuilder.createIdentity(resultSet.getBytes(1));
                Audit audit = (Audit)loadedObjects.get(returnedId);
                if (audit == null) {
                    audit = (Audit)Factory.createObject(Audit.class);
                    audit.setUserContext(transactionContext.getUserContext());

                    audit.setId(returnedId);
                    audit.setSystemUser(resultSet.getString(2));
                    audit.setEventType(resultSet.getString(3));
                    audit.setEventDate(resultSet.getTimestamp(4) == null ? null : new java.util.Date(resultSet.getTimestamp(4).getTime()));

                    audit.setIsNew(false);
                    audit.setIsDirty(false);
                    loadedObjects.put(returnedId, audit);
                }
                auditList.add(audit);
            }
            auditList.setIsFullyLoaded(true);
            return auditList;
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
        Audit audit = (Audit)valueObject;
        PreparedStatement statement = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            Logger.debug(this, "sql: " + sqlInsert);
            statement = connection.prepareStatement(sqlInsert);

            JDBCUtil.setStatement(statement, 1, audit.getId(), false);
            JDBCUtil.setStatement(statement, 2, transactionContext.getUserContext().getTenantId(), false);
            JDBCUtil.setStatement(statement, 3, audit.getSystemUser(), false, "audit.SystemUser", 50);
            JDBCUtil.setStatement(statement, 4, audit.getEventType(), false, "audit.EventType", 20);
            JDBCUtil.setStatement(statement, 5, audit.getEventDate(), false);
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
    }

    public void saveExisting(TransactionContext transactionContext, ValueObject existingValueObject, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        if (valueObject.getIsDirty()) {
            Audit audit = (Audit)valueObject;
            PreparedStatement statement = null;
            try {
                Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
                Assert.check(connection != null, "connection != null");

            Logger.debug(this, "sql: " + sqlUpdate);
            statement = connection.prepareStatement(sqlUpdate);

            JDBCUtil.setStatement(statement, 1, audit.getSystemUser(), false);
            JDBCUtil.setStatement(statement, 2, audit.getEventType(), false);
            JDBCUtil.setStatement(statement, 3, audit.getEventDate(), false);
            // where clause
            JDBCUtil.setStatement(statement, 4, transactionContext.getUserContext().getTenantId(), false);
            JDBCUtil.setStatement(statement, 5, audit.getId(), false);

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
    }

    public void saveDelete(TransactionContext transactionContext, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Audit audit = (Audit)valueObject;
        PreparedStatement statement = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("delete from Audit");
            sql.append(" where id = ?");
            Logger.debug(this, "sql: " + sql.toString());
            statement = connection.prepareStatement(sql.toString());

            // where clause
            JDBCUtil.setStatement(statement, 1, audit.getId(), false);

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
    }

    public Audit findByUser(UserContext userContext, String systemUser) {
        Assert.check(userContext != null, "userContext != null");
        Assert.check(systemUser != null, "systemUser != null");
        TransactionContext transactionContext = new TransactionContext(userContext);
        try {
            return findByUser(transactionContext, systemUser);
        } finally {
            transactionContext.close();
        }
    }
    public Audit findByUser(TransactionContext transactionContext, String systemUser) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(systemUser != null, "systemUser != null");
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        return findByUser(transactionContext,systemUser, loadedObjects);
    }

    public Audit findByUser(TransactionContext transactionContext, String systemUser, Map<Identity,ValueObject> loadedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(systemUser != null, "systemUser != null");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("select ");
            sql.append(sqlSelect);
            sql.append("from Audit as a ");
            // objectTableAlias: a
            // columnName: SystemUser
            // realname: SystemUser
            sql.append("where a.tid = ? ");
            sql.append("and a.SystemUser = ? ");
            Logger.debug(this, "select from Audit");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            JDBCUtil.setStatement(statement, 1, transactionContext.getUserContext().getTenantId(), false);
            statement.setString(2, systemUser);
            resultSet = statement.executeQuery();
            AuditList auditList = getAuditListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(auditList.size() <= 1, "auditList.size() <= 1");
            Audit audit = null;
            if (auditList.size() == 1) {
                audit = (Audit)auditList.get(0);
                loadedObjects.put(audit.getId(), audit );
                loadChildren(transactionContext, audit, loadedObjects);
            }

            return audit;
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

    public AuditList search(UserContext userContext, SearchCriteria searchCriteria, boolean deepCopy) {
        Assert.check(userContext != null, "userContext != null");
        TransactionContext transactionContext = new TransactionContext(userContext);
        try {
            return search(transactionContext, searchCriteria, deepCopy);
        } finally {
            transactionContext.close();
        }
    }

    public AuditList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        return search(transactionContext, searchCriteria, deepCopy, loadedObjects);
    }

    public AuditList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy, Map<Identity,ValueObject> loadedObjects) {
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
            sql.append("from Audit as a ");
            searchCriteria.setPreviousAlias("a");
            sql.append(searchCriteria.getFromClause());
            String whereClause = searchCriteria.getWhereClause();
            sql.append("where a.tid = ? ");
            if (!StringUtil.isEmpty(whereClause)) { 
                sql.append(" and (");
                sql.append(whereClause);
                sql.append(")");
            }
            sql.append(searchCriteria.getOrderBy());
            sql.append(getVendorSpecificRowLimitPostfix(connection, searchCriteria.getTopAmount()));
            Logger.debug(this, "select from Audit");
            Logger.debug(this, sql);
            PreparedStatement statement = connection.prepareStatement(sql.toString());

            Identity tenantId = transactionContext.getUserContext().getTenantId();
            Assert.check(tenantId != null, "tenantId != null");
            JDBCUtil.setStatement(statement, 1, tenantId, false);
            searchCriteria.setParameters(statement, 2);
            ResultSet resultSet = statement.executeQuery();

            AuditList auditList = getAuditListFromResultSet(transactionContext, resultSet, loadedObjects);
            if (deepCopy) {
                for (Audit audit : auditList) {
                    loadChildren(transactionContext, audit, loadedObjects);
                }
            }
            return auditList;
        } catch (SQLException e) {
            throw new DataAccessException ("Error finding record ", e);
        }
    }

    public int searchCount(UserContext userContext, SearchCriteria searchCriteria) {
        Assert.check(userContext != null, "userContext != null");
        TransactionContext transactionContext = new TransactionContext(userContext);
        try {
            return searchCount(transactionContext, searchCriteria);
        } finally {
            transactionContext.close();
        }
    }

    public int searchCount(TransactionContext transactionContext, SearchCriteria searchCriteria) {
        Assert.check(transactionContext != null, "transactionContext != null");
        if (searchCriteria == null) {
            searchCriteria = new SearchCriteriaBase();
        }
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("select count(*)");
            sql.append("from Audit as a ");
            searchCriteria.setPreviousAlias("a");
            sql.append(searchCriteria.getFromClause());
            String whereClause = searchCriteria.getWhereClause();
            sql.append("where a.tid = ? ");
            if (!StringUtil.isEmpty(whereClause)) { 
                sql.append(" and (");
                sql.append(whereClause);
                sql.append(")");
            }
            Logger.debug(this, "select from Audit");
            Logger.debug(this, sql);
            PreparedStatement statement = connection.prepareStatement(sql.toString());

            Identity tenantId = transactionContext.getUserContext().getTenantId();
            Assert.check(tenantId != null, "tenantId != null");
            JDBCUtil.setStatement(statement, 1, tenantId, false);
            searchCriteria.setParameters(statement, 2);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DataAccessException ("Error finding record ", e);
        }
    }

    public void loadChildren(TransactionContext transactionContext, ValueObject valueObject, Map<Identity,ValueObject> loadedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Audit audit = (Audit)valueObject;

        /*
        {
            AuditDetailDAO auditDetailDAO = (AuditDetailDAO)DataAccessLocator.findDAO("com.modelgenerated.audit.AuditDetail");
            AuditDetailList auditDetailList = auditDetailDAO.findByParent(transactionContext, audit, loadedObjects);
            audit.setAuditDetailList(auditDetailList);
        }

        */
    }

    public void saveChildren(TransactionContext transactionContext, ValueObject valueObject, Map<Identity,ValueObject> savedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Audit audit = (Audit)valueObject;

        AuditDetailList auditDetailList = null;
        try {
            auditDetailList = audit.getAuditDetailList();
        } catch (ObjectNotLoadedException e) {}
        if (auditDetailList != null) {
            AuditDetailDAO auditDetailDAO = (AuditDetailDAO)DataAccessLocator.findDAO("com.modelgenerated.audit.AuditDetail");
            auditDetailDAO.save(transactionContext, auditDetailList, savedObjects);
        }


    }
}
