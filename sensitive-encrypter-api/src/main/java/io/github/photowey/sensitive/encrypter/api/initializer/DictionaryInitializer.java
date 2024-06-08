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
package io.github.photowey.sensitive.encrypter.api.initializer;

import io.github.photowey.sensitive.encrypter.core.constant.EncrypterConstants;
import io.github.photowey.sensitive.encrypter.core.pair.DictionaryPair;

import java.util.List;

/**
 * {@code DictionaryInitializer}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/06/08
 */
public interface DictionaryInitializer {

    default List<DictionaryPair> dictionary() {
        return this.dictionary(EncrypterConstants.DEFAULT_SEGMENT_LENGTH);
    }

    List<DictionaryPair> dictionary(int segmentLength);

    // ----------------------------------------------------------------

    default void init() {
        List<DictionaryPair> dictionary = this.dictionary();
        this.init(dictionary);
    }

    default void init(int segmentLength) {
        List<DictionaryPair> dictionary = this.dictionary(segmentLength);
        this.init(dictionary);
    }

    void init(List<DictionaryPair> pairs);
}
