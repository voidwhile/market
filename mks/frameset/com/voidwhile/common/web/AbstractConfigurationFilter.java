package com.voidwhile.common.web;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;

import org.apache.commons.lang3.StringUtils;

import com.voidwhile.core.utils.Slf4JLogger;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 提供获取初始化配置参数的抽象过滤器
 * @author: xiaowei
 * @Create Date: 2014年10月24日 下午1:48:07
 *
 * @Version: v1.0
 */
public abstract class AbstractConfigurationFilter implements Filter {

    protected final Slf4JLogger logger = Slf4JLogger.getLogger(getClass());

    private boolean ignoreInitConfiguration = false;

    /**
     * Retrieves the property from the FilterConfig.  First it checks the FilterConfig's initParameters to see if it
     * has a value.
     * If it does, it returns that, otherwise it retrieves the ServletContext's initParameters and returns that value if any.
     * Essentially the documented order is:
     * <ol>
     *     <li>FilterConfig.getInitParameter</li>
     *     <li>ServletContext.getInitParameter</li>
     *     <li>Default Value</li>
     * </ol>
     *
     *
     * @param filterConfig the Filter Configuration.
     * @param propertyName the property to retrieve.
     * @param defaultValue the default value if the property is not found.
     * @return the property value, following the above conventions.  It will always return the more specific value (i.e.
     *  filter vs. context).
     */
    protected final String getPropertyFromInitParams(final FilterConfig filterConfig, final String propertyName,
            final String defaultValue) {
        final String value = filterConfig.getInitParameter(propertyName);

        if (StringUtils.isNotBlank(value)) {
            logger.info("Property [{}] loaded from FilterConfig.getInitParameter with value [{}]", propertyName, value);
            return value;
        }

        final String value2 = filterConfig.getServletContext().getInitParameter(propertyName);

        if (StringUtils.isNotBlank(value2)) {
            logger.info("Property [{}] loaded from ServletContext.getInitParameter with value [{}]", propertyName,
                    value2);
            return value2;
        }
        return defaultValue;
    }

    protected final boolean parseBoolean(final String value) {
        return ((value != null) && value.equalsIgnoreCase("true"));
    }

    protected final String loadFromContext(final InitialContext context, final String path) {
        try {
            return (String) context.lookup(path);
        } catch (final NamingException e) {
            return null;
        }
    }

    public final void setIgnoreInitConfiguration(boolean ignoreInitConfiguration) {
        this.ignoreInitConfiguration = ignoreInitConfiguration;
    }

    protected final boolean isIgnoreInitConfiguration() {
        return this.ignoreInitConfiguration;
    }
}
