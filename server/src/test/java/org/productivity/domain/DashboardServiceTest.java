package org.productivity.domain;

import org.junit.jupiter.api.Test;
import org.productivity.data.DashboardJdbcTemplateRepository;
import org.productivity.models.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;




import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DashboardServiceTest {

    @MockBean
    DashboardJdbcTemplateRepository repository;

    @Autowired
    DashboardService service;

    @Test
    void shouldCreateDashboard() {
        Dashboard dashboard = createDashboard();
        Dashboard mockDashboard = createMockDashboard();

        when(repository.createDashboard(dashboard)).thenReturn(mockDashboard);

        Result<Dashboard> actual = service.createDashboard(dashboard);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockDashboard, actual.getPayload());
    }

    @Test
    void shouldNotCreateDashboardWithNullDashboard() {
        Dashboard dashboard = new Dashboard();
        Dashboard mockDashboard = new Dashboard();

        when(repository.createDashboard(dashboard)).thenReturn(mockDashboard);

        Result<Dashboard> actual = service.createDashboard(dashboard);

        assertEquals(ResultType.INVALID, actual.getType());
        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotCreateDashboardWithNullOrBlankDashboardName() {
        Dashboard dashboard = createDashboard();
        dashboard.setDashboardName(null);

        Result<Dashboard> actual = service.createDashboard(dashboard);

        assertEquals(ResultType.INVALID, actual.getType());
        assertEquals("Dashboard name is required.", actual.getMessages().get(0));
    }

    @Test
    void shouldNotCreateDashboardWithNonExistingUserId() {
        Dashboard dashboard = createDashboard();
        dashboard.setUserId(0);

        Result<Dashboard> actual = service.createDashboard(dashboard);

        assertEquals("User Id is required.", actual.getMessages().get(0));
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdateDashboardWithValidInputs() {
        Dashboard dashboard = createDashboard();
        when(repository.findByDashboardId(1)).thenReturn(dashboard);

        Dashboard updatedDashboard = new Dashboard(1, "Name", 1, null);

        when(repository.updateDashboard(updatedDashboard)).thenReturn(true);

        Result<Dashboard> actual = service.updateDashboard(updatedDashboard);

        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateDashboardWithInvalidInputs() {
        Dashboard dashboard = createDashboard();
        when(repository.findByDashboardId(1)).thenReturn(dashboard);
        dashboard.setDashboardId(999999);

        Result<Dashboard> updatedDashboard = service.updateDashboard(dashboard);

        assertEquals(ResultType.NOT_FOUND, updatedDashboard.getType());

    }

    private Dashboard createDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.setDashboardId(1);
        dashboard.setDashboardName("Dashboard Name");
        dashboard.setUserId(1);

        return dashboard;
    }

    private Dashboard createMockDashboard() {
        Dashboard dashboard = new Dashboard();
        dashboard.setDashboardId(2);
        dashboard.setDashboardName("Mock Dashboard Name");
        dashboard.setUserId(1);

        return dashboard;
    }
}