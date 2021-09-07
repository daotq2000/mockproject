package fa.appcode.web.controller;

import fa.appcode.web.commons.exception.NotFoundException;
import fa.appcode.web.commons.utility.CONSTANT;
import fa.appcode.web.converter.PromotionConverter;
import fa.appcode.web.dto.PromotionDTO;
import fa.appcode.web.entities.Promotion;
import fa.appcode.web.service.PromotionService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PromotionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private PromotionService promotionService;
    @InjectMocks
    private PromotionController promotionController;
    private PromotionConverter promotionConverter = new PromotionConverter();
    static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static List<Promotion> mockedList = new ArrayList<>();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("setUpBeforeClass()");
        mockedList.add(new Promotion("aaa", "aaaaa", dateFormat.parse("01/02/2020"), dateFormat.parse("01/02/2020"), BigDecimal.valueOf(10)));
        mockedList.add(new Promotion("aab", "aabbb", dateFormat.parse("01/02/2020"), dateFormat.parse("01/02/2020"), BigDecimal.valueOf(10)));
        mockedList.add(new Promotion("aac", "aaacc", dateFormat.parse("01/02/2020"), dateFormat.parse("01/02/2020"), BigDecimal.valueOf(10)));
        mockedList.add(new Promotion("xxx", "xxxxx", dateFormat.parse("01/02/2020"), dateFormat.parse("01/02/2020"), BigDecimal.valueOf(10)));
        mockedList.add(new Promotion("xxy", "yyyyy", dateFormat.parse("01/02/2020"), dateFormat.parse("01/02/2020"), BigDecimal.valueOf(10)));
    }

    @Test
    public void testSaveWithValidData() throws Exception {
        Promotion promotion = new Promotion("aaa", "aaaaa", dateFormat.parse("03/10/2020"), dateFormat.parse("10/10/2020"),
                BigDecimal.valueOf(10));
        Mockito.when(promotionService.save(promotion)).thenReturn(promotion);
        String promotionJson = "{\"title\":\"aaa\",\"startTime\":[2020,1,1],\"endTime\":[2021,1,1],\"discountLevel\":50}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/promotion/save")
                .accept(MediaType.APPLICATION_JSON).content(promotionJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String actual = response.getContentAsString();
        assertEquals(promotionJson, actual);
    }

    @Test
    public void testGetByTitleWithExistingTitle() {
        String title = "aaa";
        Page<Promotion> expected = new PageImpl<>(mockedList);
        Mockito.when(promotionService.getByPageAndTitle(0, 5, title)).thenReturn(expected);
        Page<PromotionDTO> promotionDTOs = promotionController.list(0, 5, title);
        Page<Promotion> actual = promotionDTOs.map(promotionDTO -> promotionConverter.convertToEntity(promotionDTO));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByTitleWithNotExistingTitle() {
        String title = "123";
        Page<Promotion> expected = new PageImpl<>(Collections.emptyList());
        Mockito.when(promotionService.getByPageAndTitle(0, 5, title)).thenReturn(expected);
        Page<PromotionDTO> promotionDTOs = promotionController.list(0, 5, title);
        Page<Promotion> actual = promotionDTOs.map(promotionDTO -> promotionConverter.convertToEntity(promotionDTO));
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByTitleWithEmptyTitle() {
        String title = "";
        Page<Promotion> expected = new PageImpl<>(mockedList);
        Mockito.when(promotionService.getByPageAndTitle(0, 5, title)).thenReturn(expected);
        Page<PromotionDTO> promotionDTOs = promotionController.list(0, 5, title);
        Page<Promotion> actual = promotionDTOs.map(promotionDTO -> promotionConverter.convertToEntity(promotionDTO));
        assertEquals(expected, actual);
    }

    @Test
    public void testFindByIdWithExistingId() {
        int id = 1;
        Promotion expected = Mockito.mock(Promotion.class);
        Mockito.when(promotionService.getById(id)).thenReturn(expected);
        PromotionDTO promotionDTO = promotionController.get(id);
        Promotion actual = promotionConverter.convertToEntity(promotionDTO);
        assertEquals(actual, expected);
    }

    @Test
    public void testFindByIdWithNotExistingId() {
        int id = 1;
        Mockito.when(promotionService.getById(id)).thenReturn(null);
        Exception exception = assertThrows(NotFoundException.class, () -> promotionController.get(id));
        String expectedMsg = CONSTANT.PROMOTION_NOT_FOUND;
        String actualMsg = exception.getMessage();
        assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void testDeleteWithExistingId() throws Exception {
        int id = 1;
        Mockito.when(promotionService.delete(id)).thenReturn(true);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/promotion/delete/" + id)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        int actual = response.getStatus();
        assertEquals(HttpStatus.OK, actual);
    }

    @Test
    public void testDeleteWithNotExistingId() throws Exception {
        int id = 1;
        Mockito.when(promotionService.delete(id)).thenReturn(false);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/promotion/delete/" + id)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        int actual = response.getStatus();
        assertEquals(HttpStatus.OK, actual);
    }
}
