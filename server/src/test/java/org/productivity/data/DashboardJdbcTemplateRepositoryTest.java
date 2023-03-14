package org.productivity.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.productivity.models.Dashboard;
import org.productivity.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DashboardJdbcTemplateRepositoryTest {

    @Autowired
    DashboardJdbcTemplateRepository dashboardJdbcTemplateRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findByDashboardId() {
        Dashboard dashboard = dashboardJdbcTemplateRepository.findByDashboardId(1);

        assertEquals(1, dashboard.getDashboardId());
        assertEquals("Test Dashboard One", dashboard.getDashboardName());
    }

    @Test
    void createDashboard() {
        // Arrange
        Dashboard dashboard = new Dashboard();
        dashboard.setDashboardName("Test Dashboard Name");
        dashboard.setUserId(1);

        // Act
        Dashboard actualDashboard = dashboardJdbcTemplateRepository.createDashboard(dashboard);

        //Assert
        assertNotNull(actualDashboard);
        assertEquals(dashboard.getDashboardName(), "Test Dashboard Name");
    }

    @Test
    void updateDashboard() {
        Dashboard actual = dashboardJdbcTemplateRepository.findByDashboardId(1);

        actual.setDashboardName("New Dashboard Name");

        assertTrue(dashboardJdbcTemplateRepository.updateDashboard(actual));
        assertNotNull(dashboardJdbcTemplateRepository.findByDashboardId(1));
        assertEquals("New Dashboard Name", dashboardJdbcTemplateRepository.findByDashboardId(1).getDashboardName());
    }
}