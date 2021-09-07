package fa.appcode.web.service.impl;

import fa.appcode.web.commons.exception.DateRangeException;
import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.commons.utility.CONSTANT;
import fa.appcode.web.entities.Promotion;
import fa.appcode.web.repository.PromotionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PromotionServiceImplTest {

    @Mock
    PromotionRepository promotionRepository;

    @InjectMocks
    PromotionServiceImpl promotionService;

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeEach
    public void setUp() throws ParseException {
        System.out.println("set up");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveWithValidData() throws ParseException {
        Promotion expected = new Promotion("aaa", "aaaaa", dateFormat.parse("01/02/2020"), dateFormat.parse("01/02/2020"),
                BigDecimal.valueOf(10));
        Mockito.when(promotionRepository.save(expected)).thenReturn(expected);
        Promotion actual = promotionService.save(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void testSaveWithNullDate() {
        Promotion mPromotion = Mockito.mock(Promotion.class);
        mPromotion.setStartTime(null);
        mPromotion.setEndTime(null);
        assertThrows(NullPointerException.class,
                () -> promotionService.save(mPromotion));
    }

    @Test
    public void testSaveWithDateException() throws ParseException {
        Promotion promotion = new Promotion();
        promotion.setTitle("111");
        promotion.setDiscountLevel(BigDecimal.valueOf(10));
        promotion.setStartTime(dateFormat.parse("10/02/2020"));
        promotion.setEndTime(dateFormat.parse("05/01/2020"));
        Mockito.when(promotionRepository.save(promotion)).thenReturn(Mockito.mock(Promotion.class));
        Exception exception = assertThrows(DateRangeException.class, () -> promotionService.save(promotion));
        String expectedMsg = CONSTANT.DATE_RANGE_NOT_VALID;
        String actualMsg = exception.getMessage();
        Assertions.assertTrue(actualMsg.contains(expectedMsg));
    }

    @Test
    public void testFindByTitleWithExistingTitle() {
        String title = "kkk";
        Page<Promotion> expected = Mockito.mock(Page.class);
        Mockito.when(promotionRepository.findByTitleLike(title, PageRequest.of(0, 5))).thenReturn(expected);
        Page<Promotion> actual = promotionService.getByPageAndTitle(0, 5, title);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindByTitleWithNotExistingTitle() {
        String title = "123";
        Page<Promotion> expected = new PageImpl<>(Collections.emptyList());
        Mockito.when(promotionRepository.findByTitleLike(title, PageRequest.of(0, 5))).thenReturn(expected);
        Page<Promotion> actual = promotionService.getByPageAndTitle(0, 5, title);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindByTitleWithEmptyTitle() {
        String title = "";
        List<Promotion> mockedList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mockedList.add(Mockito.mock(Promotion.class));
        }
        Page<Promotion> expected = new PageImpl<>(mockedList);
        Mockito.when(promotionRepository.findByTitleLike(title, PageRequest.of(0, 5))).thenReturn(expected);
        Page<Promotion> actual = promotionService.getByPageAndTitle(0, 5, title);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteWithExistingId() {
        int id = 1;
        boolean expected = true;
        Mockito.when(promotionRepository.findById(id)).thenReturn(Optional.of(Mockito.mock(Promotion.class)));
        boolean actual = promotionService.delete(id);
        assertEquals(expected, actual);
    }


    @Test
    public void testDeleteWithNotExistingId() {
        Integer id = 0;
        Mockito.when(promotionRepository.findById(id)).thenReturn(Optional.empty());
        Exception exception = assertThrows(NotFoundException.class, () -> promotionService.delete(id));
//        Mockito.verify(promotionRepository).deleteById(0);
        String expectedMsg = CONSTANT.PROMOTION_NOT_FOUND;
        String actualMsg = exception.getMessage();
        assertTrue(actualMsg.contains(expectedMsg));
    }


    @Test
    public void testFindByIdWithExistingId() {
        int id = 1;
        Promotion expected = Mockito.mock(Promotion.class);
        Mockito.when(promotionRepository.findById(id)).thenReturn(Optional.of(expected));
        Promotion actual = promotionService.getById(id);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByIdWithNotExistingId() {
        int id = 1;
        Promotion expected = null;
        Mockito.when(promotionRepository.findById(id)).thenReturn(Optional.ofNullable(expected));
        Promotion actual = promotionService.getById(id);
        assertEquals(actual, expected);
    }
}