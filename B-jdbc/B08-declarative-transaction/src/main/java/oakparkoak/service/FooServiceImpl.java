package oakparkoak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @package: oakparkoak.service
 * @author: Captain
 * @time: 3/7/2021 10:16 AM
 */
@Service
public class FooServiceImpl implements IFooService {
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private IFooService service;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insert() {
        jdbc.execute("insert into FOO (NAME) values ('name-1')");
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertWithRollback() throws RuntimeException {
        jdbc.execute("insert into FOO (NAME) values ('name-2')");
        throw new RuntimeException("Test rollback.");
    }

    @Override
    public void invokeSelfWithThis() throws RuntimeException {
        insertWithRollback();
    }

    @Override
    public void invokeSelfWithProxy() throws RuntimeException {
        service.insertWithRollback();
    }
}
