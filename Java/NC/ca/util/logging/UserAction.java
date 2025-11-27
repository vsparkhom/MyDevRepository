package ca.util.logging;

import java.math.BigInteger;
import java.util.Date;

import ca.shaw.repository.sql.SqlQueries;

import com.netcracker.ejb.core.NCCoreInternals;
import com.netcracker.framework.jdbc.JDBCTemplates;
import com.netcracker.framework.jdbc.oracle.JDBCType;

public class UserAction {

    private final BigInteger userId;
    private final BigInteger taskId;

    public UserAction(BigInteger userId, BigInteger taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public void save() {
        getJDBCTemplates().executeUpdate(
                "insert /*ncid.j:ca.sw.ry.sl.SQ.LOGUN*/ into NC_PO_USER_CANCELS (user_id, action_date, task_id) values (?,  ?,  ?)",
                new Object[][] {{userId, JDBCType.NUMBER}, {new Date(), JDBCType.DATE},
                        {taskId, JDBCType.NUMBER}});
    }

    protected JDBCTemplates getJDBCTemplates() {
        return NCCoreInternals.jdbcInstance();
    }
}
