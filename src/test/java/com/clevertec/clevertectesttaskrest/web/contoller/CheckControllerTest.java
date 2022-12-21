package com.clevertec.clevertectesttaskrest.web.contoller;

import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.service.CheckService;
import com.clevertec.clevertectesttaskrest.web.model.CheckModel;
import com.clevertec.clevertectesttaskrest.web.model.converter.CheckMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class CheckControllerTest {
    private static final Long ID = 1L;

    @Mock
    private CheckMapper checkMapper;
    @Mock
    private CheckService checkService;

    @InjectMocks
    private CheckController checkController;

    @Test
    void getChecks_shouldCallServiceOnce() {
        PageRequest pageRequest = PageRequest.of(1, 1);

        checkController.getChecks(pageRequest);

        Mockito.verify(checkService, Mockito.only()).getByPaging(pageRequest);
    }

    @Test
    void getCheck_shouldCallServiceOnce() {
        Check check = Mockito.mock(Check.class);
        Mockito.when(checkService.getById(ID)).thenReturn(check);

        CheckModel actual =  checkController.getCheck(ID);

        Assertions.assertNotNull(actual);
        Mockito.verify(checkService).getById(ID);
    }
}