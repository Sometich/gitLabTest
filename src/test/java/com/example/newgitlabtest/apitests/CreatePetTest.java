package com.example.newgitlabtest.apitests;

import com.example.newgitlabtest.configure.TestsConfigure;
import com.example.newgitlabtest.pojo.Category;
import com.example.newgitlabtest.pojo.Pet;
import com.example.newgitlabtest.pojo.TagsItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

public final class CreatePetTest {
    private static final int MAGIC_NUMBER_ONE = 0;
    private long deleteID;
    private Pet pet;
    private static final PetApi CONFIGURE = TestsConfigure.feignSettings();

    @AfterEach
    public void deletePet() {
        CONFIGURE.deletePet(deleteID);
    }

    @BeforeEach
    public void resetPet() {
        pet = new Pet(List.of("string"),
                "alex", 1,
                new Category("string", 1),
                List.of(new TagsItem("string", 0)),
                "available");
    }

    /**
     * Проверка получения ответа со сгенерированным ID при отправке валидных данных с ID=0.
     */
    @Test
    public void validateGenerateIdIfIdIsZero() {
        pet.setId(MAGIC_NUMBER_ONE);
        Pet responsePet = CONFIGURE.addPet(pet);
        deleteID = responsePet.getId();
        Assertions.assertTrue(responsePet.getId() != 0,
                "ID равен 0, а значит он не установил ID автоматически");
    }

    /**
     * Проверка получения ответа с ID равным тому который был указан при отправке валидных данных отличного от 0.
     */
    @Test
    public void checkIdThatWasSent() {
        Pet responsePet = CONFIGURE.addPet(pet);
        deleteID = responsePet.getId();
        Assertions.assertEquals(pet.getId(), responsePet.getId(),
                "ID не одинаковые, а значит ручная установка ID работает не корректно");
    }

    /**
     * Проверка получения статуса unavailable при отправке валидного запроса со значением поля status = ”unavailable”.
     */
    @Test
    public void checkUnavailableStatus() {
        pet.setStatus("unavailable");
        Pet responsePet = CONFIGURE.addPet(pet);
        deleteID = responsePet.getId();
        Assertions.assertEquals(pet.getStatus(), responsePet.getStatus(),
                "Статусы не соответствует отправленному и сервис некорректно его установил");
    }

    /**
     * Проверка получения 200 кода при отправке валидных данных.
     */
    @Test
    public void checkCode200() {
        deleteID = pet.getId();
        Assertions.assertEquals(TestsConfigure.OK, TestsConfigure.feignSettings().getAddStatusCodePet(pet).status(),
                "Статус код не равен 200");
    }

    /**
     * Проверка корректного ответа при отправке кириллицы в качестве значения поля name.
     */
    @Test
    public void checkValidResponseWithKirilliza() {
        pet.setName("Алексей");
        Pet responsePet = CONFIGURE.addPet(pet);
        deleteID = responsePet.getId();
        Assertions.assertEquals(pet.getName(), responsePet.getName(), "Имя отправленное на сервер не было установлено");
    }
}
