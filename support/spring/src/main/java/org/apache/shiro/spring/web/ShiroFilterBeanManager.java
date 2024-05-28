/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.shiro.spring.web;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.config.ShiroFilterConfiguration;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.InvalidRequestFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

import javax.servlet.Filter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Silence_Lurker
 */
public class ShiroFilterBeanManager {
    private SecurityManager securityManager;

    private Map<String, Filter> filters;

    private List<String> globalFilters;

    // urlPathExpression_to_comma-delimited-filter-chain-definition
    private Map<String, String> filterChainDefinitionMap;

    private String loginUrl;
    private String successUrl;
    private String unauthorizedUrl;

    private AbstractShiroFilter instance;

    private ShiroFilterConfiguration filterConfiguration;

    {
        setFilters(new LinkedHashMap<String, Filter>());
        List<String> globalFilter = new ArrayList<>();
        globalFilter.add(DefaultFilter.invalidRequest.name());
        setGlobalFilters(globalFilter);
        // order matters!
        setFilterChainDefinitionMap(new LinkedHashMap<String, String>());
        setShiroFilterConfiguration(new ShiroFilterConfiguration());
    }

    public AbstractShiroFilter getInstance() {
        return instance;
    }

    public void setInstance(AbstractShiroFilter instance) {
        this.instance = instance;
    }

    public List<String> getGlobalFilters() {
        return globalFilters;
    }

    /**
     * Gets the application {@code SecurityManager} instance to be used by the
     * constructed Shiro Filter. This is a
     * required property - failure to set it will throw an initialization exception.
     *
     * @return the application {@code SecurityManager} instance to be used by the
     *         constructed Shiro Filter.
     */
    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    /**
     * Sets the application {@code SecurityManager} instance to be used by the
     * constructed Shiro Filter. This is a
     * required property - failure to set it will throw an initialization exception.
     *
     * @param securityManager the application {@code SecurityManager} instance to be
     *                        used by the constructed Shiro Filter.
     */
    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    /**
     * Gets the application {@code ShiroFilterConfiguration} instance to be used by
     * the constructed Shiro Filter.
     *
     * @return the application {@code ShiroFilterConfiguration} instance to be used
     *         by the constructed Shiro Filter.
     */
    public ShiroFilterConfiguration getShiroFilterConfiguration() {
        return filterConfiguration;
    }

    /**
     * Sets the application {@code ShiroFilterConfiguration} instance to be used by
     * the constructed Shiro Filter.
     *
     * @param filterConfiguration the application {@code SecurityManager} instance
     *                            to be used by the constructed Shiro Filter.
     */
    public void setShiroFilterConfiguration(ShiroFilterConfiguration filterConfiguration) {
        this.filterConfiguration = filterConfiguration;
    }

    /**
     * Returns the application's login URL to be assigned to all acquired Filters
     * that subclass
     * {@link AccessControlFilter} or {@code null} if no value should be assigned
     * globally. The default value
     * is {@code null}.
     *
     * @return the application's login URL to be assigned to all acquired Filters
     *         that subclass
     *         {@link AccessControlFilter} or {@code null} if no value should be
     *         assigned globally.
     * @see #setLoginUrl
     */
    public String getLoginUrl() {
        return loginUrl;
    }

    /**
     * Sets the application's login URL to be assigned to all acquired Filters that
     * subclass
     * {@link AccessControlFilter}. This is a convenience mechanism: for all
     * configured {@link #setFilters filters},
     * as well for any default ones ({@code authc}, {@code user}, etc.), this value
     * will be passed on to each Filter
     * via the {@link AccessControlFilter#setLoginUrl(String)} method<b>*</b>. This
     * eliminates the need to
     * configure the 'loginUrl' property manually on each filter instance, and
     * instead that can be configured once
     * via this attribute.
     * <p/>
     * <b>*</b>If a filter already has already been explicitly configured with a
     * value, it will
     * <em>not</em> receive this value. Individual filter configuration overrides
     * this global convenience property.
     *
     * @param loginUrl the application's login URL to apply to as a convenience to
     *                 all discovered
     *                 {@link AccessControlFilter} instances.
     * @see AccessControlFilter#setLoginUrl(String)
     */
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    /**
     * Returns the application's after-login success URL to be assigned to all
     * acquired Filters that subclass
     * {@link AuthenticationFilter} or {@code null} if no value should be assigned
     * globally. The default value
     * is {@code null}.
     *
     * @return the application's after-login success URL to be assigned to all
     *         acquired Filters that subclass
     *         {@link AuthenticationFilter} or {@code null} if no value should be
     *         assigned globally.
     * @see #setSuccessUrl
     */
    public String getSuccessUrl() {
        return successUrl;
    }

