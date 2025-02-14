package com.vaadin.flow.component.spreadsheet.test;

import com.vaadin.flow.component.spreadsheet.testbench.SpreadsheetElement;
import com.vaadin.flow.component.spreadsheet.tests.fixtures.TestFixtures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RowHeaderDoubleClickIT extends AbstractSpreadsheetIT {

    @Before
    public void init() {
        getDriver().get(getBaseURL());
        createNewSpreadsheet();
    }

    @Test
    public void loadFixture_doubleClickOnRowHeader_rowHeaderDoubleClickEventFired() {
        loadTestFixture(TestFixtures.RowHeaderDoubleClick);

        final SpreadsheetElement spreadsheet = $(SpreadsheetElement.class)
                .first();

        spreadsheet.getRowHeader(3).getResizeHandle().doubleClick();

        Assert.assertEquals("Double-click on row header",
                getCellAt(1, 3).getValue());
    }

}
