package org.oakparkoak.filter;

import java.util.List;
import java.util.Properties;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;

/**
 * META-INF/druid-filter.properties 
 * @see com.alibaba.druid.filter.FilterManager#loadFilterConfig()
 * @see com.alibaba.druid.filter.FilterManager#loadFilter(List, String) 
 *
 * @package: org.oakparkoak.filter
 * @author: Captain
 * @time: 2/8/2021 4:10 PM
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class ConnectionPrintFilter extends FilterEventAdapter {
    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        System.out.println("=== Before connect the connection ===");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        System.out.println("=== After connect the connection ===");
    }
}