    /**
     * Sets the application's after-login success URL to be assigned to all acquired
     * Filters that subclass
     * {@link AuthenticationFilter}. This is a convenience mechanism: for all
     * configured {@link #setFilters filters},
     * as well for any default ones ({@code authc}, {@code user}, etc.), this value
     * will be passed on to each Filter
     * via the {@link AuthenticationFilter#setSuccessUrl(String)} method<b>*</b>.
     * This eliminates the need to
     * configure the 'successUrl' property manually on each filter instance, and
     * instead that can be configured once
     * via this attribute.
     * <p/>
     * <b>*</b>If a filter already has already been explicitly configured with a
     * value, it will
     * <em>not</em> receive this value. Individual filter configuration overrides
     * this global convenience property.
     *
     * @param successUrl the application's after-login success URL to apply to as a
     *                   convenience to all discovered
     *                   {@link AccessControlFilter} instances.
     * @see AuthenticationFilter#setSuccessUrl(String)
     */
    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    /**
     * Returns the application's after-login success URL to be assigned to all
     * acquired Filters that subclass
     * {@link AuthenticationFilter} or {@code null} if no value should be assigned
     * globally. The default value
     * is {@code null}.
     *
     * @return the application's after-login success URL to be assigned to all
     *         acquired Filters that subclass
     *         {@link AuthenticationFilter} or {@code null} if no value should be
     *         assigned globally.
     * @see #setSuccessUrl
     */
    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    /**
     * Sets the application's 'unauthorized' URL to be assigned to all acquired
     * Filters that subclass
     * {@link AuthorizationFilter}. This is a convenience mechanism: for all
     * configured {@link #setFilters filters},
     * as well for any default ones ({@code roles}, {@code perms}, etc.), this value
     * will be passed on to each Filter
     * via the {@link AuthorizationFilter#setUnauthorizedUrl(String)}
     * method<b>*</b>. This eliminates the need to
     * configure the 'unauthorizedUrl' property manually on each filter instance,
     * and instead that can be configured once
     * via this attribute.
     * <p/>
     * <b>*</b>If a filter already has already been explicitly configured with a
     * value, it will
     * <em>not</em> receive this value. Individual filter configuration overrides
     * this global convenience property.
     *
     * @param unauthorizedUrl the application's 'unauthorized' URL to apply to as a
     *                        convenience to all discovered
     *                        {@link AuthorizationFilter} instances.
     * @see AuthorizationFilter#setUnauthorizedUrl(String)
     */
    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    /**
     * Returns the filterName-to-Filter map of filters available for reference when
     * defining filter chain definitions.
     * All filter chain definitions will reference filters by the names in this map
     * (i.e. the keys).
     *
     * @return the filterName-to-Filter map of filters available for reference when
     *         defining filter chain definitions.
     */
    public Map<String, Filter> getFilters() {
        return filters;
    }

    /**
     * Sets the filterName-to-Filter map of filters available for reference when
     * creating
     * {@link #setFilterChainDefinitionMap(java.util.Map) filter chain definitions}.
     * <p/>
     * <b>Note:</b> This property is optional: this {@code FactoryBean}
     * implementation will discover all beans in the
     * web application context that implement the {@link Filter} interface and
     * automatically add them to this filter
     * map under their bean name.
     * <p/>
     * For example, just defining this bean in a web Spring XML application context:
     *
     * <pre>
     * &lt;bean id=&quot;myFilter&quot; class=&quot;com.class.that.implements.javax.servlet.Filter&quot;&gt;
     * ...
     * &lt;/bean&gt;
     * </pre>
     *
     * Will automatically place that bean into this Filters map under the key
     * '<b>myFilter</b>'.
     *
     * @param filters the optional filterName-to-Filter map of filters available for
     *                reference when creating
     *                {@link #setFilterChainDefinitionMap (java.util.Map) filter
     *                chain definitions}.
     */
    public void setFilters(Map<String, Filter> filters) {
        this.filters = filters;
    }

