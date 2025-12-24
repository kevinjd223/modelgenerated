/* AccountDAOImpl.java
* Generated Data Access Object Implementation
*/

package com.modelgenerated.authentication.impl.gen;

import com.modelgenerated.audit.Audit;
import com.modelgenerated.audit.AuditUtil;
import com.modelgenerated.authentication.Account;
import com.modelgenerated.authentication.AccountList;
import com.modelgenerated.authentication.dao.AccountDAO;
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


public class AccountDAOImpl extends AbstractDataAccessObject implements AccountDAO {
    String sqlSelect;
    String sqlSelectWithoutJoin;
    String sqlInsert;
    String sqlUpdate;

    public AccountDAOImpl() {
        Logger.debug(this, "XXXXXXXXXXXXXXXXXXXXXX Constructor: AccountDAOImpl");
        StringBuilder sql = new StringBuilder();
        sql.append("obj.id,");
        sql.append("obj.UserName,");
        sql.append("obj.Password,");
        sql.append("obj.LockedOut,");
        sql.append("obj.BadAuthenticationAttempts ");
        sqlSelect = sql.toString();

        sql = new StringBuilder();
        sql.append("obj.id,");
        sql.append("obj.UserName,");
        sql.append("obj.Password,");
        sql.append("obj.LockedOut,");
        sql.append("obj.BadAuthenticationAttempts ");
        sqlSelectWithoutJoin = sql.toString();

        sql = new StringBuilder();
        sql.append("insert Account (id,");
        sql.append("tid,");
        sql.append("UserName,");
        sql.append("Password,");
        sql.append("LockedOut,");
        sql.append("BadAuthenticationAttempts)");
        sql.append("values (?, ?, ?, ?, ?, ?)");
        sqlInsert = sql.toString();

        sql = new StringBuilder();
        sql.append("update Account set ");
        sql.append("UserName = ?,");
        sql.append("Password = ?,");
        sql.append("LockedOut = ?,");
        sql.append("BadAuthenticationAttempts = ? ");
        sql.append("where tid = ? ");
        sql.append("and id = ?");
        sqlUpdate = sql.toString();

    }
    public ValueObject newValueObject() {
        Account account = (Account)Factory.createObject(Account.class);
        Identity id = IdentityBuilder.createIdentity();
        account.setId(id);
        return account;
    }

