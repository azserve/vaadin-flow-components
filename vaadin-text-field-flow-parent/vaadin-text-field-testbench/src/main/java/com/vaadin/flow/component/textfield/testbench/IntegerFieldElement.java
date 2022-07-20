/*
 * Copyright 2000-2022 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.textfield.testbench;

import org.openqa.selenium.By;

import java.util.Collections;

import com.vaadin.testbench.HasHelper;
import com.vaadin.testbench.HasLabel;
import com.vaadin.testbench.HasPlaceholder;
import com.vaadin.testbench.HasStringValueProperty;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

/**
 * A TestBench element representing a <code>&lt;vaadin-integer-field&gt;</code>
 * element.
 */
@Element("vaadin-integer-field")
public class IntegerFieldElement extends TestBenchElement
        implements HasStringValueProperty, HasLabel, HasPlaceholder, HasHelper {

    /**
     * Sets the given value for the slotted {@code input} element and then
     * triggers {@code input} and {@code change} DOM events.
     *
     * @param value
     *            the value to set.
     */
    public void setInputValue(String value) {
        TestBenchElement input = $("input").first();
        input.setProperty("value", value);
        input.dispatchEvent("input", Collections.singletonMap("bubbles", true));
        input.dispatchEvent("change",
                Collections.singletonMap("bubbles", true));
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        findElement(By.tagName("input")).sendKeys(keysToSend);
    }

}
