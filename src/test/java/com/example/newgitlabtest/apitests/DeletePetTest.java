package com.example.newgitlabtest.apitests;

import com.example.newgitlabtest.configure.TestsConfigure;
import com.example.newgitlabtest.pojo.Category;
import com.example.newgitlabtest.pojo.Pet;
import com.example.newgitlabtest.pojo.TagsItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public final class DeletePetTest {
    private Pet pet;
    private static final PetApi CONFIGURE = TestsConfigure.feignSettings();


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
     * Проверка получения 200 кода при отправке запроса с существующим ID.
     */
    @Test
    public void checkCode200() {
        Pet responsePet = CONFIGURE.addPet(pet);
        Assertions.assertEquals(TestsConfigure.OK, CONFIGURE.getDeleteStatusCodePet(responsePet.getId()).status());
    }

    /**
     * Проверка получения 404 кода при попытке удалить несуществующего объекта в базе.
     */
    @Test
    public void checkCode404() {
        Pet responsePet = CONFIGURE.addPet(pet);
        CONFIGURE.deletePet(responsePet.getId());
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CONFIGURE.deletePet(responsePet.getId());
        });
        Assertions.assertEquals("404", e.getMessage());
    }

    /**
     * Проверка удаления объекта при отправке валидного ID.
     */
    @Test
    public void checkSuccessDelete() {
        Pet responsePet = CONFIGURE.addPet(pet);
        CONFIGURE.deletePet(responsePet.getId());
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CONFIGURE.getPet(responsePet.getId());
        });
        Assertions.assertEquals("404", e.getMessage());
    }
}
