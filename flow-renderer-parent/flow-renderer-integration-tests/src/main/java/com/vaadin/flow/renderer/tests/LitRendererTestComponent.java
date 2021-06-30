/*
 * Copyright 2000-2021 Vaadin Ltd.
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
package com.vaadin.flow.renderer.tests;

import java.util.Collection;
import java.util.List;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.HasDataProvider;
import com.vaadin.flow.data.provider.ArrayUpdater;
import com.vaadin.flow.data.provider.CompositeDataGenerator;
import com.vaadin.flow.data.provider.DataCommunicator;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.internal.JsonUtils;
import com.vaadin.flow.renderer.LitRenderer;
import com.vaadin.flow.shared.Registration;

import elemental.json.JsonValue;

@Tag("lit-renderer-test-component")
@JsModule("lit-renderer-test-component.ts")
public class LitRendererTestComponent extends Div
        implements HasDataProvider<String> {

    private DataCommunicator<String> dataCommunicator;
    private final CompositeDataGenerator<String> dataGenerator = new CompositeDataGenerator<>();

    private Registration rendererRegistration;
    private Registration detailsRendererRegistration;

    private final ArrayUpdater arrayUpdater = new ArrayUpdater() {
        @Override
        public Update startUpdate(int sizeChange) {
            return new Update() {
                @Override
                public void clear(int start, int length) {
                }

                @Override
                public void set(int start, List<JsonValue> items) {
                    getElement().executeJs("this.items = $0",
                            items.stream().collect(JsonUtils.asArray()));
                }

                @Override
                public void commit(int updateId) {
                }
            };
        }

        @Override
        public void initialize() {
        }
    };

    public LitRendererTestComponent() {
        dataCommunicator = new DataCommunicator<String>(dataGenerator,
                arrayUpdater, data -> {
                }, getElement().getNode());
    }

    public void setRenderer(LitRenderer<String> renderer) {
        if (rendererRegistration != null) {
            rendererRegistration.remove();
            rendererRegistration = null;
        }

        if (renderer == null) {
            getElement().executeJs("this.renderer = undefined");
        } else {
            rendererRegistration = renderer.prepare(getElement(),
                    dataCommunicator.getKeyMapper(), dataGenerator);
            dataCommunicator.reset();
        }
    }

    public void setDetailsRenderer(LitRenderer<String> renderer) {
        if (detailsRendererRegistration != null) {
            detailsRendererRegistration.remove();
            detailsRendererRegistration = null;
        }

        if (renderer == null) {
            getElement().executeJs("this.detailsRenderer = undefined");
        } else {
            detailsRendererRegistration = renderer.prepare(getElement(),
                    dataCommunicator.getKeyMapper(), dataGenerator, "detailsRenderer");
            dataCommunicator.reset();
        }
    }

    @Override
    public void setItems(Collection<String> items) {
        HasDataProvider.super.setItems(items);
        dataCommunicator.setRequestedRange(0, items.size());
    }

    @Override
    public void setDataProvider(DataProvider<String, ?> dataProvider) {
        dataCommunicator.setDataProvider(dataProvider, null);
    }

}
