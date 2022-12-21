package com.clevertec.clevertectesttaskrest.converter;

import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.web.model.CheckItemModel;
import com.clevertec.clevertectesttaskrest.web.model.converter.CheckItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class CheckItemMapperTest {
    private final CheckItemMapper checkItemMapper = CheckItemMapper.INSTANCE;

    @Test
    public void toModel_shouldConvertToModel() {
        CheckItem checkItem = Mockito.mock(CheckItem.class);

        CheckItemModel checkItemModel = checkItemMapper.toModel(checkItem);

        Assertions.assertEquals(checkItemModel.getId(), checkItem.getId());
        Assertions.assertEquals(checkItemModel.getQuantity(), checkItem.getQuantity());
    }
}
