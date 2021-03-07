package org.oakparkoak.custom;

import org.springframework.dao.DataAccessException;

/**
 * @package: org.oakparkoak.custom
 * @author: Captain
 * @time: 3/7/2021 10:55 AM
 * @see org.springframework.jdbc.support.CustomSQLErrorCodesTranslation
 */
public class OverrideException extends DataAccessException {
    private static final long serialVersionUID = 8016716318515185397L;

    public OverrideException(String msg) {
        super(msg);
    }

    public OverrideException(String msg, Throwable cause) {
        super("Msg: Override Exception!", cause);
    }
}
