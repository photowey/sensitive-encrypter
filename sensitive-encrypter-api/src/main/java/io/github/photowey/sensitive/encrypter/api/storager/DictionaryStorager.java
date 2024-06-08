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
package io.github.photowey.sensitive.encrypter.api.storager;

import io.github.photowey.sensitive.encrypter.core.pair.DictionaryPair;
import io.github.photowey.spring.infras.core.strategy.OrderedBeanFactoryStrategySupporter;

import java.util.Objects;

/**
 * {@code DictionaryStorager}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/06/08
 */
public interface DictionaryStorager extends OrderedBeanFactoryStrategySupporter {

    /**
     * Store
     * |- KEY -> VALUE
     * |- 000 -> www
     * |- 001 -> xxx
     * |- ... -> yyy
     * |- 999 -> zzz
     * |- --------------------------------
     * |- 0000 -> www
     * |- 0001 -> xxx
     * |- ... -> yyy
     * |- 9999 -> zzz
     *
     * @param segment   the segment(KEY) of the dictionary.
     * @param encrypted the encrypted(VALUE) of dictionary.
     */
    void store(String segment, String encrypted);

    default void store(DictionaryPair pair) {
        Objects.requireNonNull(pair);
        Objects.requireNonNull(pair.segment());
        Objects.requireNonNull(pair.encrypted());

        this.store(pair.segment(), pair.encrypted());
    }

    // ----------------------------------------------------------------

    /**
     * Load encrypted(VALUE) by segment(KEY).
     *
     * @param segment the segment of the dictionary.
     * @return the encrypted of dictionary.
     */
    String load(String segment);

    /**
     * Load segment(KEY) by encrypted(VALUE).
     *
     * @param encrypted the encrypted(VALUE) of dictionary.
     * @return the segment(KEY) of the dictionary.
     */
    String reverseLoad(String encrypted);
}
