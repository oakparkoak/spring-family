package oakparkoak.service;

/**
 * @package: oakparkoak.service
 * @author: Captain
 * @time: 3/7/2021 11:15 AM
 */
public interface IFooService {
    /**
     * Inner: Propagation
     *
     * @throws RuntimeException
     */
    void insertInner() throws RuntimeException;

    /**
     * Outer: Propagation
     *
     * @throws RuntimeException
     */
    void insertOuter() throws RuntimeException;
}
