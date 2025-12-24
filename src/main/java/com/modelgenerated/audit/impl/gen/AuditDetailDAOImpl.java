/* AuditDetailDAOImpl.java
* Generated Data Access Object Implementation
*/

package com.modelgenerated.audit.impl.gen;

import com.modelgenerated.audit.Audit;
import com.modelgenerated.audit.AuditDetail;
import com.modelgenerated.audit.AuditDetailList;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class AuditDetailDAOImpl extends AbstractDataAccessObject implements AuditDetailDAO {
    String sqlSelect;
    String sqlSelectWithoutJoin;
    String sqlInsert;
    String sqlUpdate;

    public AuditDetailDAOImpl() {
        Logger.debug(this, "XXXXXXXXXXXXXXXXXXXXXX Constructor: AuditDetailDAOImpl");
        StringBuilder sql = new StringBuilder();
        sql.append("ad.id,");
        sql.append("ad.TableName,");
        sql.append("ad.ColumnName,");
        sql.append("ad.OldValue,");
        sql.append("ad.NewValue,");
        sql.append("ad.RecordId,");
        sql.append("ad.AuditId ");
        sqlSelect = sql.toString();

        sql = new StringBuilder();
        sql.append("ad.id,");
        sql.append("ad.TableName,");
        sql.append("ad.ColumnName,");
        sql.append("ad.OldValue,");
        sql.append("ad.NewValue,");
        sql.append("ad.RecordId,");
        sql.append("ad.AuditId ");
        sqlSelectWithoutJoin = sql.toString();

        sql = new StringBuilder();
        sql.append("insert AuditDetail (id,");
        sql.append("tid,");
        sql.append("TableName,");
        sql.append("ColumnName,");
        sql.append("OldValue,");
        sql.append("NewValue,");
        sql.append("RecordId,");
        sql.append("AuditId)");
        sql.append("values (?, ?, ?, ?, ?, ?, ?, ?)");
        sqlInsert = sql.toString();

        sql = new StringBuilder();
        sql.append("update AuditDetail set ");
        sql.append("TableName = ?,");
        sql.append("ColumnName = ?,");
        sql.append("OldValue = ?,");
        sql.append("NewValue = ?,");
        sql.append("RecordId = ?,");
        sql.append("AuditId = ? ");
        sql.append("where tid = ? ");
        sql.append("and id = ?");
        sqlUpdate = sql.toString();

    }
    public ValueObject newValueObject() {
        AuditDetail auditDetail = (AuditDetail)Factory.createObject(AuditDetail.class);
        Identity id = IdentityBuilder.createIdentity();
        auditDetail.setId(id);
        return auditDetail;
    }

    public ValueObjectList newListObject() {
        AuditDetailList auditDetailList = (AuditDetailList)Factory.createObject(AuditDetailList.class);
        return auditDetailList;
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
            sql.append("from AuditDetail as ad ");
            // objectTableAlias: ad
            // columnName: id
            // realname: id
            sql.append("where ad.tid = ? ");
            sql.append("and ad.id = ? ");
            Logger.debug(this, "select from AuditDetail");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            JDBCUtil.setStatement(statement, 1, transactionContext.getUserContext().getTenantId(), false);
            statement.setBytes(2, id.getByteValue());
            resultSet = statement.executeQuery();
            AuditDetailList auditDetailList = getAuditDetailListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(auditDetailList.size() <= 1, "auditDetailList.size() <= 1");
            AuditDetail auditDetail = null;
            if (auditDetailList.size() == 1) {
                auditDetail = (AuditDetail)auditDetailList.get(0);
                loadedObjects.put(auditDetail.getId(), auditDetail );
                loadChildren(transactionContext, auditDetail, loadedObjects);
            }

            return auditDetail;
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
            sql.append("from AuditDetail as ad ");
            sql.append("where ad.tid = ? ");
            sql.append("and ad.id = ? ");
            Logger.debug(this, "select without joins from AuditDetail");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            statement.setBytes(1, transactionContext.getUserContext().getTenantId().getByteValue());
            statement.setBytes(2, id.getByteValue());
            resultSet = statement.executeQuery();
            Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
            AuditDetailList auditDetailList = getAuditDetailListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(auditDetailList.size() <= 1, "auditDetailList.size() <= 1");
            AuditDetail auditDetail = null;
            if (auditDetailList.size() == 1) {
                auditDetail = (AuditDetail)auditDetailList.get(0);
            }

            return auditDetail;
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
    public AuditDetailList getAuditDetailListFromResultSet(TransactionContext transactionContext, ResultSet resultSet, Map<Identity,ValueObject> loadedObjects) {
        try {
            AuditDetailList auditDetailList = (AuditDetailList)Factory.createObject(AuditDetailList.class);

            while (resultSet.next()) {
                Identity returnedId = IdentityBuilder.createIdentity(resultSet.getBytes(1));
                AuditDetail auditDetail = (AuditDetail)loadedObjects.get(returnedId);
                if (auditDetail == null) {
                    auditDetail = (AuditDetail)Factory.createObject(AuditDetail.class);
                    auditDetail.setUserContext(transactionContext.getUserContext());

                    auditDetail.setId(returnedId);
                    auditDetail.setTableName(resultSet.getString(2));
                    auditDetail.setColumnName(resultSet.getString(3));
                    auditDetail.setOldValue(resultSet.getString(4));
                    auditDetail.setNewValue(resultSet.getString(5));
                    byte[] recordIdbytes = resultSet.getBytes(6);
                    if (recordIdbytes != null) {
                        Identity recordId = IdentityBuilder.createIdentity(recordIdbytes);
                        auditDetail.setRecordId(recordId);
                    }
                    SubObjectHelper.setSubObjectId(auditDetail, "ParentId", resultSet.getBytes(7));

                    auditDetail.setIsNew(false);
                    auditDetail.setIsDirty(false);
                    loadedObjects.put(returnedId, auditDetail);
                }
                auditDetailList.add(auditDetail);
            }
            auditDetailList.setIsFullyLoaded(true);
            return auditDetailList;
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
        AuditDetail auditDetail = (AuditDetail)valueObject;
        PreparedStatement statement = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            Logger.debug(this, "sql: " + sqlInsert);
            statement = connection.prepareStatement(sqlInsert);

            JDBCUtil.setStatement(statement, 1, auditDetail.getId(), false);
            JDBCUtil.setStatement(statement, 2, transactionContext.getUserContext().getTenantId(), false);
            JDBCUtil.setStatement(statement, 3, auditDetail.getTableName(), false, "auditDetail.TableName", 50);
            JDBCUtil.setStatement(statement, 4, auditDetail.getColumnName(), true, "auditDetail.ColumnName", 50);
            JDBCUtil.setStatement(statement, 5, auditDetail.getOldValue(), true, "auditDetail.OldValue", 255);
            JDBCUtil.setStatement(statement, 6, auditDetail.getNewValue(), true, "auditDetail.NewValue", 255);
            JDBCUtil.setStatement(statement, 7, auditDetail.getRecordId(), true);
            JDBCUtil.setStatement(statement, 8, SubObjectHelper.getSubObjectId(auditDetail, "ParentId"), true);
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
            AuditDetail auditDetail = (AuditDetail)valueObject;
            PreparedStatement statement = null;
            try {
                Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
                Assert.check(connection != null, "connection != null");

            Logger.debug(this, "sql: " + sqlUpdate);
            statement = connection.prepareStatement(sqlUpdate);

            JDBCUtil.setStatement(statement, 1, auditDetail.getTableName(), false);
            JDBCUtil.setStatement(statement, 2, auditDetail.getColumnName(), true);
            JDBCUtil.setStatement(statement, 3, auditDetail.getOldValue(), true);
            JDBCUtil.setStatement(statement, 4, auditDetail.getNewValue(), true);
            JDBCUtil.setStatement(statement, 5, auditDetail.getRecordId(), true);
            JDBCUtil.setStatement(statement, 6, SubObjectHelper.getSubObjectId(auditDetail, "ParentId"), true);
            // where clause
            JDBCUtil.setStatement(statement, 7, transactionContext.getUserContext().getTenantId(), false);
            JDBCUtil.setStatement(statement, 8, auditDetail.getId(), false);

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
        AuditDetail auditDetail = (AuditDetail)valueObject;
        PreparedStatement statement = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("delete from AuditDetail");
            sql.append(" where id = ?");
            Logger.debug(this, "sql: " + sql.toString());
            statement = connection.prepareStatement(sql.toString());

            // where clause
            JDBCUtil.setStatement(statement, 1, auditDetail.getId(), false);

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

    public AuditDetailList findByParent(UserContext userContext, Audit parent) {
        Assert.check(userContext != null, "userContext != null");
        Assert.check(parent != null, "parent != null");
        TransactionContext transactionContext = new TransactionContext(userContext);
        try {
            return findByParent(transactionContext, parent);
        } finally {
            transactionContext.close();
        }
    }
    public AuditDetailList findByParent(TransactionContext transactionContext, Audit parent) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(parent != null, "parent != null");
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        return findByParent(transactionContext,parent, loadedObjects);
    }

    public AuditDetailList findByParent(TransactionContext transactionContext, Audit parent, Map<Identity,ValueObject> loadedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(parent != null, "parent != null");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("select ");
            sql.append(sqlSelect);
            sql.append("from AuditDetail as ad ");
            // objectTableAlias: ad
            // columnName: AuditId
            // realname: AuditId
            sql.append("where ad.tid = ? ");
            sql.append("and ad.AuditId = ? ");
            Logger.debug(this, "select from AuditDetail");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            JDBCUtil.setStatement(statement, 1, transactionContext.getUserContext().getTenantId(), false);
            statement.setBytes(2, parent.getId().getByteValue());
            resultSet = statement.executeQuery();
            AuditDetailList auditDetailList = getAuditDetailListFromResultSet(transactionContext, resultSet, loadedObjects);
            Iterator i = auditDetailList.iterator();
            while (i.hasNext()) {
                AuditDetail auditDetail = (AuditDetail)i.next();
                loadChildren(transactionContext, auditDetail, loadedObjects);
            }
            return auditDetailList;
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

    public AuditDetailList search(UserContext userContext, SearchCriteria searchCriteria, boolean deepCopy) {
        Assert.check(userContext != null, "userContext != null");
        TransactionContext transactionContext = new TransactionContext(userContext);
        try {
            return search(transactionContext, searchCriteria, deepCopy);
        } finally {
            transactionContext.close();
        }
    }

    public AuditDetailList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        return search(transactionContext, searchCriteria, deepCopy, loadedObjects);
    }

    public AuditDetailList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy, Map<Identity,ValueObject> loadedObjects) {
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
            sql.append("from AuditDetail as ad ");
            searchCriteria.setPreviousAlias("ad");
            sql.append(searchCriteria.getFromClause());
            String whereClause = searchCriteria.getWhereClause();
            sql.append("where ad.tid = ? ");
            if (!StringUtil.isEmpty(whereClause)) { 
                sql.append(" and (");
                sql.append(whereClause);
                sql.append(")");
            }
            sql.append(searchCriteria.getOrderBy());
            sql.append(getVendorSpecificRowLimitPostfix(connection, searchCriteria.getTopAmount()));
            Logger.debug(this, "select from AuditDetail");
            Logger.debug(this, sql);
            PreparedStatement statement = connection.prepareStatement(sql.toString());

            Identity tenantId = transactionContext.getUserContext().getTenantId();
            Assert.check(tenantId != null, "tenantId != null");
            JDBCUtil.setStatement(statement, 1, tenantId, false);
            searchCriteria.setParameters(statement, 2);
            ResultSet resultSet = statement.executeQuery();

            AuditDetailList auditDetailList = getAuditDetailListFromResultSet(transactionContext, resultSet, loadedObjects);
            if (deepCopy) {
                for (AuditDetail auditDetail : auditDetailList) {
                    loadChildren(transactionContext, auditDetail, loadedObjects);
                }
            }
            return auditDetailList;
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
            sql.append("from AuditDetail as ad ");
            searchCriteria.setPreviousAlias("ad");
            sql.append(searchCriteria.getFromClause());
            String whereClause = searchCriteria.getWhereClause();
            sql.append("where ad.tid = ? ");
            if (!StringUtil.isEmpty(whereClause)) { 
                sql.append(" and (");
                sql.append(whereClause);
                sql.append(")");
            }
            Logger.debug(this, "select from AuditDetail");
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
        AuditDetail auditDetail = (AuditDetail)valueObject;

        /*
        Identity parentId = SubObjectHelper.getSubObjectId(auditDetail, "ParentId");
        if (parentId != null) {
            Audit audit = (Audit)loadedObjects.get(parentId);
            if (audit == null) {
                AuditDAO auditDAO = (AuditDAO)DataAccessLocator.findDAO("com.modelgenerated.audit.Audit");
                audit = (Audit)auditDAO.find(transactionContext, parentId, loadedObjects);
            }
            auditDetail.setParent(audit);
        }

        */
    }

    public void saveChildren(TransactionContext transactionContext, ValueObject valueObject, Map<Identity,ValueObject> savedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        AuditDetail auditDetail = (AuditDetail)valueObject;

        Audit parent = null;
        try {
            parent = auditDetail.getParent();
        } catch (ObjectNotLoadedException e) {}
        if (parent != null) {
            AuditDAO auditDAO = (AuditDAO)DataAccessLocator.findDAO("com.modelgenerated.audit.Audit");
            auditDAO.save(transactionContext, parent, savedObjects);
        }


    }
}
