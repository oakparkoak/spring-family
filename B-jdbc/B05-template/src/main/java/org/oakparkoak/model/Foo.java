package org.oakparkoak.model;

import lombok.Builder;
import lombok.Data;

/**
 * @package: org.oakparkoak.model
 * @author: Captain
 * @time: 3/6/2021 7:42 PM
 */
@Data
@Builder
public class Foo {
    private Long id;

    private String name;
}
