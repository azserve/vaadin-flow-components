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
package com.vaadin.flow.component.grid.it;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.bean.Person;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.Route;

@Route(value = "vaadin-grid/beangridpage")
public class BeanGridPage extends Div {

    public BeanGridPage() {
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(new Person("Jorma", 2018), new Person("Jarvi", 33));
        grid.setItemDetailsRenderer(TemplateRenderer
                .<Person> of("<div>[[item.name]] [[item.age]]</div>")
                .withProperty("name", Person::getFirstName)
                .withProperty("age", Person::getAge));

                grid.getColumnByKey("firstName").setTooltipTextGenerator(person -> "First name of the person is " + person.getFirstName());
        add(grid);
    }

}
