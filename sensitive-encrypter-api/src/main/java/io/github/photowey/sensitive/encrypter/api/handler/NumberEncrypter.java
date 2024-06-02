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
package io.github.photowey.sensitive.encrypter.api.handler;

import io.github.photowey.spring.infras.core.strategy.OrderedBeanFactoryStrategySupporter;

/**
 * {@code NumberEncrypter}
 *
 * @author photowey
 * @version 1.0.0
 * @since 2024/06/02
 */
public interface NumberEncrypter extends OrderedBeanFactoryStrategySupporter {

    int ORDERED_STEP = 1000;
    String DEFAULT_PADDING_SEQUENCE = "0";

    String encrypt(String number);

    default boolean isNotEmpty(String sequence) {
        return !this.isEmpty(sequence);
    }

    default boolean isEmpty(String sequence) {
        return null == sequence || sequence.trim().isEmpty();
    }
}
