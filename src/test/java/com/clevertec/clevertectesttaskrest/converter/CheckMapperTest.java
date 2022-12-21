package com.clevertec.clevertectesttaskrest.converter;

import com.clevertec.clevertectesttaskrest.domain.Check;
import com.clevertec.clevertectesttaskrest.domain.CheckItem;
import com.clevertec.clevertectesttaskrest.domain.DiscountCard;
import com.clevertec.clevertectesttaskrest.domain.Product;
import com.clevertec.clevertectesttaskrest.web.model.CheckModel;
import com.clevertec.clevertectesttaskrest.web.model.converter.CheckMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;


public class CheckMapperTest {
    private final CheckMapper checkMapper = CheckMapper.INSTANCE;

    @Test
    public void toModel_shouldConvertToCheckModel() {
        Check check = new Check();
        Product product = Mockito.mock(Product.class);
        CheckItem checkItem = Mockito.mock(CheckItem.class);
        DiscountCard discountCard = Mockito.mock(DiscountCard.class);
        Set<CheckItem> checkItems = Collections.singleton(checkItem);
        check.setId(1L);
        check.setTotalPrice(BigDecimal.valueOf(123));
        check.setCheckItems(checkItems);
        check.setDiscountCard(discountCard);

        CheckModel checkModel = checkMapper.toModel(check);

        Assertions.assertEquals(checkModel.getId(), check.getId());
        Assertions.assertEquals(checkModel.getTotalPrice(), check.getTotalPrice());
        Assertions.assertNotNull(checkModel.getItems());
        Assertions.assertNotNull(checkModel.getDiscountCard());
        Assertions.assertEquals(checkModel.getItems().size(), check.getCheckItems().size());
    }
}
