package oakparkoak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @package: oakparkoak.service
 * @author: Captain
 * @time: 3/7/2021 11:16 AM
 */
@Service
public class FooServiceImpl implements IFooService {
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private IFooService service;

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.NESTED)
    public void insertInner() throws RuntimeException {
        jdbc.execute("insert into FOO (NAME) values ('name-1')");
        //throw new RuntimeException("Inner Rollback.");
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertOuter() throws RuntimeException {
        jdbc.execute("insert into FOO (NAME) values ('name-2')");
        try {
            service.insertInner();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("Outer Rollback.");
    }
}
