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
package org.apache.shiro.mgt;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceLock;

import static org.apache.shiro.test.AbstractShiroTest.GLOBAL_SECURITY_MANAGER_RESOURCE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @since May 8, 2008 12:26:23 AM
 */
@ResourceLock(GLOBAL_SECURITY_MANAGER_RESOURCE)
public class VMSingletonDefaultSecurityManagerTest {

    @BeforeEach
    public void setUp() {
        ThreadContext.remove();
    }

    @AfterEach
    public void tearDown() {
        ThreadContext.remove();
    }

    @Test
    void testVMSingleton() {
        DefaultSecurityManager sm = new DefaultSecurityManager();
        Ini ini = new Ini();
        Ini.Section section = ini.addSection(IniRealm.USERS_SECTION_NAME);
        section.put("guest", "guest");
        sm.setRealm(new IniRealm(ini));
        SecurityUtils.setSecurityManager(sm);

        try {
            Subject subject = SecurityUtils.getSubject();

            AuthenticationToken token = new UsernamePasswordToken("guest", "guest");
            subject.login(token);
            subject.getSession().setAttribute("key", "value");
            assertEquals("value", subject.getSession().getAttribute("key"));

            subject = SecurityUtils.getSubject();

            assertTrue(subject.isAuthenticated());
            assertEquals("value", subject.getSession().getAttribute("key"));
        } finally {
            sm.destroy();
            //SHIRO-270:
            SecurityUtils.setSecurityManager(null);
        }
    }
}
