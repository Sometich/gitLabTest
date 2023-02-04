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

public final class UpdatePetTest {
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
                "alex",
                1,
                new Category("string", 1),
                List.of(new TagsItem("string", 0)),
                "available");
    }

    /**
     * Проверка получения 200 кода при попытке обновить данные по существующему ID в базе.
     */
    @Test
    public void checkCode200() {
        CONFIGURE.addPet(pet);
        pet.setStatus("unavailable");
        deleteID = pet.getId();
        Assertions.assertEquals(TestsConfigure.OK, TestsConfigure.feignSettings().getStatusUpdatePet(pet).status());
    }

    /**
     * Проверка изменения значения поля name у существующего объекта в базе данных при его обновление.
     */
    @Test
    public void checkCorrectChangedValueOfName() {
        Pet responsePet = CONFIGURE.addPet(pet);
        pet.setName("Alexey");
        pet.setId(responsePet.getId());
        Pet updatePet = CONFIGURE.updatePet(pet);
        deleteID = updatePet.getId();
        Assertions.assertEquals("Alexey", updatePet.getName(), "The name did not change when sending the PUT request.");
    }

    /**
     * Проверка изменения значения поля status с available на unavailable при отправке запроса с изменением.
     */
    @Test
    public void checkCorrectChangedValueOfStatus() {
        Pet responsePet = CONFIGURE.addPet(pet);
        pet.setStatus("unavailable");
        pet.setId(responsePet.getId());
        Pet updatePet = CONFIGURE.updatePet(pet);
        deleteID = updatePet.getId();
        Assertions.assertNotEquals("available", updatePet.getStatus(),
                "The status did not change when sending the PUT request.");
    }
}
