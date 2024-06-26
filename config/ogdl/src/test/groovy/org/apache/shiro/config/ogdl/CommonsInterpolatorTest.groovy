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
package org.apache.shiro.config.ogdl

import org.apache.shiro.config.ogdl.CommonsInterpolator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Isolated

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertNull

/**
 * Tests for {@link org.apache.shiro.config.ogdl.CommonsInterpolator}.
 * @since 1.4
 */
@Isolated("System property usage")
class CommonsInterpolatorTest {

    @SuppressWarnings("unused")
    public final static String TEST_ME = "success";

    @Test
    void testBasicOperation() {

        def interpolator = new CommonsInterpolator();

        assertNull interpolator.interpolate(null);

        def sourceString = """
            \${os.name}
            \${foobar}
            \${const:org.apache.shiro.config.ogdl.CommonsInterpolatorTest.TEST_ME}
            Some other text
        """

        def expectedResult = """
            ${System.getProperty("os.name")}
            \${foobar}
            success
            Some other text
        """.toString()

        assertEquals expectedResult, interpolator.interpolate(sourceString)
    }
}
