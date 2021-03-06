/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.plugin.internal

import spock.lang.Specification

import static org.gradle.plugin.internal.PluginIds.isQualified
import static org.gradle.plugin.internal.PluginIds.qualifyIfUnqualified

class PluginIdsTest extends Specification {

    def "test validation matcher"() {
        expect:
        PluginIds.INVALID_PLUGIN_ID_CHAR_MATCHER.indexIn(input) == index

        where:
        input     | index
        "foo"     | -1
        "f o"     | 1
        "ab_c"    | 2
        "foo.bür" | 5
        "123"     | -1
        "FOO.bar" | -1
    }

    def "is qualified"() {
        expect:
        !isQualified("foo")
        isQualified("foo.bar")
    }

    def "qualify if unqualified"() {
        expect:
        qualifyIfUnqualified("foo", "bar") == "foo.bar"
        qualifyIfUnqualified("bar", "foo.bar") == "foo.bar"
    }

}