    /**
     * Returns the chainName-to-chainDefinition map of chain definitions to use for
     * creating filter chains intercepted
     * by the Shiro Filter. Each map entry should conform to the format defined by
     * the
     * {@link FilterChainManager#createChain(String, String)} JavaDoc, where the map
     * key is the chain name (e.g. URL
     * path expression) and the map value is the comma-delimited string chain
     * definition.
     *
     * @return he chainName-to-chainDefinition map of chain definitions to use for
     *         creating filter chains intercepted
     *         by the Shiro Filter.
     */
    public Map<String, String> getFilterChainDefinitionMap() {
        return filterChainDefinitionMap;
    }

    /**
     * Sets the chainName-to-chainDefinition map of chain definitions to use for
     * creating filter chains intercepted
     * by the Shiro Filter. Each map entry should conform to the format defined by
     * the
     * {@link FilterChainManager#createChain(String, String)} JavaDoc, where the map
     * key is the chain name (e.g. URL
     * path expression) and the map value is the comma-delimited string chain
     * definition.
     *
     * @param filterChainDefinitionMap the chainName-to-chainDefinition map of chain
     *                                 definitions to use for creating
     *                                 filter chains intercepted by the Shiro
     *                                 Filter.
     */
    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        this.filterChainDefinitionMap = filterChainDefinitionMap;
    }

    /**
     * Sets the list of filters that will be executed against every request.
     * Defaults to the {@link InvalidRequestFilter} which will block known invalid
     * request attacks.
     *
     * @param globalFilters the list of filters to execute before specific path
     *                      filters.
     */
    public void setGlobalFilters(List<String> globalFilters) {
        this.globalFilters = globalFilters;
    }

    /**
     * 比较并合并两个 ShiroFilterBeanManager 实例
     */
    public void mergeWith(ShiroFilterBeanManager other) {
        mergeSecurityManager(other);
        mergeFilters(other);
        mergeGlobalFilters(other);
        mergeFilterChainDefinitionMap(other);
        mergeUrls(other);
        mergeInstance(other);
        mergeShiroFilterConfiguration(other);
    }

    private void mergeSecurityManager(ShiroFilterBeanManager other) {
        if (other.getSecurityManager() != null) {
            this.setSecurityManager(other.getSecurityManager());
            System.out.println("Replace security manager");
        }
    }

    private void mergeFilters(ShiroFilterBeanManager other) {
        if (other.getFilters() != null && !other.getFilters().isEmpty()) {
            this.setFilters(other.getFilters());
            System.out.println("Replace Filters");
        }
    }

    private void mergeGlobalFilters(ShiroFilterBeanManager other) {
        if (other.getGlobalFilters() != null && !other.getGlobalFilters().isEmpty()) {
            this.setGlobalFilters(other.getGlobalFilters());
            System.out.println("Replace Global Filters");
        }
    }

    private void mergeFilterChainDefinitionMap(ShiroFilterBeanManager other) {
        if (other.getFilterChainDefinitionMap() != null && !other.getFilterChainDefinitionMap().isEmpty()) {
            this.setFilterChainDefinitionMap(other.getFilterChainDefinitionMap());
            System.out.println("Replace map");
        }
    }

    private void mergeUrls(ShiroFilterBeanManager other) {
        if (other.getLoginUrl() != null) {
            this.setLoginUrl(other.getLoginUrl());
            System.out.println("Replace Login Url");
        }
        if (other.getSuccessUrl() != null) {
            this.setSuccessUrl(other.getSuccessUrl());
            System.out.println("Replace Success Url");
        }
        if (other.getUnauthorizedUrl() != null) {
            this.setUnauthorizedUrl(other.getUnauthorizedUrl());
            System.out.println("Replace Unau URL");
        }
    }

    private void mergeInstance(ShiroFilterBeanManager other) {
        if (other.getInstance() != null) {
            this.setInstance(other.getInstance());
            System.out.println("Replace Instance");
        }
    }

    private void mergeShiroFilterConfiguration(ShiroFilterBeanManager other) {
        if (other.getShiroFilterConfiguration() != null) {
            this.setShiroFilterConfiguration(other.getShiroFilterConfiguration());
            System.out.println("Replace Config");
        }
    }
}