    public ValueObjectList newListObject() {
        AccountList accountList = (AccountList)Factory.createObject(AccountList.class);
        return accountList;
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
            sql.append("from Account as obj ");
            // objectTableAlias: obj
            // columnName: id
            // realname: id
            sql.append("where obj.tid = ? ");
            sql.append("and obj.id = ? ");
            Logger.debug(this, "select from Account");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            JDBCUtil.setStatement(statement, 1, transactionContext.getUserContext().getTenantId(), false);
            statement.setBytes(2, id.getByteValue());
            resultSet = statement.executeQuery();
            AccountList accountList = getAccountListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(accountList.size() <= 1, "accountList.size() <= 1");
            Account account = null;
            if (accountList.size() == 1) {
                account = (Account)accountList.get(0);
                loadedObjects.put(account.getId(), account );
                loadChildren(transactionContext, account, loadedObjects);
            }

            return account;
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
            sql.append("from Account as obj ");
            sql.append("where obj.tid = ? ");
            sql.append("and obj.id = ? ");
            Logger.debug(this, "select without joins from Account");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            statement.setBytes(1, transactionContext.getUserContext().getTenantId().getByteValue());
            statement.setBytes(2, id.getByteValue());
            resultSet = statement.executeQuery();
            Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
            AccountList accountList = getAccountListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(accountList.size() <= 1, "accountList.size() <= 1");
            Account account = null;
            if (accountList.size() == 1) {
                account = (Account)accountList.get(0);
            }

            return account;
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
    public AccountList getAccountListFromResultSet(TransactionContext transactionContext, ResultSet resultSet, Map<Identity,ValueObject> loadedObjects) {
        try {
            AccountList accountList = (AccountList)Factory.createObject(AccountList.class);

            while (resultSet.next()) {
                Identity returnedId = IdentityBuilder.createIdentity(resultSet.getBytes(1));
                Account account = (Account)loadedObjects.get(returnedId);
                if (account == null) {
                    account = (Account)Factory.createObject(Account.class);
                    account.setUserContext(transactionContext.getUserContext());

                    account.setId(returnedId);
                    account.setUserName(resultSet.getString(2));
                    account.setPassword(resultSet.getString(3));
                    account.setLockedOut(ResultSetWrapper.getBoolean(resultSet, 4));
                    account.setBadAuthenticationAttempts(resultSet.getInt(5));

                    account.setIsNew(false);
                    account.setIsDirty(false);
                    loadedObjects.put(returnedId, account);
                }
                accountList.add(account);
            }
            accountList.setIsFullyLoaded(true);
            return accountList;
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
        Account account = (Account)valueObject;
        PreparedStatement statement = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            Logger.debug(this, "sql: " + sqlInsert);
            statement = connection.prepareStatement(sqlInsert);

            JDBCUtil.setStatement(statement, 1, account.getId(), false);
            JDBCUtil.setStatement(statement, 2, transactionContext.getUserContext().getTenantId(), false);
            JDBCUtil.setStatement(statement, 3, account.getUserName(), true, "account.UserName", 50);
            JDBCUtil.setStatement(statement, 4, account.getPassword(), true, "account.Password", 50);
            JDBCUtil.setStatement(statement, 5, account.getLockedOut(), true);
            JDBCUtil.setStatement(statement, 6, account.getBadAuthenticationAttempts(), false);
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

        Account account = (Account)valueObject;

        AuditUtil auditUtil = new AuditUtil();
        Audit audit = auditUtil.createAuditRecord(transactionContext, "add");

        String userName = account.getUserName();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "UserName", null, userName);
        String password = account.getPassword();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "Password", null, password);
        Boolean lockedOut = account.getLockedOut();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "LockedOut", null, lockedOut);
        int badAuthenticationAttempts = account.getBadAuthenticationAttempts();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "BadAuthenticationAttempts", null, badAuthenticationAttempts);
    }

    public void saveExisting(TransactionContext transactionContext, ValueObject existingValueObject, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        if (valueObject.getIsDirty()) {
            Account account = (Account)valueObject;
            PreparedStatement statement = null;
            try {
                Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
                Assert.check(connection != null, "connection != null");

            Logger.debug(this, "sql: " + sqlUpdate);
            statement = connection.prepareStatement(sqlUpdate);

            JDBCUtil.setStatement(statement, 1, account.getUserName(), true);
            JDBCUtil.setStatement(statement, 2, account.getPassword(), true);
            JDBCUtil.setStatement(statement, 3, account.getLockedOut(), true);
            JDBCUtil.setStatement(statement, 4, account.getBadAuthenticationAttempts(), false);
            // where clause
            JDBCUtil.setStatement(statement, 5, transactionContext.getUserContext().getTenantId(), false);
            JDBCUtil.setStatement(statement, 6, account.getId(), false);

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
            Account newAccount = (Account)valueObject;
            Account oldAccount = (Account)existingValueObject;

            AuditUtil auditUtil = new AuditUtil();
            Audit audit = auditUtil.createAuditRecord(transactionContext, "update");

            String oldUserName = oldAccount.getUserName();
            String newUserName = newAccount.getUserName();
            if (auditUtil.compareValuesDiffer(newUserName, oldUserName)) {
                auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "UserName", oldUserName, newUserName);
            }

            String oldPassword = oldAccount.getPassword();
            String newPassword = newAccount.getPassword();
            if (auditUtil.compareValuesDiffer(newPassword, oldPassword)) {
                auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "Password", oldPassword, newPassword);
            }

            Boolean oldLockedOut = oldAccount.getLockedOut();
            Boolean newLockedOut = newAccount.getLockedOut();
            if (auditUtil.compareValuesDiffer(newLockedOut, oldLockedOut)) {
                auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "LockedOut", oldLockedOut, newLockedOut);
            }

            int oldBadAuthenticationAttempts = oldAccount.getBadAuthenticationAttempts();
            int newBadAuthenticationAttempts = newAccount.getBadAuthenticationAttempts();
            if (auditUtil.compareValuesDiffer(newBadAuthenticationAttempts, oldBadAuthenticationAttempts)) {
                auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "BadAuthenticationAttempts", oldBadAuthenticationAttempts, newBadAuthenticationAttempts);
            }

        }
    }

    public void saveDelete(TransactionContext transactionContext, ValueObject valueObject) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Account account = (Account)valueObject;
        PreparedStatement statement = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("delete from Account");
            sql.append(" where id = ?");
            Logger.debug(this, "sql: " + sql.toString());
            statement = connection.prepareStatement(sql.toString());

            // where clause
            JDBCUtil.setStatement(statement, 1, account.getId(), false);

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

        Account account = (Account)valueObject;

        AuditUtil auditUtil = new AuditUtil();
        Audit audit = auditUtil.createAuditRecord(transactionContext, "delete");

        String userName = account.getUserName();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "UserName", userName, null);

        String password = account.getPassword();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "Password", password, null);

        Boolean lockedOut = account.getLockedOut();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "LockedOut", lockedOut, null);

        int badAuthenticationAttempts = account.getBadAuthenticationAttempts();
        auditUtil.createAuditDetailRecord(transactionContext, audit, valueObject.getId(), "Account", "BadAuthenticationAttempts", badAuthenticationAttempts, null);

    }

    public Account findByUserName(UserContext userContext, String userName) {
        Assert.check(userContext != null, "userContext != null");
        Assert.check(userName != null, "userName != null");
        TransactionContext transactionContext = new TransactionContext(userContext);
        try {
            return findByUserName(transactionContext, userName);
        } finally {
            transactionContext.close();
        }
    }
    public Account findByUserName(TransactionContext transactionContext, String userName) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(userName != null, "userName != null");
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        return findByUserName(transactionContext,userName, loadedObjects);
    }

    public Account findByUserName(TransactionContext transactionContext, String userName, Map<Identity,ValueObject> loadedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(userName != null, "userName != null");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = transactionContext.findConnection(daoDescriptor.getConnectionName());
            Assert.check(connection != null, "connection != null");

            StringBuilder sql = new StringBuilder();
            sql.append("select ");
            sql.append(sqlSelect);
            sql.append("from Account as obj ");
            // objectTableAlias: obj
            // columnName: UserName
            // realname: UserName
            sql.append("where obj.tid = ? ");
            sql.append("and obj.UserName = ? ");
            Logger.debug(this, "select from Account");
            Logger.debug(this, sql);
            statement = connection.prepareStatement(sql.toString());


            JDBCUtil.setStatement(statement, 1, transactionContext.getUserContext().getTenantId(), false);
            statement.setString(2, userName);
            resultSet = statement.executeQuery();
            AccountList accountList = getAccountListFromResultSet(transactionContext, resultSet, loadedObjects);
            Assert.check(accountList.size() <= 1, "accountList.size() <= 1");
            Account account = null;
            if (accountList.size() == 1) {
                account = (Account)accountList.get(0);
                loadedObjects.put(account.getId(), account );
                loadChildren(transactionContext, account, loadedObjects);
            }

            return account;
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

    public AccountList search(UserContext userContext, SearchCriteria searchCriteria, boolean deepCopy) {
        Assert.check(userContext != null, "userContext != null");
        TransactionContext transactionContext = new TransactionContext(userContext);
        try {
            return search(transactionContext, searchCriteria, deepCopy);
        } finally {
            transactionContext.close();
        }
    }

    public AccountList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        return search(transactionContext, searchCriteria, deepCopy, loadedObjects);
    }

    public AccountList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy, Map<Identity,ValueObject> loadedObjects) {
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
            sql.append("from Account as obj ");
            searchCriteria.setPreviousAlias("obj");
            sql.append(searchCriteria.getFromClause());
            String whereClause = searchCriteria.getWhereClause();
            sql.append("where obj.tid = ? ");
            if (!StringUtil.isEmpty(whereClause)) { 
                sql.append(" and (");
                sql.append(whereClause);
                sql.append(")");
            }
            sql.append(searchCriteria.getOrderBy());
            sql.append(getVendorSpecificRowLimitPostfix(connection, searchCriteria.getTopAmount()));
            Logger.debug(this, "select from Account");
            Logger.debug(this, sql);
            PreparedStatement statement = connection.prepareStatement(sql.toString());

            Identity tenantId = transactionContext.getUserContext().getTenantId();
            Assert.check(tenantId != null, "tenantId != null");
            JDBCUtil.setStatement(statement, 1, tenantId, false);
            searchCriteria.setParameters(statement, 2);
            ResultSet resultSet = statement.executeQuery();

            AccountList accountList = getAccountListFromResultSet(transactionContext, resultSet, loadedObjects);
            if (deepCopy) {
                for (Account account : accountList) {
                    loadChildren(transactionContext, account, loadedObjects);
                }
            }
            return accountList;
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
            sql.append("from Account as obj ");
            searchCriteria.setPreviousAlias("obj");
            sql.append(searchCriteria.getFromClause());
            String whereClause = searchCriteria.getWhereClause();
            sql.append("where obj.tid = ? ");
            if (!StringUtil.isEmpty(whereClause)) { 
                sql.append(" and (");
                sql.append(whereClause);
                sql.append(")");
            }
            Logger.debug(this, "select from Account");
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
        Account account = (Account)valueObject;

        /*
        */
    }

    public void saveChildren(TransactionContext transactionContext, ValueObject valueObject, Map<Identity,ValueObject> savedObjects) {
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Account account = (Account)valueObject;


    }
}
