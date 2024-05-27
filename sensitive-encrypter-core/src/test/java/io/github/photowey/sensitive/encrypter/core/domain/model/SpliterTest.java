/*
 * Copyright © 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.photowey.sensitive.encrypter.core.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * {@code SpliterTest}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/05/28
 */
class SpliterTest {

    @Test
    void testSplit() {
        String phoneNumber = "13112345678";
        int segmentLength = 3;

        Spliter spliter = Spliter.builder()
                .sensitive(phoneNumber)
                .length(segmentLength)
                .build();

        List<String> segments = spliter.split();

        Assertions.assertEquals(9, segments.size());
        Assertions.assertEquals("131", segments.get(0));
        Assertions.assertEquals("311", segments.get(1));
        Assertions.assertEquals("112", segments.get(2));
        Assertions.assertEquals("123", segments.get(3));
        Assertions.assertEquals("234", segments.get(4));
        Assertions.assertEquals("345", segments.get(5));
        Assertions.assertEquals("456", segments.get(6));
        Assertions.assertEquals("567", segments.get(7));
        Assertions.assertEquals("678", segments.get(8));
    }

    @Test
    void testSplit_padding() {
        String phoneNumber = "13";
        int segmentLength = 3;

        Spliter spliter = Spliter.builder()
                .sensitive(phoneNumber)
                .length(segmentLength)
                .build();

        List<String> segments = spliter.split();

        Assertions.assertEquals(1, segments.size());
        Assertions.assertEquals("130", segments.get(0));
    }
}