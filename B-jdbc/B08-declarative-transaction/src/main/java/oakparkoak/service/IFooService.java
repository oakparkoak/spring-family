package oakparkoak.service;

/**
 * @package: oakparkoak.service
 * @author: Captain
 * @time: 3/7/2021 10:15 AM
 */
public interface IFooService {
    /**
     * Insert action
     */
    void insert();

    /**
     * Rollback
     *
     * @throws RuntimeException
     */
    void insertWithRollback() throws RuntimeException;

    /**
     * Rollback failed
     *
     * @throws RuntimeException
     */
    void invokeSelfWithThis() throws RuntimeException;

    /**
     * Rollback successful
     *
     * @throws RuntimeException
     */
    void invokeSelfWithProxy() throws RuntimeException;
}
